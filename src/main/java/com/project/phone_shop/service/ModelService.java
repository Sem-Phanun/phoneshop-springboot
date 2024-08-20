package com.project.phone_shop.service;

import com.project.phone_shop.dto.ModelDTO;
import com.project.phone_shop.entities.Model;

public interface ModelService {
    Model save(ModelDTO dto);
}
