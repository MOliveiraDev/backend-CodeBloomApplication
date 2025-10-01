package com.code.bloom.controller.register;

import com.code.bloom.dto.register.RegisterRequest;
import com.code.bloom.dto.register.RegisterResponse;
import com.code.bloom.service.register.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/aluno")
    public RegisterResponse registerAlunoUser(@Valid @RequestBody RegisterRequest request) {
        return registerService.newUserAluno(request);
    }

    @PostMapping("/professor")
    public RegisterResponse registerResponse(@Valid @RequestBody RegisterRequest request) {
        return registerService.newUserProfessor(request);
    }
}
