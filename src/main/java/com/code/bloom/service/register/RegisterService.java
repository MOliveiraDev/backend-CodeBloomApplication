package com.code.bloom.service.register;

import com.code.bloom.database.entity.users.Role;
import com.code.bloom.database.entity.users.UserEntity;
import com.code.bloom.database.repository.users.UserRepository;
import com.code.bloom.dto.register.RegisterRequest;
import com.code.bloom.dto.register.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse newUserAluno(RegisterRequest request) {

        UserEntity user = new UserEntity();

        user.setUsername(request.username());
        user.setPhoneNumber(request.phoneNumber());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.ALUNO);
        userRepository.save(user);

        return new RegisterResponse("Usuário cadastrado com sucesso");

    }

    public RegisterResponse newUserProfessor(RegisterRequest request) {
        UserEntity user = new UserEntity();

        user.setUsername(request.username());
        user.setPhoneNumber(request.phoneNumber());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.PROFESSOR);
        userRepository.save(user);

        return new RegisterResponse("Professor cadastrado com sucesso");

    }
}
