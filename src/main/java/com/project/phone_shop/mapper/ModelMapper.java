package com.project.phone_shop.mapper;

import com.project.phone_shop.dto.ModelDTO;
import com.project.phone_shop.entities.Brand;
import com.project.phone_shop.entities.Model;
import com.project.phone_shop.service.BrandService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BrandService.class})
public interface ModelMapper {
    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    @Mapping(target = "brand", source = "brandId")
    Model toModel(ModelDTO dto);

    @Mapping(target = "brandId", source = "brand.id")
    ModelDTO toModelDTO(Model model);

}
