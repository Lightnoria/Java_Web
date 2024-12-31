package main.test.java.com.example.cosmocats.repository;

import com.example.cosmocats.model.Product;
import com.example.cosmocats.repository.ProductRepository;

import main.java.com.example.cosmocats.projection.ProductProjection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@SpringBootTest
@Autowired
@Testcontainers
@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @Autowired
    private ProductRepository productRepository;

    @BeforeAll
    static void setUp() {
        postgresContainer.start();
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setName("Cosmic Toy");
        product.setDescription("A toy for cats");
        product.setPrice(19.99);

        Product savedProduct = productRepository.save(product);

        assertEquals("Cosmic Toy", savedProduct.getName());
    }

    @Test
    void testFindByNameContaining() {
        // Create category and product
        Category category = new Category();
        category.setName("Electronics");
        Product product = new Product();
        product.setName("Smartphone");
        product.setPrice(500.0);
        product.setCategory(category);
        productRepository.save(product);

        // Testing the search by product name
        List<ProductProjection> products = productRepository.findByNameContaining("Smart");
        assertThat(products).isNotEmpty();
        assertThat(products.get(0).getName()).contains("Smart");
    }

    @Test
    void testFindMostPopularProducts() {
        // Create categories and products
        Category category = new Category();
        category.setName("Books");
        Product product1 = new Product();
        product1.setName("Java Programming");
        product1.setPrice(40.0);
        product1.setCategory(category);
        Product product2 = new Product();
        product2.setName("Spring Boot Essentials");
        product2.setPrice(45.0);
        product2.setCategory(category);
        productRepository.save(product1);
        productRepository.save(product2);

        // Let's assume that both products were ordered.
        // Let's make sure that query returns results.

        List<Object[]> mostPopularProducts = productRepository.findMostPopularProducts();
        assertThat(mostPopularProducts).isNotEmpty();
        assertThat(mostPopularProducts.get(0)[0]).isEqualTo("Java Programming");
        assertThat(mostPopularProducts.get(1)[0]).isEqualTo("Spring Boot Essentials");
    }
}
