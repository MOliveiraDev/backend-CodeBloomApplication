package com.code.bloom.database.repository.user;

import com.code.bloom.database.entity.user.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleName(String roleName);
}
