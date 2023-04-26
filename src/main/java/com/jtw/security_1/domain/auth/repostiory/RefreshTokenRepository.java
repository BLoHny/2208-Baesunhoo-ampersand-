package com.jtw.security_1.domain.auth.repostiory;

import com.jtw.security_1.domain.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}