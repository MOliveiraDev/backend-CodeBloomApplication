package com.code.bloom.controller.users;

import com.code.bloom.dto.aluno.AlunoResponseDto;
import com.code.bloom.service.user.aluno.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/professor")
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping("/buscarAlunos")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'PROFESSOR_ROOT')")
    public ResponseEntity<List<AlunoResponseDto>> listarAlunos(Pageable pageable) {
        List<AlunoResponseDto> alunos = alunoService.findAllAlunos(pageable);
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/buscarAlunosOnline")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'PROFESSOR_ROOT')")
    public ResponseEntity<List<AlunoResponseDto>> listarAlunosOnline(Pageable pageable) {
        List<AlunoResponseDto> alunos = alunoService.findAllAlunosOnline(pageable);
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/buscarAlunosOnline")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'PROFESSOR_ROOT')")
    public ResponseEntity<List<AlunoResponseDto>> listarAlunosOffline(Pageable pageable) {
        List<AlunoResponseDto> alunos = alunoService.findAllAlunosOffline(pageable);
        return ResponseEntity.ok(alunos);
    }
}
