package com.project.phone_shop.service;

import com.project.phone_shop.dto.ProductImportDTO;
import com.project.phone_shop.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Map;

public interface ProductService {
    Product create(Product product);
    Product getProductById(Long id);
    Product getByModelIdAndColorId(Long modelId, Long colorId);
    void importProduct(ProductImportDTO importDTO);
    void setSalePrice(Long productId, BigDecimal price);
    void validateStock(Long productId, Integer quantity);
    Map<Integer, String> uploadProduct(MultipartFile file);
}
