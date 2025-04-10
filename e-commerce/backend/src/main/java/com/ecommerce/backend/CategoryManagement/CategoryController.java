package com.ecommerce.backend.CategoryManagement;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get")
    public Map<String, List<CategoryEntity>> getCategories() {
        return categoryService.getAllCategories();
    }

   @PostMapping("/post")
    public ResponseEntity<Map<String, List<CategoryEntity>>> createCategories(@RequestBody Map<String, List<CategoryEntity>> categoryData) {
        // Convert Category to CategoryEntity if needed (see previous response)
        Map<String, List<CategoryEntity>> categoryEntityData = (categoryData);

        // Pass to the service for saving
        categoryService.addCategoryEntity(categoryEntityData);

        return new ResponseEntity<>(categoryData, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@customSecurity.hasRole(authentication, 'ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
