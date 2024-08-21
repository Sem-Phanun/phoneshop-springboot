package com.project.phone_shop.service;

import com.project.phone_shop.dto.ModelDTO;
import com.project.phone_shop.entities.Model;

import java.util.List;

public interface ModelService {
    Model save(Model model);
    List<Model> getByBrand(Long brandId);
}
