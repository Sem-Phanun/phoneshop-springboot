package com.project.phone_shop.service;

import com.project.phone_shop.entities.Brand;

import java.util.List;

public interface BrandService {
    Brand save(Brand brand);
    Brand getBrandById(Integer id);
    List<Brand> getAllBrands();
    Brand update(Integer id,Brand brandUpdate);
    Brand delete(Integer id);
}
