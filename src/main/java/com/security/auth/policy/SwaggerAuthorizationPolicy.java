package com.security.auth.policy;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@FunctionalInterface
public interface SwaggerAuthorizationPolicy {
    void configure(HttpSecurity http) throws Exception;
}
