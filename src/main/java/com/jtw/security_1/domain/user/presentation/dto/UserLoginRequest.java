package com.jtw.security_1.domain.user.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Component
@NoArgsConstructor
public class UserLoginRequest {

    private String userName;
    private String password;
}
