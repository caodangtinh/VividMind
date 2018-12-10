package com.test.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.backend.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
	public List<UserRole> findByUserUserName(String userName);

}
