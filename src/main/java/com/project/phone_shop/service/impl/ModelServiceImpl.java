package com.project.phone_shop.service.impl;


import com.project.phone_shop.entities.Model;
import com.project.phone_shop.exception.ApiException;
import com.project.phone_shop.exception.ResourceNotFoundException;
import com.project.phone_shop.repository.ModelRepository;
import com.project.phone_shop.service.ModelService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;



    @Override
    public Model save(Model model) {
        Optional<Model> modelOptional = modelRepository.findModelByName(model.getName());
        if (modelOptional.isPresent()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Model " + model.getName() + " already exists");
        }
        return modelRepository.save(model);
    }


    @Override
    public List<Model> getByBrand(Long brandId) {
        return modelRepository.findByBrandId(brandId);
    }

    @Override
    public Model getById(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model", id));
    }

    @Override
    public Page<Model> getModels(Map<String, String> params) {
        return null;
    }

}
