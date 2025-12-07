package com.rishi.service;

import com.rishi.modal.CartItem;

public interface CartItemService {
    CartItem updatedCartItem(Long userId, Long id, CartItem cartItem) throws Exception;
    void removeCartItem(Long userId, Long cartItemId) throws Exception;
    CartItem findCartItemById(Long id) throws Exception;
}
