package com.project.phone_shop.service;

import com.project.phone_shop.entities.Brand;
import com.project.phone_shop.exception.ResourceNotFoundException;
import com.project.phone_shop.repository.BrandRepository;
import com.project.phone_shop.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;
    private BrandServiceImpl brandService;

    @BeforeEach
    public void setUp(){
        brandService = new BrandServiceImpl(brandRepository);

    }

//    @Test
//    public void testSave(){
//        //Given
//        Brand newBrand = new Brand();
//        newBrand.setName("Apple");
//        newBrand.setId(1);
//        //When
//        when(brandRepository.save(any(Brand.class))).thenReturn(newBrand);
//        Brand brand = brandService.save(new Brand());
//        //Then
//        Assertions.assertEquals(1, brand.getId());
//        Assertions.assertEquals("Apple", brand.getName());
//
//    }
    @Test
    public void testSave(){
        //Given
        Brand newBrand = new Brand();
        newBrand.setName("Apple");
        //When
        brandService.save(newBrand);

        //Then
        verify(brandRepository, times(1)).save(newBrand);
    }

    @Test
    public void testGetByIdSuccess(){
        //Given
        Brand newBrand = new Brand();
        newBrand.setName("Apple");
        newBrand.setId(1);
        //When
        when(brandRepository.findById(1)).thenReturn(Optional.of(newBrand));
        Brand brandReturn = brandService.getBrandById(1);
        //Then
        Assertions.assertEquals(1, brandReturn.getId());
        Assertions.assertEquals("Apple", brandReturn.getName());

    }

    @Test
    public void testGetByIdThrows(){
        //Given

        //When
        when(brandRepository.findById(2)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> brandService.getBrandById(2));

    }

}
