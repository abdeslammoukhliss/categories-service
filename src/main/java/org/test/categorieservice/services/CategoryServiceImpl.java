package org.test.categorieservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.categorieservice.entities.Category;
import org.test.categorieservice.entities.IdsRequest;
import org.test.categorieservice.repositpries.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

/*    @Override
    public List<Category> getCategoriesByIds(List<Long> ids) {
        return categoryRepository.findByIdIn(new IdsRequest(ids));
    }*/

    @Override
    public List<Category> getCategoriesByName(String name) {
        return categoryRepository.findByName(name);
    }


    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }


}
