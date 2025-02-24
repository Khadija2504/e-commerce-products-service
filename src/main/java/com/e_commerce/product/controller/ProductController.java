package com.e_commerce.product.controller;

import com.e_commerce.product.dto.ProductDTO;
import com.e_commerce.product.mapper.ProductMapper;
import com.e_commerce.product.model.Product;
import com.e_commerce.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO, BindingResult result) {
        logger.info("Received request to add product: {}", productDTO.getName());
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            logger.warn("Validation errors while adding product: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productService.addProduct(product);
        logger.info("Product added successfully: {}", savedProduct.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/update-product/{productId}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO, BindingResult result, @PathVariable Long productId) {
        logger.info("Received request to update product with ID: {}", productId);
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            logger.warn("Validation errors while updating product: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }
        Product product = productMapper.toEntity(productDTO);
        Product updatedProduct = productService.updateProduct(product, productId);
        logger.info("Product updated successfully: {}", updatedProduct.getId());
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/delete-product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        logger.info("Received request to delete product with ID: {}", productId);
        boolean isDeleted = productService.deleteProduct(productId);
        if (isDeleted) {
            logger.info("Product deleted successfully: {}", productId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successfully.");
        } else {
            logger.warn("Failed to delete product: {}", productId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
    }

    @GetMapping("/display-all-products")
    public ResponseEntity<?> displayProducts() {
        logger.info("Fetching all products...");
        List<Product> products = productService.getAllProducts();
        logger.info("Fetched {} products", products.size());
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/display-all-available-products")
    public ResponseEntity<?> displayAvailableProducts() {
        logger.info("Fetching all available products...");
        List<Product> products = productService.getAvailableProducts();
        logger.info("Fetched {} available products", products.size());
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/product-details/{productId}")
    public ResponseEntity<?> productDetails(@PathVariable Long productId) {
        logger.info("Fetching details for product with ID: {}", productId);
        Product product = productService.getProduct(productId);
        if (product != null) {
            logger.info("Product details retrieved: {}", product);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            logger.warn("Product not found: {}", productId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
    }

    @GetMapping("/search-for-product/{productName}")
    public ResponseEntity<?> searchProduct(@PathVariable String productName) {
        logger.info("Searching for product with name: {}", productName);
        List<Product> products = productService.getProduct(productName);
        logger.info("Found {} products matching '{}'", products.size(), productName);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}
