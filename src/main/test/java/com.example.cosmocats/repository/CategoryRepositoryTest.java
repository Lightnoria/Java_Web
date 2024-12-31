package main.test.java.com.example.cosmocats.repository;

import com.example.cosmocats.model.Category;

import main.java.com.example.cosmocats.repository.CategoryRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
class CategoryRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @DynamicPropertySource
    static void overrideProperties(org.springframework.test.context.DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testCreateAndReadCategory() {
        // Creating a category
        Category category = new Category();
        category.setName("Electronics");
        categoryRepository.save(category);

        // Check that the category has been saved
        Category savedCategory = categoryRepository.findById(category.getId()).orElse(null);
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getName()).isEqualTo("Electronics");
    }

    @Test
    void testDeleteCategory() {
        // Creating a category
        Category category = new Category();
        category.setName("Books");
        categoryRepository.save(category);

        // Deleting a category
        categoryRepository.delete(category);

        // Check that the category has been deleted
        Category deletedCategory = categoryRepository.findById(category.getId()).orElse(null);
        assertThat(deletedCategory).isNull();
    }
}
