package com.ecommerce.backend.CategoryManagement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CategoryEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String categoryName;
    private String prodName;
    private String imgUrl;
    
    public CategoryEntity() {
    }

    public CategoryEntity(Long id, String categoryName, String prodName, String imgUrl) {
        this.categoryName = categoryName;
        this.prodName = prodName;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    
}
