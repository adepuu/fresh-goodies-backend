package com.adepuu.freshGoodiesBackend.products;

import com.adepuu.freshGoodiesBackend.products.entity.Product;
import com.adepuu.freshGoodiesBackend.products.service.ProductService;
import com.adepuu.freshGoodiesBackend.responses.Response;
import jakarta.validation.*;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v1/products")
@Log
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Product>>> getProducts() {
        return Response.successfulResponse("All products fetched", productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Optional<Product>>> getProduct(@PathVariable Long id) {
        var productFound = productService.getProduct(id);
        if (productFound.isEmpty()) {
            return Response.failedResponse(HttpStatus.NOT_FOUND.value(), "Product not found");
        }
        return Response.successfulResponse("Product detail found", productFound);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Product>> updateProduct(@Validated @RequestBody Product product, @PathVariable long id) {
        product.setId(id);
        return Response.successfulResponse("Update product success", productService.updateProduct(product));
    }

    @PostMapping
    public ResponseEntity<Response<Product>> createProduct(@Validated @RequestBody Product product) {
        var createdProduct = productService.addProduct(product);
        return Response.successfulResponse(HttpStatus.CREATED.value(), "New product created", productService.updateProduct(createdProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Objects>> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return Response.successfulResponse("Product deleted");
    }
}
