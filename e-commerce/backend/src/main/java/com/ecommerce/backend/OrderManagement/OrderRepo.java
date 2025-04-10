package com.ecommerce.backend.OrderManagement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Long> {

    long countByStatus(String status);

    List<OrderEntity> findTop5ByOrderByCreatedAtDesc();

    @Query("SELECT SUM(o.totalPrice) FROM OrderEntity o WHERE o.status = :status")
    Double getTotalRevenue(@Param("status") String status);

    @Query(value = "SELECT product_id, COUNT(*) FROM orders GROUP BY product_id", nativeQuery = true)
    List<Object[]> getBestSellingProductStats();
    


    
}
