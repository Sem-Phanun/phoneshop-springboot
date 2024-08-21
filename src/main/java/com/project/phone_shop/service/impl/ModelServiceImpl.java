package com.project.phone_shop.service.impl;


import com.project.phone_shop.entities.Model;
import com.project.phone_shop.repository.ModelRepository;
import com.project.phone_shop.service.ModelService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;



    @Override
    public Model save(Model model) {
        return modelRepository.save(model);
    }


    @Override
    public List<Model> getByBrand(Long brandId) {
        return modelRepository.findByBrandId(brandId);
    }

}
