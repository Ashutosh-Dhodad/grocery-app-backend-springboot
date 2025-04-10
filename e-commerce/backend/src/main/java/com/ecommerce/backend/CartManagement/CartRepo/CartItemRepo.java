package com.ecommerce.backend.CartManagement.CartRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.CartManagement.CartEntity.CartItemEntity;

@Repository
public interface CartItemRepo extends JpaRepository<CartItemEntity, Long>{
    
}
