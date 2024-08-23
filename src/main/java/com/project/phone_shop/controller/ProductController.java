package com.project.phone_shop.controller;

import com.project.phone_shop.dto.PriceDTO;
import com.project.phone_shop.dto.ProductDTO;
import com.project.phone_shop.dto.ProductImportDTO;
import com.project.phone_shop.entities.Product;
import com.project.phone_shop.mapper.ProductMapper;
import com.project.phone_shop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        product = productService.create(product);
        return ResponseEntity.ok(product);
    }

    @PostMapping("importProduct")
    public ResponseEntity<?> importProduct(@RequestBody @Valid ProductImportDTO productImportDTO) {
        productService.importProduct(productImportDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{productId}/setSalePrice")
    public ResponseEntity<?> setSalePrice(@PathVariable Long productId, @RequestBody PriceDTO priceDTO) {
        productService.setSalePrice(productId, priceDTO.getPrice());
        return ResponseEntity.ok().build();
    }

    @PostMapping("uploadProduct")
    public ResponseEntity<?> uploadProductFromExcel(@RequestParam("file") MultipartFile file) {
        Map<Integer, String> errorMap = productService.uploadProduct(file);
        return ResponseEntity.ok(errorMap);
    }
}
