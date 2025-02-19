package com.e_commerce.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private double price;
    private int quantity;
    private boolean isAvailable;

    @ElementCollection
    @CollectionTable(name = "images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image")
    private List<String> images;
}
