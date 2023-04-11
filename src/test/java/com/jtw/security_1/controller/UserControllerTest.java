package com.jtw.security_1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jtw.security_1.domain.dto.UserJoinRequest;
import com.jtw.security_1.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
    public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    void join() throws Exception {
        String userName = "BLoHny";
        String password = "1234";

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password))))//UserJoin에 담은값 JSON으로 변환
                .andDo(print()) //요청 응답 출력
                .andExpect(status().isOk()); //Is it 200?
    }

    @Test
    @DisplayName("회원가입 실패 - userName 중복")
    void join_fail() throws Exception {
        String username = "BLoHny";
        String password = "1234";

        when(userService.join(any(), any()))
                .thenThrow(new RuntimeException("해당 userID가 중복됩니다"));

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(username, password))))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}
