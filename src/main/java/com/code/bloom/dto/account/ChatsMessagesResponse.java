package com.code.bloom.dto.account;

import com.code.bloom.dto.assistant.MessageDto;

import java.sql.Timestamp;
import java.util.List;

public record ChatsMessagesResponse(String title, Timestamp createdAt, List<MessageDto> messages) {
}

