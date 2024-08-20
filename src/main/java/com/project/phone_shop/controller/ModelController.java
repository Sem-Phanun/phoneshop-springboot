package com.project.phone_shop.controller;

import com.project.phone_shop.dto.ModelDTO;
import com.project.phone_shop.entities.Model;
import com.project.phone_shop.mapper.ModelMapper;
import com.project.phone_shop.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/models")
public class ModelController {

    private final ModelService modelService;


    @PostMapping
    public ResponseEntity<?> createModel(@RequestBody ModelDTO modelDTO) {

        Model model = modelService.save(modelDTO);
        return ResponseEntity.ok(model);
    }
}
