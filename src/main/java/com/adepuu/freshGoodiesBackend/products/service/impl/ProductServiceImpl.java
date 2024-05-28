package com.adepuu.freshGoodiesBackend.products.service.impl;

import com.adepuu.freshGoodiesBackend.products.model.Product;
import com.adepuu.freshGoodiesBackend.products.service.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductServiceImpl implements ProductService {
    List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Optional<Product> getProduct(long id) {
        return products.stream().filter(product -> product.getId() == id).findFirst();
    }

    @Override
    public Product addProduct(Product product) {
        boolean exists = products.stream().anyMatch(p -> p.getId() == product.getId());
        if (exists) {
            throw new IllegalArgumentException("Product with ID " + product.getId() + " already exists.");
        }
        products.add(product);
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        Product currentProduct = products.stream()
                .filter(p -> p.getId() == product.getId())
                .findFirst()
                .orElse(null);

        if (currentProduct != null) {
            currentProduct.setName(product.getName());
            currentProduct.setCategory(product.getCategory());
            currentProduct.setImageUrl(product.getImageUrl());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setWeight(product.getWeight());
            currentProduct.setMetadata(product.getMetadata());
        }

        return currentProduct;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
