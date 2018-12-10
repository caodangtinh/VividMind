package com.test.backend.service.impl;

import com.test.backend.model.User;
import com.test.backend.repository.UserRepository;
import com.test.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User findUserByUserName(String userName) {
        return this.userRepository.findUserByUserName(userName);
    }

}
