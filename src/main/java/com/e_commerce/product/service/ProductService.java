package com.e_commerce.product.service;

import com.e_commerce.product.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(Product product, long productId);
    boolean deleteProduct(long productId);
    List<Product> getAllProducts();
}
