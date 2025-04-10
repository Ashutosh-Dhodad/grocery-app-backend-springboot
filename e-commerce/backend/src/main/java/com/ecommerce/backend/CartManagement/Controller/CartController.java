package com.ecommerce.backend.CartManagement.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.backend.CartManagement.CartEntity.CartEntity;
import com.ecommerce.backend.CartManagement.CartEntity.CartItemEntity;
import com.ecommerce.backend.CartManagement.Service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}/get")
    public ResponseEntity<CartEntity> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<Void> addItem(@PathVariable Long userId, @RequestBody CartItemEntity item) {
        cartService.addItem(userId, item);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/updateQuantity")
    public ResponseEntity<Void> updateItemQuantity(@PathVariable Long userId, @RequestBody Map<String, Long> request) {
        Long productId = request.get("productId");
        Long quantity = request.get("quantity");
        cartService.updateItemQuantity(userId, productId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/remove/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long userId, @PathVariable Long itemId) {
        cartService.removeItem(userId, itemId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}