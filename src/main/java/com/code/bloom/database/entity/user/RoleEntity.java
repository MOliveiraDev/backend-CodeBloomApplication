package com.code.bloom.database.entity.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles_tb")
@Data
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

}
