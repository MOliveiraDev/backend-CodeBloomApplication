package com.code.bloom.service.register;

import com.code.bloom.database.entity.user.*;
import com.code.bloom.database.repository.user.RoleRepository;
import com.code.bloom.database.repository.user.UserRepository;
import com.code.bloom.database.repository.user.UserRoleRepository;
import com.code.bloom.dto.register.RegisterRequest;
import com.code.bloom.dto.register.RegisterResponse;
import com.code.bloom.strategy.register.IRegisterValidations;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final List<IRegisterValidations> registerValidationsList;


    @Transactional
    public RegisterResponse newUserAluno(RegisterRequest request) {
        registerValidationsList.forEach(registerValidations -> registerValidations.registerResponseValidations(request));

        UserEntity user = buildBaseUser(request);
        userRepository.save(user);

        RoleEntity alunoRole = roleRepository.findByRoleName("ALUNO")
                .orElseGet(() -> {
                    RoleEntity newRole = new RoleEntity();
                    newRole.setRoleName("ALUNO");
                    return roleRepository.save(newRole);
                });


        linkUserToRole(user, alunoRole);
        return new RegisterResponse("Usuário aluno registrado com sucesso");


    }

    @Transactional
    public RegisterResponse newUserProfessor(RegisterRequest request) {
        registerValidationsList.forEach(registerValidations -> registerValidations.registerResponseValidations(request));

        UserEntity user = buildBaseUser(request);
        userRepository.save(user);

        RoleEntity professorRole = roleRepository.findByRoleName("PROFESSOR")
                .orElseGet(() -> {
                    RoleEntity newRole = new RoleEntity();
                    newRole.setRoleName("PROFESSOR");
                    return roleRepository.save(newRole);
                });

        linkUserToRole(user, professorRole);


        return new RegisterResponse("Usuário professor registrado com sucesso");
    }

    private UserEntity buildBaseUser(RegisterRequest request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.username());
        user.setPhoneNumber(request.phoneNumber());
        user.setBirthdayDate(request.birthdayDate());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        user.setAge(calculateAge(request.birthdayDate()));
        user.setUserStatus(UserStatus.OFFLINE);
        return user;
    }

    private int calculateAge(Date birthDate) {
        if (birthDate == null) return 0;

        java.time.LocalDate birthLocalDate = new java.sql.Date(birthDate.getTime()).toLocalDate();
        java.time.LocalDate today = java.time.LocalDate.now();

        return java.time.Period.between(birthLocalDate, today).getYears();
    }


    private void linkUserToRole(UserEntity user, RoleEntity role) {
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);
    }

}
