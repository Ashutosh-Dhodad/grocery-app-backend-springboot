package com.ecommerce.backend.OrderManagement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.backend.ProductManagement.ProductRepo;
import com.ecommerce.backend.UserData.UserRepo;
import com.ecommerce.backend.UserData.UserService;

@RestController
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private UserRepo userRepository;
    
    @Autowired
    private UserService userService;

    //📌 1. API for Dashboard Statistics
   @GetMapping("/stats")
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalProducts", productRepository.count());
        stats.put("totalOrders", orderRepository.count());
        stats.put("totalUsers", userRepository.count());
        stats.put("pendingOrders", orderRepository.countByStatus("Pending"));
        stats.put("totalRevenue", orderRepository.getTotalRevenue("Delivered"));
        return stats;
    }
    

    // 📌 2. API to Get Recent Orders (Last 5 Orders)
   @GetMapping("/recent-orders")
public List<OrderResponse> getRecentOrders() {
    List<OrderEntity> orders = orderRepository.findTop5ByOrderByCreatedAtDesc();

    return orders.stream()
        .map(order -> {
            OrderResponse res = new OrderResponse();
            res.setOrderId(order.getId());
            res.setCustomerName(order.getUserName()); // Join user table
            res.setStatus(order.getStatus());
            return res;
        })
        .collect(Collectors.toList());
}

    @GetMapping("/best-selling-products")
    public List<Map<String, Object>> getBestSellingProducts() {
    return adminService.getBestSellingProducts();
}



}
