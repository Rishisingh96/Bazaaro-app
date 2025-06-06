package com.rishi.repository;

import com.rishi.modal.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // Additional query methods can be defined here if needed
    // For example, you might want to find carts by user ID or other criteria

}
