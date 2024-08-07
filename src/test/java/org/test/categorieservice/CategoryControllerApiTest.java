package org.test.categorieservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.test.categorieservice.entities.Category;
import org.test.categorieservice.repositpries.CategoryRepository;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerApiTest {


    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        categoryRepository.deleteAll();
    }

    @Test
    void testGetCategories() throws Exception {
        Category category = new Category();
        category.setName("Utilities");
        categoryRepository.save(category);

        ResultActions resultActions = mockMvc.perform(get("/categories"));
        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.categories", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.categories[0].name", is("Utilities")));
    }
    @Test
    void testGetCategoryById() throws Exception {
        Category category = new Category();
        category.setName("Utilities");
        category = categoryRepository.save(category);
        ResultActions resultActions = mockMvc.perform(get("/categories/" + category.getId()));
        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Utilities")));
    }


    @Test
    void testUpdateCategory() throws Exception {
        Category category = new Category();
        category.setName("Health");
        category = categoryRepository.save(category);
        category.setName("Healthcare");
        categoryRepository.save(category);
        ResultActions resultActions = mockMvc.perform(get("/categories"));
        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.categories", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.categories[0].name", is("Healthcare")));
    }
    @Test
    void testDeleteCategory() throws Exception {
        Category category = new Category();
        category.setName("Health");
        category = categoryRepository.save(category);
        categoryRepository.delete(category);
        ResultActions resultActions = mockMvc.perform(get("/categories"));
        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.categories", hasSize(0)));
    }
    @Test
    void testCreateCategory() throws Exception {
        Category category = new Category();
        category.setName("Health");

        // Perform the POST request
        ResultActions resultActions = mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(category)));

        // Validate the response
        resultActions.andExpect(status().isCreated());
    }

}
