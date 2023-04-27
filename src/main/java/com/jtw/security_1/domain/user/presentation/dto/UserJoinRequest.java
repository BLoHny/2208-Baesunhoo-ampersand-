package com.jtw.security_1.domain.user.presentation.dto;

import com.jtw.security_1.domain.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserJoinRequest {

    private String userName;
    private String password;
    private Role role;
}
