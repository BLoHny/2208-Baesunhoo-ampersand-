package com.jtw.security_1.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;

import java.util.Date;

public class JwtTokenUtil  {

    @AllArgsConstructor
    private enum TokenType {
        ACCESS_TOKEN,
        REFRESH_TOKEN;
    }

    public static String createToken(String userName, String key, long expireTimeMs) {
        Claims claims = Jwts.claims(); //일종의 Map
        claims.put("userName", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact()
                ;
    }
}
