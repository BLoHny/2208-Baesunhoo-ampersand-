package com.jtw.security_1.service;

import com.jtw.security_1.domain.User;
import com.jtw.security_1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String join(String userName, String password) {

        //중복 CHECK -> USERNAME
        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    throw new RuntimeException(userName + "는 이미 있습니다.");
                });

        User user = User.builder()
                .userName(userName)
                .password(password)
                .build();
        //저장
        userRepository.save(user);

        return "SUCCESS";
    }
}
