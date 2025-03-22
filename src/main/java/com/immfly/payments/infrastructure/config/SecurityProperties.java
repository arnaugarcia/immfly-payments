package com.immfly.payments.infrastructure.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
record SecurityProperties(String secret, long expiration) { }
