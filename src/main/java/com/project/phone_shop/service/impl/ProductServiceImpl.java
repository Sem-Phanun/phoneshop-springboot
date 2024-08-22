package com.project.phone_shop.service.impl;

import com.project.phone_shop.dto.ProductImportDTO;
import com.project.phone_shop.entities.Product;
import com.project.phone_shop.entities.ProductImportHistory;
import com.project.phone_shop.exception.ApiException;
import com.project.phone_shop.exception.ResourceNotFoundException;
import com.project.phone_shop.mapper.ProductMapper;
import com.project.phone_shop.repository.ProductImportHistoryRepository;
import com.project.phone_shop.repository.ProductRepository;
import com.project.phone_shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Product create(Product product) {
        String name;
        name = "%s  %s"
                .formatted(product.getModel().getName(),product.getColor().getColorName());
        product.setProductName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product",id));
    }

    @Override
    public void importProduct(ProductImportDTO importDTO) {
        //Update available product unit
        if (importDTO.getImportUnit() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Product unit can't be null");
        }
        Product product = getProductById(importDTO.getProductId());
        Integer availableUnit = 0;
        if (product.getAvailableUnit() != null) {
            availableUnit = product.getAvailableUnit();
        }

        product.setAvailableUnit(availableUnit + importDTO.getImportUnit());
        productRepository.save(product);

        //Save product import history
        ProductImportHistory productImportHistory = productMapper.toProductImportHistory(importDTO, product);
        productImportHistoryRepository.save(productImportHistory);
    }

    @Override
    public void setSalePrice(Long productId, BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Price can't be less than zero");
        }
        Product product = getProductById(productId);
        product.setSalePrice(price);
        productRepository.save(product);
    }

    @Override
    public void validateStock(Long productId, Integer quantity) {

    }


}
