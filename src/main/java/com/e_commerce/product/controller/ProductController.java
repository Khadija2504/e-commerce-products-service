package com.e_commerce.product.controller;

import com.e_commerce.product.dto.ProductDTO;
import com.e_commerce.product.mapper.ProductMapper;
import com.e_commerce.product.model.Product;
import com.e_commerce.product.service.ProductService;
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

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/update-product/{productId}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO, BindingResult result, @PathVariable Long productId) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Product product = productMapper.toEntity(productDTO);
        Product updatedProduct = productService.updateProduct(product, productId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/delete-product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        boolean isDeleted = productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("product deleted: " + isDeleted);
    }

    @GetMapping("/display-all-products")
    public ResponseEntity<?> displayProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.FOUND).body(products);
    }

    @GetMapping("/display-all-available-products")
    public ResponseEntity<?> displayAvailableProducts() {
        List<Product> products = productService.getAvailableProducts();
        return ResponseEntity.status(HttpStatus.FOUND).body(products);
    }
}
