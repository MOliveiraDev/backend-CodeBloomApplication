package com.code.bloom.dto.assistant;

public record MessageResponse(
        String title,
        String answer,
        String context,
        String type,
        String role
) {
}
