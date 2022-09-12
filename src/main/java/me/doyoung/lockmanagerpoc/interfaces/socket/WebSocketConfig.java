package me.doyoung.lockmanagerpoc.interfaces.socket;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final LockHandler lockHandler;

    public WebSocketConfig(LockHandler lockHandler) {
        this.lockHandler = lockHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(lockHandler, "ws/lock").setAllowedOrigins("*");
    }
}
