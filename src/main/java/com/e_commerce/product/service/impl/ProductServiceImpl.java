package com.e_commerce.product.service.impl;

import com.e_commerce.product.model.Product;
import com.e_commerce.product.repository.ProductRepository;
import com.e_commerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, long productId) {
        Product oldProduct = productRepository.findProductById(productId);
        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setQuantity(product.getQuantity());
        oldProduct.setIsAvailable(product.getIsAvailable());
        oldProduct.setImages(product.getImages());
        return productRepository.save(oldProduct);
    }

    @Override
    public boolean deleteProduct(long productId) {
        Product product = productRepository.findProductById(productId);
        if (product != null) {
            productRepository.delete(product);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAvailableProducts() {
        return productRepository.findProductByIsAvailable(true);
    }

    @Override
    public Product getProduct(long productId) {
        return productRepository.findProductById(productId);
    }

    @Override
    public List<Product> getProduct(String productName) {
        return productRepository.findProductByName(productName);
    }
}
