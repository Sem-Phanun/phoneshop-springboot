package com.project.phone_shop.controller;

import com.project.phone_shop.dto.BrandDTO;
import com.project.phone_shop.dto.ModelDTO;
import com.project.phone_shop.dto.PageDTO;
import com.project.phone_shop.entities.Brand;
import com.project.phone_shop.entities.Model;
import com.project.phone_shop.mapper.BrandMapper;
import com.project.phone_shop.mapper.BrandMapperImpl;
import com.project.phone_shop.mapper.ModelEntityMapper;
import com.project.phone_shop.service.BrandService;
import com.project.phone_shop.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "brands")
@AllArgsConstructor
public class BrandController {
    private final BrandService brandService;
    private final ModelService modelService;
    private final ModelEntityMapper modelEntityMapper;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createBrand(@RequestBody BrandDTO brandDTO) {
//        Brand brand = Mapper.toBrand(brandDTO);
        Brand brand = BrandMapperImpl.INSTANCE.toBrand(brandDTO);
        brand = brandService.save(brand);
        return ResponseEntity.ok(BrandMapperImpl.INSTANCE.toBrandDTO(brand));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOneBrand(@PathVariable("id") Long brandId) {
        Brand brand = brandService.getById(brandId);
        return ResponseEntity.ok(BrandMapperImpl.INSTANCE.toBrandDTO(brand));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBrand(@PathVariable("id") Long brandId, @RequestBody BrandDTO brandDTO) {
//        Brand brand = Mapper.toBrand(brandDTO);
        Brand brand = BrandMapperImpl.INSTANCE.toBrand(brandDTO);
        Brand brandUpdated = brandService.update(brandId, brand);
        return ResponseEntity.ok(BrandMapperImpl.INSTANCE.toBrandDTO(brandUpdated));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") Long brandId) {
//        BrandMapperImpl.INSTANCE.toBrand(new BrandDTO());
        brandService.removeById(brandId);
//        return ResponseEntity.ok(BrandMapperImpl.INSTANCE.toBrandDTO(brandDelete));
        return ResponseEntity.ok("brandId: " + brandId);
    }

    @GetMapping
    public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params) {
//        Page<Brand> brands = brandService.getBrands(params);
//        PageDTO pageDTO = new PageDTO(brands);
        List<Brand> list = brandService.getBrands(params)
                .stream()
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}/models")
    public ResponseEntity<?> getModelByBrand(@PathVariable("id") Long brandId) {
        List<Model> brands = modelService.getByBrand(brandId);
        List<ModelDTO> collect = brands.stream().map(modelEntityMapper::toModelDTO).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

}
