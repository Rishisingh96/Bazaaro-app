package com.rishi.repository;

import com.rishi.domain.AccountStatus;
import com.rishi.modal.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findFirstByEmail(String email);
    List<Seller> findByAccountStatus(AccountStatus status);
    
    // Deprecated: Use findFirstByEmail instead to handle duplicates
    @Deprecated
    default Seller findByEmail(String email) {
        return findFirstByEmail(email).orElse(null);
    }
}
