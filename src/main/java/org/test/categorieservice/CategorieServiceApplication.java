package org.test.categorieservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.test.categorieservice.entities.Category;
import org.test.categorieservice.repositpries.CategoryRepository;

@SpringBootApplication
public class CategorieServiceApplication implements org.springframework.boot.CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(CategorieServiceApplication.class, args);
    }

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public void run(String... args) throws Exception {
        Category category = new Category();
        category.setName("walo");
        categoryRepository.save(category);
    }
}
