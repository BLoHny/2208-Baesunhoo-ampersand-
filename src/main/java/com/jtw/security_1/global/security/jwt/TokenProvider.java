package com.jtw.security_1.global.security.jwt;

import com.jtw.security_1.global.security.auth.MemberDetailsService;
import com.jtw.security_1.global.security.exception.TokenExpirationException;
import com.jtw.security_1.global.security.exception.TokenNotValidException;
import com.jtw.security_1.global.security.jwt.properties.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final MemberDetailsService memberDetailsService;
    private final JwtProperties jwtProperties;
    private final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 120;
    private final long REFRESH_TOKEN_EXPIRE_TIME = ACCESS_TOKEN_EXPIRE_TIME * 12 * 7;

    @AllArgsConstructor
    private enum TokenType {
        ACCESS_TOKEN("accessToken"),
        REFRESH_TOKEN("refreshToken");
        String value;
    }

    @AllArgsConstructor
    private enum TokenClaimName {
        USER_NAME("userName"),
        TOKEN_TYPE("tokenType");
        String value;
    }

    private Key getSignkey(String secretKey) {
        byte[] bytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytes);
    }
    //TODO : 분류
    private String generateToken(String userName, TokenType tokenType, String secret, long expireTime) {
        final Claims claims = Jwts.claims();
        claims.put(TokenClaimName.USER_NAME.value, userName);
        claims.put(TokenClaimName.TOKEN_TYPE.value, tokenType);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expireTime))
                .signWith(getSignkey(secret), SignatureAlgorithm.HS256)
                .compact();
    }


    public Claims extractAllClaims(String token, String secret) {
        token = token.replace("Bearer", "");

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignkey(secret))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new TokenExpirationException();
        } catch (JwtException e) {
            throw new TokenNotValidException();
        }
    }

    public ZonedDateTime getExpiredAtToken(String token, String secret) {
        return ZonedDateTime.now().plusSeconds(ACCESS_TOKEN_EXPIRE_TIME);
    }

    public String getUserEmail(String token, String secret) {
        return extractAllClaims(token, secret).get(TokenClaimName.USER_NAME.value, String.class);
    }

    public String getTokenType(String token, String secret) {
        return extractAllClaims(token, secret).get(TokenClaimName.TOKEN_TYPE.value, String.class);
    }

    public String generatedAccessToken(String userName) {
        return generateToken(userName, TokenType.ACCESS_TOKEN, jwtProperties.getAccessSecret(), ACCESS_TOKEN_EXPIRE_TIME);
    }

    public String generatedRefreshToken(String userName) {
        return generateToken(userName, TokenType.REFRESH_TOKEN, jwtProperties.getRefreshSecret(), REFRESH_TOKEN_EXPIRE_TIME);
    }

    public UsernamePasswordAuthenticationToken authenticationToken(String userName) {
        UserDetails userDetails = memberDetailsService.loadUserByUsername(userName);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}