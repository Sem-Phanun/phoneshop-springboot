package com.project.phone_shop.mapper;

import com.project.phone_shop.dto.ProductDTO;
import com.project.phone_shop.dto.ProductImportDTO;
import com.project.phone_shop.entities.Product;
import com.project.phone_shop.entities.ProductImportHistory;
import com.project.phone_shop.service.ColorService;
import com.project.phone_shop.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {ModelService.class, ColorService.class})
public interface ProductMapper {
    @Mapping(target = "model", source = "modelId")
    @Mapping(target = "color", source = "colorId")
    Product toProduct(ProductDTO productDTO);

    @Mapping(target = "dateImport",source = "productImportDTO.importDate")
    @Mapping(target = "pricePerUnit", source = "productImportDTO.importPrice")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "importId", ignore = true)
    ProductImportHistory toProductImportHistory(ProductImportDTO productImportDTO, Product product);
}
