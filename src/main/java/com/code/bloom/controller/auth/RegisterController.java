package com.code.bloom.controller.auth;

import com.code.bloom.dto.register.RegisterRequest;
import com.code.bloom.dto.register.RegisterResponse;
import com.code.bloom.service.auth.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<RegisterResponse> registerAlunoUser(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = registerService.newUserAluno(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/professor")
    @PreAuthorize( "hasRole('PROFESSOR')")
    public ResponseEntity<RegisterResponse> registerResponse(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = registerService.newUserProfessor(request);
        return ResponseEntity.ok(response);
    }
}
