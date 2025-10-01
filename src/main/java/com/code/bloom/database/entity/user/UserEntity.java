package com.code.bloom.database.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.util.UUID;

@Entity
@Table(name = "users_tb")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NumberFormat
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Email
    @Column(name = "email", nullable = false)
    private String email;


    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "user_active", nullable = false)
    private UserStatus userStatus;

}
