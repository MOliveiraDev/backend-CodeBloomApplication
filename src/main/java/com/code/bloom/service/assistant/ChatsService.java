package com.code.bloom.service.assistant;

import com.code.bloom.database.entity.assistant.ChatsEntity;
import com.code.bloom.database.entity.user.UserEntity;
import com.code.bloom.database.repository.assistant.ChatsRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatsService {

    private final ChatsRespository chatsRespository;

    public ChatsEntity createChat(UserEntity user, String title) {
        ChatsEntity chats = new ChatsEntity();
        chats.setUser(user);
        chats.setTitle(title);
        chats.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        return chatsRespository.save(chats);
    }

    public Optional<ChatsEntity> findByUser(UserEntity user) {
        return chatsRespository.findByUser(user);
    }
}
