package com.project.phone_shop.controller;

import com.project.phone_shop.dto.ColorDTO;
import com.project.phone_shop.entities.Color;
import com.project.phone_shop.mapper.ColorMapper;
import com.project.phone_shop.mapper.ColorMapperImpl;
import com.project.phone_shop.service.ColorService;
import com.project.phone_shop.service.impl.ColorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("colors")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;
    private final ColorServiceImpl colorServiceImpl;

    @PostMapping
    public ResponseEntity<?> createColor(@RequestBody ColorDTO colorDTO) {
        Color color = ColorMapperImpl.INSTANCE.toColor(colorDTO);
        color = colorService.create(color);
        return ResponseEntity.ok(ColorMapperImpl.INSTANCE.toColorDTO(color));
    }
}
