package com.project.phone_shop.mapper;


import com.project.phone_shop.dto.ColorDTO;
import com.project.phone_shop.entities.Color;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ColorMapper {
    ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);
    Color toColor(ColorDTO colorDTO);
    ColorDTO toColorDTO(Color color);
}
