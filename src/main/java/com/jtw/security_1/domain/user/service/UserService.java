package com.jtw.security_1.domain.user.service;

import com.jtw.security_1.domain.auth.entity.RefreshToken;
import com.jtw.security_1.domain.auth.presentation.LoginResponse;
import com.jtw.security_1.domain.auth.repostiory.RefreshTokenRepository;
import com.jtw.security_1.domain.user.entity.User;
import com.jtw.security_1.domain.user.presentation.dto.UserJoinRequest;
import com.jtw.security_1.domain.user.presentation.dto.UserLoginRequest;
import com.jtw.security_1.domain.user.repository.UserRepository;
import com.jtw.security_1.global.exception.AppException;
import com.jtw.security_1.global.exception.ErrorCode;
import com.jtw.security_1.global.security.jwt.TokenProvider;
import com.jtw.security_1.global.security.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserLoginRequest loginRequest;
    private final JwtProperties jwtProperties;

    @Transactional(rollbackFor = Exception.class)
    public String join(UserJoinRequest request) {

        //중복 CHECK -> USERNAME
        userRepository.findByUserName(request.getUserName())
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, request.getUserName() + "는 이미 있습니다.");
                });

        User user = User.builder()
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        //저장
        userRepository.save(user);

        return "SUCCESS";
    }

    public LoginResponse execute(UserLoginRequest signInRequest) {

        User user = userRepository
                .findByUserName(signInRequest.getUserName())
                .orElseThrow(()->new RuntimeException("없는 유저 입니다."));

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("패스워드가 일치 하지 않음");
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
