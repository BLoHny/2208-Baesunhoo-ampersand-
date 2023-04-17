package com.jtw.security_1.filter;

import com.jtw.security_1.service.UserService;
import com.jtw.security_1.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Value("${jwt.token.secret}")
    private String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}", authorization);

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("authorization 을 잘못보냈습니다.");
            filterChain.doFilter(request, response);
            return;
        }
        //Token 꺼내기
        String token = authorization.split(" ")[1];

        //Token Expired 체크
        if(JwtTokenUtil.isExpired(token, key)) {
            log.error("토큰이 만료되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        //Token에서 Username 꺼내기
        String userName = JwtTokenUtil.getUserName(token, key);
        log.info("userName : {}", userName);

        //권한 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, null, List.of(new SimpleGrantedAuthority("USER")));
        //Detail
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
