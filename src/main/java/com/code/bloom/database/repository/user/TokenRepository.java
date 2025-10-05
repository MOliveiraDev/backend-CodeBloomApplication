package com.code.bloom.database.repository.user;

import com.code.bloom.database.entity.user.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    Optional<TokenEntity> findByToken(String token);

    List<TokenEntity> findAllByIsLoggedOutTrue();
}