package com.ecommerce.backend.OrderManagement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.ecommerce.backend.ProductManagement.ProductEntity;
import com.ecommerce.backend.ProductManagement.ProductRepo;
import com.ecommerce.backend.UserData.UserEntity;
import com.ecommerce.backend.UserData.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    /**
     * User taps “Buy Now” → creates a new OrderEntity.
     */
    @PostMapping("/buy")
    public ResponseEntity<String> buyNow(
            @RequestParam Long userId,
            @RequestParam Long productId) {

        Optional<UserEntity> userOpt = userRepo.findById(userId);
        Optional<ProductEntity> prodOpt = productRepo.findById(productId);

        if (userOpt.isEmpty() || prodOpt.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Invalid user or product ID.");
        }

        UserEntity user = userOpt.get();
        ProductEntity product = prodOpt.get();

        OrderEntity order = new OrderEntity();
        order.setUserName(user.getUsername());
        order.setStatus("Pending");
        order.setTotalPrice(product.getPrice());
        order.setCreatedAt(LocalDateTime.now());
        order.setProductId(product.getId());
        order.setProductName(product.getName());

        orderRepo.save(order);
        return ResponseEntity.ok("Order placed successfully.");
    }

    /**
     * Admin can fetch every order, including user & product info.
     */
    @GetMapping("/all")
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        return ResponseEntity.ok(orderRepo.findAll());
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<String> updateOrderStatus(
        @PathVariable Long orderId,
        @RequestParam String status
    ){
    Optional<OrderEntity> optionalOrder = orderRepo.findById(orderId);

    if (optionalOrder.isPresent()) {
        OrderEntity order = optionalOrder.get();
        order.setStatus(status);
        orderRepo.save(order);
        return ResponseEntity.ok("Order status updated to: " + status);
    } else {
        return ResponseEntity.badRequest().body("Order not found with ID: " + orderId);
    }
}

}
