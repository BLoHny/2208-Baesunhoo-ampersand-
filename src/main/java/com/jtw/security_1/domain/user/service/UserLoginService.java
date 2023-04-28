package com.jtw.security_1.domain.user.service;

import com.jtw.security_1.domain.auth.entity.RefreshToken;
import com.jtw.security_1.domain.auth.presentation.LoginResponse;
import com.jtw.security_1.domain.auth.repostiory.RefreshTokenRepository;
import com.jtw.security_1.domain.user.entity.User;
import com.jtw.security_1.domain.user.exception.InvalidPasswordException;
import com.jtw.security_1.domain.user.exception.UserNotFoundException;
import com.jtw.security_1.domain.user.presentation.dto.UserLoginRequest;
import com.jtw.security_1.domain.user.repository.UserRepository;
import com.jtw.security_1.global.security.jwt.TokenProvider;
import com.jtw.security_1.global.security.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserLoginRequest loginRequest;
    private final JwtProperties jwtProperties;

    public LoginResponse execute(UserLoginRequest signInRequest) {

        User user = userRepository
                .findByUserName(signInRequest.getUserName())
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }


        String accessToken = tokenProvider.generatedAccessToken(signInRequest.getUserName());
        String refreshToken = tokenProvider.generatedRefreshToken(signInRequest.getUserName());

        RefreshToken refreshTokenEntity = new RefreshToken(signInRequest.getUserName(), refreshToken,tokenProvider.getREFRESH_TOKEN_EXPIRE_TIME());

        refreshTokenRepository.save(refreshTokenEntity);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(tokenProvider.getExpiredAtToken(accessToken, jwtProperties.getAccessSecret()))
                .build();
    }
}
