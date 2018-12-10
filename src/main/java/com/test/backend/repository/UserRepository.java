package com.test.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findUserByUserName(String userName);
}
