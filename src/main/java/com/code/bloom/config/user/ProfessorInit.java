package com.code.bloom.config.user;

import com.code.bloom.database.entity.user.RoleEntity;
import com.code.bloom.database.entity.user.UserEntity;
import com.code.bloom.database.entity.user.UserRoleEntity;
import com.code.bloom.database.entity.user.UserStatus;
import com.code.bloom.database.repository.user.RoleRepository;
import com.code.bloom.database.repository.user.UserRepository;
import com.code.bloom.database.repository.user.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class ProfessorInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${root.password}")
    private String rootPassword;

    @Override
    public void run(String... args) {
        String professorEmail = "rootprofessor@gmail.com";
        if (userRepository.findByEmail(professorEmail).isEmpty()) {

            UserEntity professor = new UserEntity();
            professor.setUsername("ProfessorRoot");
            professor.setEmail(professorEmail);
            professor.setPhoneNumber("00000000000");
            professor.setBirthdayDate(new Date());
            professor.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
            professor.setAge(calculateAge(professor.getBirthdayDate()));
            professor.setPassword(passwordEncoder.encode(rootPassword));
            professor.setUserStatus(UserStatus.OFFLINE);
            userRepository.save(professor);

            RoleEntity professorRole = roleRepository.findByRoleName("PROFESSOR_ROOT")
                    .orElseGet(() -> {
                        RoleEntity newRole = new RoleEntity();
                        newRole.setRoleName("PROFESSOR_ROOT");
                        return roleRepository.save(newRole);
                    });

            linkUserToRole(professor, professorRole);
            IO.println("PROFESSOR USER SEEDED: \n " + "Email: " + professorEmail + "\n " + "Password: " + rootPassword);

        }
    }

    private void linkUserToRole(UserEntity user, RoleEntity role) {
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);
    }

    private int calculateAge(Date birthDate) {
        if (birthDate == null) return 0;

        java.time.LocalDate birthLocalDate = new java.sql.Date(birthDate.getTime()).toLocalDate();
        java.time.LocalDate today = java.time.LocalDate.now();

        return java.time.Period.between(birthLocalDate, today).getYears();
    }
}
