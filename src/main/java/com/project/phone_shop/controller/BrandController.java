package com.project.phone_shop.controller;

import com.project.phone_shop.dto.BrandDTO;
import com.project.phone_shop.entities.Brand;
import com.project.phone_shop.mapper.BrandMapperImpl;
import com.project.phone_shop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("brands")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createBrand(@RequestBody BrandDTO brandDTO) {
//        Brand brand = Mapper.toBrand(brandDTO);
        Brand brand = BrandMapperImpl.INSTANCE.toBrand(brandDTO);
        brand = brandService.save(brand);
        return ResponseEntity.ok(BrandMapperImpl.INSTANCE.toBrandDTO(brand));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOneBrand(@PathVariable("id") Integer brandId) {
        Brand brand = brandService.getBrandById(brandId);
        return ResponseEntity.ok(BrandMapperImpl.INSTANCE.toBrandDTO(brand));
    }
    @GetMapping
    public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params) {
        List<BrandDTO> list = brandService.getBrands(params)
                .stream()
                .map(BrandMapperImpl.INSTANCE::toBrandDTO)
                .toList();
        return ResponseEntity.ok(list);
    }


    @PutMapping("{id}")
    public ResponseEntity<?> updateBrand(@PathVariable("id") Integer brandId, @RequestBody BrandDTO brandDTO) {
//        Brand brand = Mapper.toBrand(brandDTO);
        Brand brand = BrandMapperImpl.INSTANCE.toBrand(brandDTO);
        Brand brandUpdated = brandService.update(brandId, brand);
        return ResponseEntity.ok(BrandMapperImpl.INSTANCE.toBrandDTO(brandUpdated));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") Integer brandId) {
        Brand brand = BrandMapperImpl.INSTANCE.toBrand(new BrandDTO());
        Brand brandDelete = brandService.delete(brandId);
        return ResponseEntity.ok(BrandMapperImpl.INSTANCE.toBrandDTO(brandDelete));
    }
}
