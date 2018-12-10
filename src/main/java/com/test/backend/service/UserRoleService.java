package com.test.backend.service;

import java.util.List;

public interface UserRoleService {
	public List<String> findAllRolesForUsername(String userName);
}
