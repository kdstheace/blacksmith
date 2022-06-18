package com.daniel.blacksmith.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.blacksmith.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name); //필드변수의 이름과 맞춰줘야한다.
}
