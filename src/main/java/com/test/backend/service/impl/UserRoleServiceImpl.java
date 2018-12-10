package com.test.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.backend.model.UserRole;
import com.test.backend.repository.UserRoleRepository;
import com.test.backend.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public List<String> findAllRolesForUsername(String userName) {
		List<UserRole> userRoles = this.userRoleRepository.findByUserUserName(userName);
		List<String> roles = new ArrayList<>();
		for(UserRole userRole:userRoles)
		{
			roles.add(userRole.getRole().getRoleName());
		}
		return roles;
		
	}

}
