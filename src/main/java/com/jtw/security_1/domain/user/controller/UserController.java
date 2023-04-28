package com.jtw.security_1.domain.user.controller;

import com.jtw.security_1.domain.user.service.UserJoinService;
import com.jtw.security_1.domain.user.presentation.dto.UserJoinRequest;
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
public class UserController {

    private final UserJoinService userService;

    @PostMapping("/join")
    public ResponseEntity<HttpStatus> join(@RequestBody UserJoinRequest dto) {
        userService.join(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
