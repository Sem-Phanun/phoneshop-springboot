package com.project.phone_shop.service;

import com.project.phone_shop.entities.Brand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface BrandService {
    Brand save(Brand brand);
    Brand getBrandById(Integer id);
    Brand update(Integer id,Brand brandUpdate);
    Brand delete(Integer id);
    Page<Brand> getBrands(Map<String, String> params);
    //Page<Brand> getAllBrands(Map<String, String> params);
    //List<Brand> getAllBrands();
    // List<Brand> getBrandsByName(String name);
}
