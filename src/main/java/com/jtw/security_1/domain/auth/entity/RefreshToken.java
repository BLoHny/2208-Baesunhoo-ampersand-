package com.jtw.security_1.domain.auth.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@RedisHash(value = "refreshToken")
public class RefreshToken {

    @Id
    private String userName;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private long expiredAt;
}