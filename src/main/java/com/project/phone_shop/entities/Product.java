package com.project.phone_shop.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products", uniqueConstraints = {@UniqueConstraint(columnNames = {"model_id", "color_id"})})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name" , unique = true)
    private String productName;

    @Column(name = "available_unit")
    private Integer availableUnit;

    @DecimalMin(value = "0.00001d", message = "Sale Price must be greater than 0")
    @Column(name = "sale_price")
    private BigDecimal salePrice;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;
}
