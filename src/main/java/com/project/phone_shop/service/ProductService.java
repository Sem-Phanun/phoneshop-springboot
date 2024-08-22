package com.project.phone_shop.service;

import com.project.phone_shop.dto.ProductImportDTO;
import com.project.phone_shop.entities.Product;

import java.math.BigDecimal;

public interface ProductService {
    Product create(Product product);
    Product getProductById(Long id);
    void importProduct(ProductImportDTO importDTO);
    void setSalePrice(Long productId, BigDecimal price);
    void validateStock(Long productId, Integer quantity);
}
