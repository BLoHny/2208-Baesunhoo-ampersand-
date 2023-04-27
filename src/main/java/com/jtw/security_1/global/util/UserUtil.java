package com.jtw.security_1.global.util;

import com.jtw.security_1.domain.user.entity.User;
import com.jtw.security_1.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {
    
    private final UserRepository userRepository;

    public User currentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("로그인 안됨"));
    }
}
