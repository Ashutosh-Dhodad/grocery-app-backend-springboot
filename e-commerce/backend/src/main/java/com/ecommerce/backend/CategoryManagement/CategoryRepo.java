package com.ecommerce.backend.CategoryManagement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends CrudRepository<CategoryEntity, Long>{
    
}
