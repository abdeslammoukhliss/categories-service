package org.test.categorieservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.test.categorieservice.entities.Category;
import org.test.categorieservice.repositpries.CategoryRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;
    @BeforeEach
    void setUp() {
        categoryRepository.deleteAll();
    }
    @Test
    void testCreateCategory() {
        Category category = new Category();
        category.setName("Food");

        category = categoryRepository.save(category);
        assertThat(category.getId()).isNotNull();

        Category foundCategory = categoryRepository.findById(category.getId()).orElse(null);
        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory.getName()).isEqualTo("Food");
    }
    @Test
    void testUpdateCategory() {
        Category category = new Category();
        category.setName("Food");

        category = categoryRepository.save(category);
        assertThat(category.getId()).isNotNull();

        Category foundCategory = categoryRepository.findById(category.getId()).orElse(null);
        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory.getName()).isEqualTo("Food");

        foundCategory.setName("Drinks");
        categoryRepository.save(foundCategory);

        Category updatedCategory = categoryRepository.findById(foundCategory.getId()).orElse(null);
        assertThat(updatedCategory).isNotNull();
        assertThat(updatedCategory.getName()).isEqualTo("Drinks");
    }
    @Test
    void testDeleteCategory() {
        Category category = new Category();
        category.setName("Food");

        category = categoryRepository.save(category);
        assertThat(category.getId()).isNotNull();

        categoryRepository.deleteById(category.getId());

        Category foundCategory = categoryRepository.findById(category.getId()).orElse(null);
        assertThat(foundCategory).isNull();
    }
    @Test
    void testFindAllCategories() {
        Category category1 = new Category();
        category1.setName("Food");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setName("Drinks");
        categoryRepository.save(category2);

        List<Category> categories = categoryRepository.findAll();
        assertThat(categories.size()).isEqualTo(2);
    }
}
