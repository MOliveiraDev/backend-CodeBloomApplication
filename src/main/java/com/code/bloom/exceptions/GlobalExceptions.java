package com.code.bloom.exceptions;

import com.code.bloom.exceptions.auth.UnauthorizedException;
import com.code.bloom.exceptions.login.EmailNotFoundException;
import com.code.bloom.exceptions.login.PasswordIncorretException;
import com.code.bloom.exceptions.register.*;
import com.code.bloom.exceptions.webhook.WebhookException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomExceptions> handleRuntimeException(RuntimeException ex) {
        CustomExceptions customException = new CustomExceptions(
                ex.getMessage(),
                "Erro do sistema interno.",
                400,
                java.time.LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(customException);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<CustomExceptions> handleEmailNotFoundException(EmailNotFoundException ex) {
        CustomExceptions customException = new CustomExceptions(
                ex.getMessage(),
                "O email informado não foi encontrado.",
                400,
                java.time.LocalDateTime.now()
        );
        return ResponseEntity.status(404).body(customException);
    }

    @ExceptionHandler(EmailActuallyExistsException.class)
    public ResponseEntity<CustomExceptions> handleEmailActuallyExistsException(EmailActuallyExistsException ex) {
        CustomExceptions customException = new CustomExceptions(
                ex.getMessage(),
                "O email já está cadastrado.",
                400,
                java.time.LocalDateTime.now()
        );
        return ResponseEntity.status(409).body(customException);
    }

    @ExceptionHandler(UsernameActuallyExistsException.class)
    public ResponseEntity<CustomExceptions> handleUsernameActuallyExistsException(UsernameActuallyExistsException ex) {
        CustomExceptions customException = new CustomExceptions(
                ex.getMessage(),
                "O nome de usário já está cadastrado.",
                400,
                java.time.LocalDateTime.now()
        );
        return ResponseEntity.status(409).body(customException);
    }

    @ExceptionHandler(PasswordLengthExeption.class)
    public ResponseEntity<CustomExceptions> handlePasswordLengthExeption(PasswordLengthExeption ex) {
        CustomExceptions customException = new CustomExceptions(
                ex.getMessage(),
                "Senha muito curta.",
                400,
                java.time.LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(customException);
    }

    @ExceptionHandler(EmailEmptyException.class)
    public ResponseEntity<CustomExceptions> handleEmailEmptyException(EmailEmptyException ex) {
        CustomExceptions customException = new CustomExceptions(
                ex.getMessage(),
                "Email vazio.",
                400,
                java.time.LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(customException);
    }

    @ExceptionHandler(UsernameIsEmptyException.class)
    public ResponseEntity<CustomExceptions> handleUsernameIsEmptyException(UsernameIsEmptyException ex) {
        CustomExceptions customException = new CustomExceptions(
                ex.getMessage(),
                "Nome de usuário vazio.",
                400,
                java.time.LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(customException);
    }

    @ExceptionHandler(PasswordIncorretException.class)
    public ResponseEntity<CustomExceptions> handlepasswordincorrectexception(PasswordIncorretException ex) {
        CustomExceptions customException = new CustomExceptions(
                ex.getMessage(),
                "A senha para logar no sistema está errada.",
                400,
                java.time.LocalDateTime.now()
        );
        return ResponseEntity.status(401).body(customException);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<CustomExceptions> handleUnauthorizedException(UnauthorizedException ex) {
        CustomExceptions customException = new CustomExceptions(
                ex.getMessage(),
                "Acesso não autorizado.",
                401,
                java.time.LocalDateTime.now()
        );
        return ResponseEntity.status(401).body(customException);
    }

    @ExceptionHandler(WebhookException.class)
    public ResponseEntity<CustomExceptions> handleWebhookException(WebhookException ex) {
        CustomExceptions customException = new CustomExceptions(
                ex.getMessage(),
                "Erro ao processar a requisição via webhook.",
                502,
                java.time.LocalDateTime.now()
        );
        return ResponseEntity.status(502).body(customException);
    }
}