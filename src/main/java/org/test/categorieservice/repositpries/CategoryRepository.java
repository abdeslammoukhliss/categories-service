package org.test.categorieservice.repositpries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.test.categorieservice.entities.Category;
import org.test.categorieservice.entities.IdsRequest;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories",excerptProjection = CategoryProjection.class)
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Additional query methods if needed
    List<Category> findByName(String name);


}
