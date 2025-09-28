package com.code.bloom.exceptions;

import java.time.LocalDateTime;

public record CustomExceptions(String message, String details, int statusCode, LocalDateTime timestamp) {
}
