package com.project.phone_shop.service;


import com.project.phone_shop.entities.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ModelService {
    Model save(Model model);
    List<Model> getByBrand(Long brandId);
    Model getById(Long id);
    Page<Model> getModels(Map<String, String> params);
}
