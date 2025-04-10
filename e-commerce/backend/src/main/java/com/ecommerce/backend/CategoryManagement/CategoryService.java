package com.ecommerce.backend.CategoryManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Map<String, List<CategoryEntity>> getAllCategories(){
        List<CategoryEntity> categories = (List<CategoryEntity>) categoryRepo.findAll();
        return categories.stream().collect(Collectors.groupingBy(CategoryEntity::getCategoryName));
    }

    public void addCategoryEntity(Map<String, List<CategoryEntity>> categoryData) {
        // Flatten the Map and save the list of CategoryEntity objects
        for (Map.Entry<String, List<CategoryEntity>> entry : categoryData.entrySet()) {
            List<CategoryEntity> categoryEntities = entry.getValue();
            
            // Save each list of CategoryEntities (could be done in bulk if desired)
            categoryRepo.saveAll(categoryEntities);
        }
    }

    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
