package com.example.cosmocats.service;

import com.example.cosmocats.dto.ProductDTO;
import com.example.cosmocats.exception.ResourceNotFoundException;
import com.example.cosmocats.mapper.ProductMapper;
import com.example.cosmocats.model.Product;
import com.example.cosmocats.repository.ProductRepository;

import main.java.com.example.cosmocats.service.FeatureToggleService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final FeatureToggleService featureToggleService;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, FeatureToggleService featureToggleService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.featureToggleService = featureToggleService;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    public List<ProductDTO> getAllProducts() {
        if (!featureToggleService.isKittyProductsEnabled()) {
            throw new FeatureNotAvailableException("Kitty Products feature is disabled.");
        }
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return productMapper.toDTO(product);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
