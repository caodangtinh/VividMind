package com.test.backend.service;

import java.util.List;

public interface UserRoleService {
    List<String> findAllRolesForUsername(String userName);
}
