package me.gaeng.springbootdeveloper.repository;

import me.gaeng.springbootdeveloper.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId (Long userId);
    Optional<RefreshToken> findByRefreshToken (String refreshToken);
}
