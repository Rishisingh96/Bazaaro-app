package com.rishi.repository;

import com.rishi.modal.Cart;
import com.rishi.modal.CartItem;
import com.rishi.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);


}
