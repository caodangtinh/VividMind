package com.test.backend.service;

import com.test.backend.model.User;

public interface UserService {

    User findUserByUserName(String userName);

}
