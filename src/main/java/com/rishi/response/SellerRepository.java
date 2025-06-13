package com.rishi.response;

import com.rishi.modal.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long>{

    // This interface will automatically provide CRUD operations for Seller entities.
    // Additional custom query methods can be defined here if needed.

    // Example: Find a seller by their email
    Seller findByEmail(String email);


}
