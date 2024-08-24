package com.project.phone_shop.controller;

import com.project.phone_shop.dto.ModelDTO;
import com.project.phone_shop.entities.Model;
import com.project.phone_shop.mapper.ModelEntityMapper;
import com.project.phone_shop.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("models")
@AllArgsConstructor
public class ModelController {

    private final ModelService modelService;
    private final ModelEntityMapper modelEntityMapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createModel(@RequestBody ModelDTO modelDTO) {
        Model model = modelEntityMapper.toModel(modelDTO);
        model = modelService.save(model);
        return ResponseEntity.ok(ModelEntityMapper.INSTANCE.toModelDTO(model));
    }

    public ResponseEntity<?> getModels(@RequestBody ModelDTO modelDTO) {
        return ResponseEntity.ok().build();
    }
}
