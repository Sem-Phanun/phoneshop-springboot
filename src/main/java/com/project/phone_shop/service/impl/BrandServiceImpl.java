package com.project.phone_shop.service.impl;

import com.project.phone_shop.entities.Brand;
import com.project.phone_shop.exception.ApiException;
import com.project.phone_shop.exception.ResourceNotFoundException;
import com.project.phone_shop.repository.BrandRepository;
import com.project.phone_shop.repository.ModelRepository;
import com.project.phone_shop.service.BrandService;
import com.project.phone_shop.service.util.PageUtil;
import com.project.phone_shop.specification.BrandFilter;
import com.project.phone_shop.specification.BrandSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {


    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;


    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }


    @Override
    public Brand getById(Long brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Brand not found", brandId));
    }

//    @Override
//    public List<Brand> getAllBrands() {
//        return brandRepository.findAll();
//    }

//    @Override
//    public List<Brand> getBrandsByName(String name) {
//        return brandRepository.findByNameIsLikeIgnoreCase("%" + name + "%");
//    }

    @Override
    public Brand update(Long brandId, Brand brandUpdate) {
        Brand brand = getById(getById(brandId).getId());
        brand.setName(brandUpdate.getName()); //TODO Improve update
        return brandRepository.save(brand);
    }


    @Override
    public void removeById(Long id) {
        Brand brand = getById(id);
        brandRepository.findById(brand.getId());
        if (modelRepository.existsById(brand.getId())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Brand with id = %d ".formatted(id) + " already has associated models can not be removed");
        }
        brandRepository.delete(brand);
    }



// Filtering
//    @Override
//    public List<Brand> getBrands(Map<String, String> params) {
//        BrandFilter brandFilter = new BrandFilter();
//        if (params.containsKey("id")) {
//            String id = params.get("id");
//            brandFilter.setId(Integer.parseInt(params.get("id")));
//        }
//        if (params.containsKey("name")) {
//            String name = params.get("name");
//            brandFilter.setName(name);
//        }
//
//        int pageLimit = 1;
//        if (params.containsKey(PageUtil.PAGE_LIME)) {
//            pageLimit = Integer.parseInt(params.get("pageLimit"));
//        }
//
//        int pageNumber = 1;
//        if (params.containsKey(PageUtil.PAGE_NUMBER)) {
//            pageNumber = Integer.parseInt(params.get("pageNumber"));
//        }
//
//        BrandSpecification brandSpecification = new BrandSpecification(brandFilter);
//        Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);
//        return brandRepository.findAll(brandSpecification);
//    }

    @Override
    public Page<Brand> getBrands(Map<String, String> params) {
        BrandFilter brandFilter = new BrandFilter();
        if (params.containsKey("id")) {
            String id = params.get("id");
            brandFilter.setId(Integer.parseInt(params.get("id")));
        }
        if (params.containsKey("name")) {
            String name = params.get("name");
            brandFilter.setName(name);
        }

        //Todo add to function
        int pageSize = PageUtil.DEFAULT_PAGE_LIMIT;
        if (params.containsKey(PageUtil.PAGE_LIME)) {
            pageSize = Integer.parseInt(params.get(PageUtil.PAGE_LIME));
        }

        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if (params.containsKey(PageUtil.PAGE_NUMBER)) {
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }

        BrandSpecification brandSpecification = new BrandSpecification(brandFilter);
        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize);
        Page<Brand> page;
        page = brandRepository.findAll(brandSpecification, pageable);

        return page;
    }


}