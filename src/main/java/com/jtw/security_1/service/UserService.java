package com.jtw.security_1.service;

import com.jtw.security_1.domain.User;
import com.jtw.security_1.exception.AppException;
import com.jtw.security_1.exception.ErrorCode;
import com.jtw.security_1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public String join(String userName, String password) {

        //중복 CHECK -> USERNAME
        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, userName + "는 이미 있습니다.");
                });

        User user = User.builder()
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .build();
        //저장
        userRepository.save(user);

        return "SUCCESS";
    }

    public String login(String userName, String password) {
        //UserName 없음
         User selectedUser = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, userName + "이 없습니다."));
        //password 틀림
        if (!passwordEncoder.matches(selectedUser.getPassword(), password)) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드가 잘못 입력 했습니다.");
        }
        return null;
    }
}
