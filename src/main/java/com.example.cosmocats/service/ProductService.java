package com.example.cosmocats.service;

import com.example.cosmocats.dto.ProductDTO;
import com.example.cosmocats.exception.ResourceNotFoundException;
import com.example.cosmocats.mapper.ProductMapper;
import com.example.cosmocats.model.Product;
import com.example.cosmocats.projection.ProductProjection;
import com.example.cosmocats.repository.ProductRepository;
import com.example.cosmocats.exception.FeatureNotAvailableException;
import com.example.cosmocats.service.FeatureToggleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final FeatureToggleService featureToggleService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductDTO createProduct(ProductDTO product) {
        // Logic for creating a product
        return null;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<ProductDTO> getAllProducts() {
        // Logic for retrieving all products
        return null;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProduct(Long id) {
        // Logic for deleting a product
    }

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, FeatureToggleService featureToggleService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.featureToggleService = featureToggleService;
    }

    // A method for creating a product
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    // Method for obtaining all products
    public List<ProductDTO> getAllProducts() {
        if (!featureToggleService.isKittyProductsEnabled()) {
            throw new FeatureNotAvailableException("Kitty Products feature is disabled.");
        }
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Method for retrieving product by id
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return productMapper.toDTO(product);
    }

    // Method for upgrading the product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDTO(updatedProduct);
    }

    // Method for product removal
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    // Method for searching products by name using projection
    public List<ProductProjection> searchProductsByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    // Method for obtaining the most popular products
    public List<Object[]> getMostPopularProducts() {
        return productRepository.findMostPopularProducts();
    }
}
