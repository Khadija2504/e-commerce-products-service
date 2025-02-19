package com.e_commerce.product.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {

    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @Positive(message = "Price must be greater than zero")
    private double price;

    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;

    private Boolean isAvailable;

    @Size(max = 5, message = "A product can have at most 5 images")
    private List<@NotBlank(message = "Image URL cannot be blank") @Pattern(regexp = "^(http|https)://.*$", message = "Invalid image URL") String> images;
}
