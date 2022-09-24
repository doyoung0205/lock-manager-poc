package me.doyoung.lockmanagerpoc.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("dorie")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .and()
                .withUser("user")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("1234"))
                .roles("USER").roles("ADMIN")
        ;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();

        http
                .authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/", "/index", "/login.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//            .loginPage("/login.html") <- Spring Boot 기본 제공
                .defaultSuccessUrl("/pages/items")
                .and()
                .logout()
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }
}
