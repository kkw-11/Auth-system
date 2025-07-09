package com.security.auth.policy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@Slf4j
public class ProdSwaggerPolicy implements SwaggerAuthorizationPolicy {

    private static final String[] SWAGGER_PATHS = {
            "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**"
    };

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(SWAGGER_PATHS).denyAll();
        });
        log.info("Swagger endpoints DENIED (Prod Profile)");
    }
}
