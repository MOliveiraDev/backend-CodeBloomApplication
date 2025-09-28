package com.code.bloom.exceptions;

import com.code.bloom.exceptions.register.EmailActuallyExistsException;
import com.code.bloom.exceptions.register.UsernameActuallyExistsException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Hidden
public class GlobalExceptions {

    @ExceptionHandler
    public CustomExceptions handleRuntimeException(RuntimeException ex) {
        return new CustomExceptions(
                ex.getMessage(),
                "Um erro inesperado ocorreu.",
                400,
                java.time.LocalDateTime.now()
        );
    }

    @ExceptionHandler(EmailActuallyExistsException.class)
    public ResponseEntity<CustomExceptions> handleEmailActuallyExistsException(EmailActuallyExistsException ex) {
        CustomExceptions customException = new CustomExceptions(
                ex.getMessage(),
                "O email já está cadastrado.",
                409,
                java.time.LocalDateTime.now()
        );
        return ResponseEntity.status(409).body(customException);
    }

    @ExceptionHandler(UsernameActuallyExistsException.class)
    public ResponseEntity<CustomExceptions> handleUsernameActuallyExistsException(UsernameActuallyExistsException ex) {
        CustomExceptions customExceptions = new CustomExceptions(
                ex.getMessage(),
                "O nome de usário já está cadastrado.",
                409,
                java.time.LocalDateTime.now()
        );
        return ResponseEntity.status(409).body(customExceptions);
    }
}
