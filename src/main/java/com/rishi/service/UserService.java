package com.rishi.service;

import com.rishi.modal.User;

import java.util.List;

public interface UserService {

    User findUserByJwtToken(String jwtToken) throws Exception;

    User findUserByEmail(String email) throws Exception;

    User findUserById(Long id) throws Exception;

    User saveUser(User user);

    User updateUser(Long id, User user) throws Exception;

    void deleteUser(Long id) throws Exception;

    List<User> getAllUsers();
}
