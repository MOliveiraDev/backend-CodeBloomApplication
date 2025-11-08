package com.code.bloom.database.repository.assistant;

import com.code.bloom.database.entity.assistant.MessagesEntity;
import com.code.bloom.database.entity.assistant.ChatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessagesRepository extends JpaRepository<MessagesEntity, Long> {

    Optional<MessagesEntity> findTopByChatsOrderByTimestampDesc(Optional<ChatsEntity> chats);

    List<MessagesEntity> findByChatsOrderByTimestampAsc(Optional<ChatsEntity> chats);
}
