package com.rishi.repository;

import com.rishi.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Additional query methods can be defined here if needed
    Optional<User> findFirstByEmail(String email);
    
    // Deprecated: Use findFirstByEmail instead to handle duplicates
    @Deprecated
    default User findByEmail(String email) {
        return findFirstByEmail(email).orElse(null);
    }
}
