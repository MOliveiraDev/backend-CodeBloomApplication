package com.code.bloom.service.user.aluno;

import com.code.bloom.database.entity.user.UserEntity;
import com.code.bloom.database.entity.user.UserStatus;
import com.code.bloom.database.repository.user.AlunoRepository;
import com.code.bloom.dto.account.UserRoleResponse;
import com.code.bloom.dto.aluno.AlunoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public List<AlunoResponseDto> findAllAlunos(Pageable pageable) {

        List<UserEntity> aluno = alunoRepository.findAllByRoleName("ALUNO", pageable).getContent();

        return aluno.stream()
                .map(user -> new AlunoResponseDto(
                        user.getUsername(),
                        user.getUserRole().stream()
                                .map(r -> new UserRoleResponse(r.getRole().getRoleName()))
                                .toList(),
                        user.getUserStatus()
                ))
                .toList();
    }

    public List<AlunoResponseDto> findAllAlunosOnline(Pageable pageable) {

        List<UserEntity> aluno = alunoRepository.findAllByRoleName("ALUNO", pageable).getContent();

        return aluno.stream()
                .map(user -> new AlunoResponseDto(
                        user.getUsername(),
                        user.getUserRole().stream()
                                .map(r -> new UserRoleResponse(r.getRole().getRoleName()))
                                .toList(),
                        UserStatus.ONLINE
                ))
                .toList();
    }

    public List<AlunoResponseDto> findAllAlunosOffline(Pageable pageable) {

        List<UserEntity> aluno = alunoRepository.findAllByRoleName("ALUNO", pageable).getContent();

        return aluno.stream()
                .map(user -> new AlunoResponseDto(
                        user.getUsername(),
                        user.getUserRole().stream()
                                .map(r -> new UserRoleResponse(r.getRole().getRoleName()))
                                .toList(),
                        UserStatus.OFFLINE
                ))
                .toList();
    }
}
