package com.project.phone_shop.service.impl;

import com.project.phone_shop.entities.Brand;
import com.project.phone_shop.exception.ResourceNotFoundException;
import com.project.phone_shop.repository.BrandRepository;
import com.project.phone_shop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;
    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand getBrandById(Integer id) {
//        Optional<Brand> brandOptional = brandRepository.findById(id);
//        if (brandOptional.isPresent()) {
//            return brandOptional.get();
//        }
////        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Brand not found");
//        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Brand with Id = %d not found"));
        return brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found",id));
                //.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("Brand with Id = %d not found", id)));
                //.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Brand with Id = %d not found", id)));
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand update(Integer id, Brand brandUpdate) {
        Brand brand = getBrandById(id);
        brand.setName(brandUpdate.getName()); //TODO Improve update
        return brandRepository.save(brand);
    }


    @Override
    public Brand delete(Integer id) {
        Brand brand = getBrandById(id);
        brandRepository.delete(brand);
        return brand;
    }
}
