package com.rishi.service.impl;

import com.rishi.config.JwtProvider;
import com.rishi.modal.User;
import com.rishi.repository.UserRepository;
import com.rishi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with id: " + id));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public User updateUser(Long id, User user) throws Exception {
        User existingUser = findUserById(id);

        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setMobile(user.getMobile());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);

    }

    @Override
    public void deleteUser(Long id) throws Exception {
        User user = findUserById(id);
        userRepository.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
