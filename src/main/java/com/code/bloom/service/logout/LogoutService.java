package com.code.bloom.service.logout;

import com.code.bloom.database.entity.user.UserEntity;
import com.code.bloom.database.entity.user.UserStatus;
import com.code.bloom.database.repository.user.UserRepository;
import com.code.bloom.strategy.logout.ILogoutValidations;
import com.code.bloom.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogoutService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final List<ILogoutValidations> logoutValidationsList;

    public String logout(String token) {

        logoutValidationsList.forEach(strategy -> strategy.logoutResponseValidation(token));

        String email = jwtUtil.extractEmail(token);
        UserEntity user = userRepository.findByEmail(email);

        user.setUserStatus(UserStatus.OFFLINE);
        String message = "Logout realizado com sucesso";
        userRepository.save(user);

        return message;
    }
}