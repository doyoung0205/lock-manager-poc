package me.doyoung.lockmanagerpoc.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.util.StringUtils;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Configuration(proxyBeanMethods = false)
public class RedisConfig {

    private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.host}")
    public String host;

    private RedisServer redisServer;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        final RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        final LettuceConnectionFactory redisConnectionFactory = new LettuceConnectionFactory(standaloneConfiguration);
        log.info("EmbeddedRedisConnectionFactory(Lettuce) configuration enabled host :: {}, port :: {} ",
                redisConnectionFactory.getHostName(), redisConnectionFactory.getPort());
        return redisConnectionFactory;
    }


    @PostConstruct
    public void redisServer() throws IOException {
        int availablePort = isRedisRunning() ? findAvailablePort() : this.port;
        redisServer = RedisServer.builder()
                .port(availablePort)
                .setting("maxmemory 128M") //maxheap 128M
                .build();
        redisServer.start();

        this.port = availablePort;
        log.info("EmbeddedRedis server {} availablePort start ", availablePort);
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }

    /**
     * Embedded Redis가 현재 실행중인지 확인
     */
    private boolean isRedisRunning() throws IOException {
        return isRunning(executeGrepProcessCommand(port));
    }

    /**
     * 현재 PC/서버에서 사용가능한 포트 조회
     */
    public int findAvailablePort() throws IOException {

        for (int availablePort = 10000; availablePort <= 65535; availablePort++) {
            Process process = executeGrepProcessCommand(availablePort);
            if (!isRunning(process)) {
                return availablePort;
            }
        }

        throw new IllegalArgumentException("Not Found Available port: 10000 ~ 65535");
    }

    /**
     * 해당 port를 사용중인 프로세스 확인하는 sh 실행
     */
    private Process executeGrepProcessCommand(int port) throws IOException {
        String command = String.format("netstat -nat | grep LISTEN|grep %d", port);
        String[] shell = {"/bin/sh", "-c", command};
        return Runtime.getRuntime().exec(shell);
    }

    /**
     * 해당 Process가 현재 실행중인지 확인
     */
    private boolean isRunning(Process process) {
        String line;
        StringBuilder pidInfo = new StringBuilder();

        try (BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

            while ((line = input.readLine()) != null) {
                pidInfo.append(line);
            }

        } catch (Exception exception) {
            log.error("throw error At isRunning", exception);
        }

        return StringUtils.hasText(pidInfo.toString());
    }
}
