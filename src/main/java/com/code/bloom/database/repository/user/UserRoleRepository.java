package com.code.bloom.database.repository.user;

import com.code.bloom.database.entity.user.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

}
