package com.jtw.security_1.domain.user.controller;

import com.jtw.security_1.domain.auth.presentation.LoginResponse;
import com.jtw.security_1.domain.user.presentation.dto.UserLoginRequest;
import com.jtw.security_1.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserLoginController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid UserLoginRequest loginRequest) {
        LoginResponse loginResponse = userService.execute(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
