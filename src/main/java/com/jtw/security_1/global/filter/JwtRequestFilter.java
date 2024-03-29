package com.jtw.security_1.global.filter;

import com.jtw.security_1.global.security.exception.TokenNotValidException;
import com.jtw.security_1.global.security.jwt.TokenProvider;
import com.jtw.security_1.global.security.jwt.properties.JwtProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;

    public void registerSecurityContext(HttpServletRequest request, String userName) {
        UsernamePasswordAuthenticationToken authenticationToken = tokenProvider.authenticationToken(userName);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws  ServletException, IOException {
        String accessToken = request.getHeader("Authorization");

        if (!Objects.isNull(accessToken)) {
            tokenProvider.extractAllClaims(accessToken, jwtProperties.getAccessSecret());

            if (!tokenProvider.getTokenType(accessToken, jwtProperties.getAccessSecret()).equals("ACCESS_TOKEN")) {
                throw new TokenNotValidException();
            }

            String userName = tokenProvider.getUserName(accessToken, jwtProperties.getAccessSecret());
            registerSecurityContext(request, userName);
        }
        filterChain.doFilter(request, response);
    }
}