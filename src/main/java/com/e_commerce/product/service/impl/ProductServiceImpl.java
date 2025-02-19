package com.e_commerce.product.service.impl;

import com.e_commerce.product.model.Product;
import com.e_commerce.product.repository.ProductRepository;
import com.e_commerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
