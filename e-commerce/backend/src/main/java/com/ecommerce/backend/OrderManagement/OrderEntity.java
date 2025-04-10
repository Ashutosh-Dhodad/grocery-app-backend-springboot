package com.ecommerce.backend.OrderManagement;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String status;
    private double totalPrice;
    private LocalDateTime createdAt;

    // ─── NEW FIELDS ───────────────────────────────────────────────────────────────
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;
    // ──────────────────────────────────────────────────────────────────────────────

    public OrderEntity() { }

    // updated constructor
    public OrderEntity(Long id, String userName, String status, double totalPrice,
                       LocalDateTime createdAt, Long productId, String productName) {
        this.id = id;
        this.userName = userName;
        this.status = status;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.productId = productId;
        this.productName = productName;
    }

    // existing getters/setters…

    

    public Long getProductId() {
        return productId;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
}
