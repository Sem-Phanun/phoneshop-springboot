package com.project.phone_shop.service.impl;

import com.project.phone_shop.dto.ModelDTO;
import com.project.phone_shop.entities.Model;
import com.project.phone_shop.mapper.ModelMapper;
import com.project.phone_shop.repository.ModelRepository;
import com.project.phone_shop.service.ModelService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    private final ModelMapper modelMapper;

    @Override
    public Model save(ModelDTO modelDTO) {
        Model model = modelMapper.toModel(modelDTO);
        return modelRepository.save(model);
    }
}
