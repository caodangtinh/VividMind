package com.test.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.backend.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
