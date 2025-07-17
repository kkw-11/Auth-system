package com.security.auth.config;

import com.security.auth.policy.SwaggerAuthorizationPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final SwaggerAuthorizationPolicy swaggerPolicy;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        log.info("-----------Security Filter Chain-----------");

        http.csrf(csrf -> csrf.disable());

        swaggerPolicy.configure(http);

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/api/signup").permitAll();
            auth.anyRequest().authenticated();
        });

        return http.build();
    }
}
