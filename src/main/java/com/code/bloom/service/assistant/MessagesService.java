package com.code.bloom.service.assistant;

import com.code.bloom.database.entity.assistant.ChatsEntity;
import com.code.bloom.database.entity.assistant.FlowRole;
import com.code.bloom.database.entity.assistant.MessagesEntity;
import com.code.bloom.database.repository.assistant.MessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessagesService {

    private final MessagesRepository messagesRepository;

    public void saveMessage(ChatsEntity chats, String content, FlowRole role) {
        MessagesEntity messages = new MessagesEntity();
        messages.setChats(chats);
        messages.setContext(content);
        messages.setRole(role);
        messages.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
        messagesRepository.save(messages);
    }
}
