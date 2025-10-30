package com.code.bloom.service.assistant;

import com.code.bloom.database.entity.assistant.ChatsEntity;
import com.code.bloom.database.entity.assistant.FlowRole;

import com.code.bloom.database.entity.user.UserEntity;
import com.code.bloom.dto.assistant.MessageRequest;
import com.code.bloom.dto.assistant.MessageResponse;
import com.code.bloom.dto.flow.N8nResponse;
import com.code.bloom.service.flow.N8nConnService;
import com.code.bloom.strategy.auth.IAuthenticationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssistantService {

    private final ChatsService chatsService;
    private final IAuthenticationStrategy iAuthenticationStrategy;
    private final MessagesService messagesService;
    private final N8nConnService n8nConnService;

    public MessageResponse processUserMessage(MessageRequest request) {

        UserEntity user = iAuthenticationStrategy.getAuthenticatedUser();


        ChatsEntity chats = chatsService.findByUser(user)
                .orElseGet(() -> chatsService.createChat(user, "Chat bot do usuário: " + user.getUsername()));

        messagesService.saveMessage(chats, request.message(), FlowRole.USER);

        N8nResponse n8nResponse = n8nConnService.sendToN8n(request.message(), user.getUsername());

        messagesService.saveMessage(chats, n8nResponse.answer(), FlowRole.ASSISTANT);

        return new MessageResponse(
                chats.getTitle(),
                n8nResponse.answer(),
                n8nResponse.context(),
                n8nResponse.type(),
                FlowRole.ASSISTANT.name()
        );
    }
}
