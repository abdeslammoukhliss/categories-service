package org.test.categorieservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.categorieservice.entities.Category;
import org.test.categorieservice.repositpries.CategoryRepository;

import java.util.List;
@Service
public interface CategoryService {
    List<Category> getCategoriesByName(String name);
    Category createCategory(Category category);
    void deleteCategory(Long id);
    List<Category> getCategories();
}

