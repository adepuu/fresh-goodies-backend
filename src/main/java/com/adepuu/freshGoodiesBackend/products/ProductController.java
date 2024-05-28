package com.adepuu.freshGoodiesBackend.products;

import com.adepuu.freshGoodiesBackend.products.model.Product;
import com.adepuu.freshGoodiesBackend.products.service.ProductService;
import com.adepuu.freshGoodiesBackend.products.service.impl.ProductServiceImpl;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@Log
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable Long id) {
        var productFound = productService.getProduct(id);
        if (!productFound.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productFound);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody final Product product) {
        try {
            var createdProduct = productService.addProduct(product);
            return ResponseEntity.ok(createdProduct);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
