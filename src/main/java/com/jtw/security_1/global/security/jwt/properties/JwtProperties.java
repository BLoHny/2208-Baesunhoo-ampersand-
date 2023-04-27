package com.jtw.security_1.global.security.jwt.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    @Value("${jwt.accessSecret}")
    private String accessSecret;

    @Value("${jwt.refreshSecret}")
    private String refreshSecret;
}
