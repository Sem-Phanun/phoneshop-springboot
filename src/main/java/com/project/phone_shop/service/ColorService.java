package com.project.phone_shop.service;

import com.project.phone_shop.entities.Color;

public interface ColorService {
    Color create(Color color);
    Color getColorById(Long id);
}
