package com.code.bloom.dto.aluno;

import com.code.bloom.dto.account.UserRoleResponse;

import java.util.List;

public record AlunoResponseDto(
        String username,
        List<UserRoleResponse> userRole,
        com.code.bloom.database.entity.user.UserStatus userStatus
) {
}
