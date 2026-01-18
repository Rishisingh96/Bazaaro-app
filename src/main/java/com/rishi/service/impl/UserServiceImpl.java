package com.rishi.service.impl;

import org.springframework.stereotype.Service;

import com.rishi.config.JwtProvider;
import com.rishi.modal.User;
import com.rishi.repository.UserRepository;
import com.rishi.service.UserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;



    @Override
    public User findUserByJwtToken(String jwtToken) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwtToken);
        
        return this.findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findFirstByEmail(email).orElse(null);
        if(user == null){
            throw new Exception("User not found with email: " + email);
        }
        return user;
    }

    @Override
    public User findUserById(Long id)  throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User saveUser(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
