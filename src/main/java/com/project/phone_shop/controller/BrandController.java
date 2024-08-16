package com.project.phone_shop.controller;

import com.project.phone_shop.dto.BrandDTO;
import com.project.phone_shop.dto.PageDTO;
import com.project.phone_shop.entities.Brand;
import com.project.phone_shop.mapper.BrandMapperImpl;
import com.project.phone_shop.service.BrandService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

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

    @PutMapping("{id}")
    public ResponseEntity<?> updateBrand(@PathVariable("id") Integer brandId, @RequestBody BrandDTO brandDTO) {
//        Brand brand = Mapper.toBrand(brandDTO);
        Brand brand = BrandMapperImpl.INSTANCE.toBrand(brandDTO);
        Brand brandUpdated = brandService.update(brandId, brand);
        return ResponseEntity.ok(BrandMapperImpl.INSTANCE.toBrandDTO(brandUpdated));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") Integer brandId) {
        BrandMapperImpl.INSTANCE.toBrand(new BrandDTO());
        Brand brandDelete = brandService.delete(brandId);
        return ResponseEntity.ok(BrandMapperImpl.INSTANCE.toBrandDTO(brandDelete));
    }

    @GetMapping
    public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params) {
        Page<Brand> brands = brandService.getBrands(params);

        /*List<BrandDTO> list = brandService.getBrands(params)
              .stream()
              .map(BrandMapperImpl.INSTANCE::toBrandDTO)
                .toList(); */
        return ResponseEntity.ok(brands);
    }

}
