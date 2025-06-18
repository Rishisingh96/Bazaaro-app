package com.rishi.service;

import com.rishi.modal.User;

public interface UserService {
    
    User findUserByJwtToken(String jwtToken) throws Exception;

    User findUserByEmail(String email) throws Exception;

    User findUserById(Long id) throws Exception;

    User saveUser(User user) throws Exception;

    User updateUser(User user) throws Exception;
}
