package com.project.phone_shop.service;

import com.project.phone_shop.dto.BrandDTO;
import com.project.phone_shop.entities.Brand;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface BrandService {
    Brand save(Brand brand);
    Brand getById(Long id);
    Brand update(Long id, Brand brandUpdate);
    void removeById(Long id);
    Page<Brand> getBrands(Map<String, String> params);
    //Page<Brand> getAllBrands(Map<String, String> params);
    //List<Brand> getAllBrands();
    // List<Brand> getBrandsByName(String name);
}
