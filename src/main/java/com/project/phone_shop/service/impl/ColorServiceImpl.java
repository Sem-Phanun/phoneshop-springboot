package com.project.phone_shop.service.impl;

import com.project.phone_shop.entities.Color;
import com.project.phone_shop.exception.ResourceNotFoundException;
import com.project.phone_shop.repository.ColorRepository;
import com.project.phone_shop.service.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;
    @Override
    public Color create(Color color) {
        if (color == null) {
            throw new RuntimeException("Invalid input");
        }
        return colorRepository.save(color);
    }

    @Override
    public Color getColorById(Long id) {
        return colorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No color found with id: ", id));
    }
}
