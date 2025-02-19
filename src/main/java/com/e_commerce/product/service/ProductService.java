package com.e_commerce.product.service;

import com.e_commerce.product.model.Product;

public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(Product product, long productId);
}
