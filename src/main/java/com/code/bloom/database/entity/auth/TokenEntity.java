package com.code.bloom.database.entity.auth;

import com.code.bloom.database.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "token_tb")
@Data
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;

    @Column(name = "token", nullable = false, unique = true, length = 512)
    private String token;

    @Column(name = "is_logged_out", nullable = false)
    private boolean isLoggedOut;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
