package com.ecommerce.backend.ProductManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepo productRepo;

    public List<ProductEntity> getAllProducts() {
        return (List<ProductEntity>) productRepo.findAll();
    }

    public List<ProductEntity> getProductsByCategory(String category) {
        return productRepo.findByCategory(category);
    }

    public ProductEntity addProduct(ProductEntity product) {
        return productRepo.save(product);
    }

    public ProductEntity updateProduct(ProductEntity product) {
        return productRepo.save(product);
    }

    public ProductEntity getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}

