package com.code.bloom.dto.assistant;

import java.sql.Timestamp;

public record MessageDto(
        String content,
        String role,
        Timestamp timestamp
) {
}

