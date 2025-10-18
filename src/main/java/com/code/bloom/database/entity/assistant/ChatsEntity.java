package com.code.bloom.database.entity.assistant;

import com.code.bloom.database.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "chats_tb")
@Data
public class ChatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chats_id")
    private Long chatId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "title", nullable = false, length = 225)
    private String title;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

}
