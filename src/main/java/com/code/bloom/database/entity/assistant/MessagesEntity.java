package com.code.bloom.database.entity.assistant;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "messages_tb")
@Data
public class MessagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "chats_id", nullable = false)
    private ChatsEntity chats;

    @Column(name = "content", nullable = false)
    private String context;

    @Column(name = "create_at", nullable = false)
    private Timestamp timestamp;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private FlowRole role;

}
