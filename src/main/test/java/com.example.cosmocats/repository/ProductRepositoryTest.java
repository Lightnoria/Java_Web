package main.test.java.com.example.cosmocats.repository;

import com.example.cosmocats.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
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
}
