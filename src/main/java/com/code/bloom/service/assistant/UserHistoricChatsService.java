package com.code.bloom.service.assistant;

import com.code.bloom.database.entity.assistant.ChatsEntity;
import com.code.bloom.database.entity.assistant.MessagesEntity;
import com.code.bloom.database.entity.user.UserEntity;
import com.code.bloom.database.repository.assistant.ChatsRespository;
import com.code.bloom.database.repository.assistant.MessagesRepository;
import com.code.bloom.dto.account.ChatsHistoricResponse;
import com.code.bloom.dto.account.ChatsMessagesResponse;
import com.code.bloom.dto.assistant.MessageDto;
import com.code.bloom.strategy.auth.IAuthenticationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserHistoricChatsService {

    private final ChatsRespository chatsRespository;
    private final IAuthenticationStrategy iAuthenticationStrategy;
    private final MessagesRepository messagesRepository;

    @Transactional(readOnly = true)
    public ChatsHistoricResponse getHistoricChats() {
        UserEntity user = iAuthenticationStrategy.getAuthenticatedUser();

        Optional<ChatsEntity> chat = chatsRespository.findByUser(user);

        Optional<MessagesEntity> lastMessageOpt = messagesRepository.findTopByChatsOrderByTimestampDesc(chat);

        String lastMessage = lastMessageOpt.map(MessagesEntity::getContext).orElse("");

        return new ChatsHistoricResponse(chat.map(ChatsEntity::getTitle).orElse(""), lastMessage);
    }

    @Transactional(readOnly = true)
    public ChatsMessagesResponse getChatMessages() {
        UserEntity user = iAuthenticationStrategy.getAuthenticatedUser();

        Optional<ChatsEntity> chat = chatsRespository.findByUser(user);

        List<MessagesEntity> messages = messagesRepository.findByChatsOrderByTimestampAsc(chat);

        List<MessageDto> messageDtos = messages.stream()
                .map(msg -> new MessageDto(msg.getContext(), msg.getRole().name(), msg.getTimestamp()))
                .toList();

        return new ChatsMessagesResponse(chat.map(ChatsEntity::getTitle).orElse(""), messageDtos);
    }

}
