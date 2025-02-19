package com.e_commerce.product.mapper;

import com.e_commerce.product.dto.ProductDTO;
import com.e_commerce.product.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDTO productDTO);
}

