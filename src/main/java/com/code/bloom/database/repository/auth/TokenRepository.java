package com.code.bloom.database.repository.auth;

import com.code.bloom.database.entity.auth.TokenEntity;
import com.code.bloom.database.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    Optional<TokenEntity> findByToken(String token);

    List<TokenEntity> findAllByIsLoggedOutTrue();

    List<TokenEntity> findAllByIsLoggedOutFalse();

    long countByUserAndIsLoggedOutFalse(UserEntity user);
}