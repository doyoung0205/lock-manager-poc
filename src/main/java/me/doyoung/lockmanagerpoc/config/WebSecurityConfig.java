package me.doyoung.lockmanagerpoc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
public class WebSecurityConfig implements WebSecurityCustomizer {

    @Override
    public void customize(WebSecurity web) {
        web.ignoring().anyRequest();
    }
}
