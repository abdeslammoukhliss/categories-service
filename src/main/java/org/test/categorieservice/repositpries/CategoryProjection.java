package org.test.categorieservice.repositpries;

import org.springframework.data.rest.core.config.Projection;
import org.test.categorieservice.entities.Category;

@Projection(types = Category.class, name = "fullCategory")
public interface CategoryProjection {
    Long getId();
    String getName();
}
