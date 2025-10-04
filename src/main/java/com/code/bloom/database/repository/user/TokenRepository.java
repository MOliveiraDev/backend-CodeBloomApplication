package com.code.bloom.database.repository.user;

import com.code.bloom.database.entity.user.TokenEntity;
import com.code.bloom.database.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    Optional<TokenEntity> findByToken(String token);

    List<TokenEntity> findAllByUserAndIsLoggedOutFalse(UserEntity user);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update TokenEntity t set t.isLoggedOut = true where t.user = :user and t.isLoggedOut = false")
    int logoutAllByUser(@Param("user") UserEntity user);
}