package com.code.bloom.dto.account;

import java.sql.Timestamp;

public record ChatsHistoricResponse(String title,String lastMessage,Timestamp createdAt) {
}
