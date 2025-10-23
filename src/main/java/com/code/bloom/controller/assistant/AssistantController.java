package com.code.bloom.controller.assistant;

import com.code.bloom.dto.account.ChatsHistoricResponse;
import com.code.bloom.dto.account.ChatsMessagesResponse;
import com.code.bloom.dto.assistant.MessageRequest;
import com.code.bloom.dto.assistant.MessageResponse;
import com.code.bloom.service.assistant.AssistantService;
import com.code.bloom.service.assistant.UserHistoricChatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/BloomAssistant")
public class AssistantController {

    private final AssistantService assistantService;
    private final UserHistoricChatsService historicChatsService;

    @PostMapping("/chat")
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest request) {
        MessageResponse response = assistantService.processUserMessage(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/chatbot-historic")
    public ResponseEntity<ChatsHistoricResponse> getChatBotHistoric() throws AccessDeniedException {
        ChatsHistoricResponse response = historicChatsService.getHistoricChats();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/chatbot-messages")
    public ResponseEntity<ChatsMessagesResponse> getChatBotMessages() throws AccessDeniedException {
        ChatsMessagesResponse response = historicChatsService.getChatMessages();
        return ResponseEntity.ok(response);
    }
}
