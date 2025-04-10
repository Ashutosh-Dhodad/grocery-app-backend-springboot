package com.ecommerce.backend.CartManagement.Service;

import com.ecommerce.backend.CartManagement.CartEntity.CartEntity;
import com.ecommerce.backend.CartManagement.CartEntity.CartItemEntity;
import com.ecommerce.backend.CartManagement.CartRepo.CartItemRepo;
import com.ecommerce.backend.CartManagement.CartRepo.CartRepository;
import com.ecommerce.backend.UserData.UserRepo;
import com.ecommerce.backend.UserData.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepo cartItemRepository;

    @Autowired
    private UserRepo userRepo;

    public CartEntity getCart(Long userId) {
        UserEntity user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findByUserId(userId).orElseGet(() -> {
            CartEntity cart = new CartEntity();
            cart.setUser(user);
            return cartRepository.save(cart);
        });
    }

    public void addItem(Long userId, CartItemEntity item) {
        CartEntity cart = getCart(userId);
        item.setCart(cart); // Set the cart reference in the item
        cartItemRepository.save(item); // Save the CartItemEntity first
        cart.getItems().add(item);
        cartRepository.save(cart);
    }

    public void removeItem(Long userId, Long itemId) {
        CartEntity cart = getCart(userId);
        cart.getItems().removeIf(item -> item.getId().equals(itemId));
        cartRepository.save(cart);
    }

    public void updateItemQuantity(Long userId, Long productId, Long quantity) {
        CartEntity cart = getCart(userId);
        cart.getItems().stream()
                .filter(cartItem -> cartItem.getProductId().equals(productId))
                .findFirst()
                .ifPresent(cartItem -> cartItem.setQuantity(quantity));

        cartRepository.save(cart);
    }

    public void clearCart(Long userId) {
        CartEntity cart = getCart(userId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}