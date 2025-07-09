package com.security.auth.policy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
@Profile({"local", "dev"})
@Slf4j
public class DevSwaggerPolicy implements SwaggerAuthorizationPolicy {

    private static final String[] SWAGGER_PATHS = {
            "/swagger-ui/**", "/swagger-resources/**",  "/v3/api-docs/**"
    };

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(SWAGGER_PATHS).permitAll();
        });
        log.info("Swagger endpoints PERMITTED (Dev Profile)");
    }
}