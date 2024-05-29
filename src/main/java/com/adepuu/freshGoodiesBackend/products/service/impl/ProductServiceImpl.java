package com.adepuu.freshGoodiesBackend.products.service.impl;

import com.adepuu.freshGoodiesBackend.exceptions.ApplicationException;
import com.adepuu.freshGoodiesBackend.exceptions.DataNotFoundException;
import com.adepuu.freshGoodiesBackend.products.entity.Product;
import com.adepuu.freshGoodiesBackend.products.repository.ProductRepository;
import com.adepuu.freshGoodiesBackend.products.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new DataNotFoundException("Product with ID " + id + " not found.");
        }
        return product;
    }
    @Override
    public Product addProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            throw new DataNotFoundException("Product with ID " + product.getId() + " already exists.");
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        if (!productRepository.existsById(product.getId())) {
            throw new DataNotFoundException("Product with ID " + product.getId() + " does not exist.");
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new DataNotFoundException("Product with ID " + id + " does not exist.");
        }
        productRepository.softDeleteById(id);
        productRepository.softDeleteMetadataByProductId(id);
    }
}
