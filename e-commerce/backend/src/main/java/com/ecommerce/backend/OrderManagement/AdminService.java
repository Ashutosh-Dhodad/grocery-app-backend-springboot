package com.ecommerce.backend.OrderManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.ProductManagement.ProductEntity;
import com.ecommerce.backend.ProductManagement.ProductRepo;

@Service
public class AdminService {

    @Autowired
    private ProductRepo productRepository;

     @Autowired
    private OrderRepo orderRepository;
    
    public List<Map<String, Object>> getBestSellingProducts() {
        List<Object[]> stats = orderRepository.getBestSellingProductStats();
        List<Map<String, Object>> result = new ArrayList<>();
    
        for (Object[] row : stats) {
            Long productId = Long.parseLong(String.valueOf(row[0]));
            Long count = Long.parseLong(String.valueOf(row[1]));
    
            ProductEntity product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", product.getName());
                map.put("imageUrl", product.getUrl());
                map.put("sales", count);
                result.add(map);
            }
        }
    
        return result;
    }
    
}
