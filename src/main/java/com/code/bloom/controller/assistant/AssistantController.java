package com.code.bloom.controller.assistant;

import com.code.bloom.dto.assistant.MessageRequest;
import com.code.bloom.dto.assistant.MessageResponse;
import com.code.bloom.service.assistant.AssistantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/BloomAssistant")
public class AssistantController {

    private final AssistantService assistantService;

    @PostMapping("/chat")
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest request) {
        MessageResponse response = assistantService.processUserMessage(request);
        return ResponseEntity.ok(response);
    }
}
