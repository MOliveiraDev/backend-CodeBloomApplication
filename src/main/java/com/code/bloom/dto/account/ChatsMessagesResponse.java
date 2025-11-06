package com.code.bloom.dto.account;

import com.code.bloom.dto.assistant.MessageDto;

import java.util.List;

public record ChatsMessagesResponse(String title, List<MessageDto> messages) {
}

