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


}
