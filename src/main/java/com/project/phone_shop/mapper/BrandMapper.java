package com.project.phone_shop.mapper;

import com.project.phone_shop.dto.BrandDTO;
import com.project.phone_shop.entities.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    Brand toBrand(BrandDTO brandDTO);

    BrandDTO toBrandDTO(Brand entity);
}
