package com.code.bloom.service.home;

import com.code.bloom.database.entity.user.UserEntity;
import com.code.bloom.dto.account.MyAccountResponse;
import com.code.bloom.dto.account.UserRoleResponse;
import com.code.bloom.database.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyAccountService {

    private final UserRepository userRepository;

    public MyAccountResponse getMyProfile() throws AccessDeniedException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            throw new AccessDeniedException("Acesso negado");
        }

        String username = auth.getName();

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        List<UserRoleResponse> roles = user.getUserRole().stream()
                .map(r -> new UserRoleResponse(r.getIdUserRole(), r.getRole().getRoleName()))
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
