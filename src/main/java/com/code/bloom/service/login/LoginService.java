package com.code.bloom.service.login;

import com.code.bloom.database.entity.user.UserEntity;
import com.code.bloom.database.entity.user.UserStatus;
import com.code.bloom.database.repository.user.UserRepository;
import com.code.bloom.dto.login.LoginRequest;
import com.code.bloom.dto.login.LoginResponse;
import com.code.bloom.strategy.login.ILoginValidations;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private final List<ILoginValidations> loginValidationsList;

    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.email());

        loginValidationsList.forEach(strategy -> strategy.loginResponseValidation(loginRequest));

        user.setUserStatus(UserStatus.ONLINE);
        userRepository.save(user);

        var claims = JwtClaimsSet.builder()
                .subject(user.getUserId().toString())
                .claim("email", user.getEmail())
                .build();

        var token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(token, user.getEmail());
    }
}
