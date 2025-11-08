package com.code.bloom.database.repository.user;

import com.code.bloom.database.entity.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AlunoRepository extends JpaRepository<UserEntity, UUID> {

    @Query("SELECT DISTINCT u " +
            "FROM UserEntity u " +
            "JOIN u.userRole ur " +
            "JOIN ur.role r " +
            "WHERE r.roleName = :roleName")
    Page<UserEntity> findAllByRoleName(@Param("roleName") String roleName, Pageable pageable);
}
