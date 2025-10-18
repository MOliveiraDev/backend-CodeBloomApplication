package com.code.bloom.service.home;


import com.code.bloom.database.entity.user.UserEntity;
import com.code.bloom.dto.account.MyAccountResponse;
import com.code.bloom.dto.account.UserRoleResponse;
import com.code.bloom.strategy.auth.IAuthenticationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class MyAccountService {

    private final IAuthenticationStrategy iAuthenticationStrategy;

    public MyAccountResponse getMyProfile() {

        UserEntity user = iAuthenticationStrategy.getAuthenticatedUser();

        List<UserRoleResponse> roles = user.getUserRole().stream()
                .map(r -> new UserRoleResponse(r.getRole().getRoleName()))
                .toList();

        return new MyAccountResponse(
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                roles,
                user.getAge()
        );
    }

}
