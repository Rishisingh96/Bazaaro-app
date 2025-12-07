package com.rishi.service.impl;

import com.rishi.modal.CartItem;
import com.rishi.modal.User;
import com.rishi.repository.CartItemRepository;
import com.rishi.repository.CartRepository;
import com.rishi.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    @Override
    public CartItem updatedCartItem(Long userId, Long id, CartItem cartItem) throws Exception {
        CartItem item = findCartItemById(id);

        User cartItemUser = item.getCart().getUser();

        if(cartItemUser.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setMrpPrice(item.getQuantity()*item.getProduct().getMrpPrice());
            item.setSellingPrice(item.getQuantity()*item.getProduct().getSellingPrice());
            return cartItemRepository.save(item);
        }
        throw new Exception(" Yor can't update this cartItem..");
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {
        CartItem item = findCartItemById(cartItemId);

        User cartItemUser = item.getCart().getUser();

        if(cartItemUser.getId().equals(userId)){
            cartItemRepository.delete(item);
        }
        else throw new Exception("you can't delete this item....");
    }

    @Override
    public CartItem findCartItemById(Long id) throws Exception {
        return cartItemRepository.findById(id).orElseThrow(()->
                new Exception(STR."cart item not fount with id....\{id}"));
    }
}
