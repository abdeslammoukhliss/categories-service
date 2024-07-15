package org.test.categorieservice.repositpries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.test.categorieservice.entities.Category;
@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Additional query methods if needed
}
