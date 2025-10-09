package com.code.bloom.dto.account;

import java.util.List;

public record MyAccountResponse(
        String username,
        String email,
        String phoneNumber,
        List<UserRoleResponse> userRole,
        Integer age
) {}
