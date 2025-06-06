package com.rishi.repository;

import com.rishi.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Additional query methods can be defined here if needed
    User findByEmail(String email);
}
