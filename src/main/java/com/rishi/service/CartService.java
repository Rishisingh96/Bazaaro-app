package com.rishi.service;

import com.rishi.modal.Cart;
import com.rishi.modal.CartItem;
import com.rishi.modal.Product;
import com.rishi.modal.User;


public interface CartService {
    public CartItem addCartItem(
            User user,
            Product product,
            String size,
            int quantity
    );
    public Cart findUserCart(User user);
};

