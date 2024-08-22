package com.project.phone_shop.service.impl;

import com.project.phone_shop.dto.ProductSoldDTO;
import com.project.phone_shop.dto.SaleDTO;
import com.project.phone_shop.entities.Product;
import com.project.phone_shop.entities.Sale;
import com.project.phone_shop.entities.SaleDetail;
import com.project.phone_shop.exception.ApiException;
import com.project.phone_shop.repository.ProductRepository;
import com.project.phone_shop.repository.SaleDetailRepository;
import com.project.phone_shop.repository.SaleRepository;
import com.project.phone_shop.service.ProductService;
import com.project.phone_shop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;



    @Override
    public void sell(SaleDTO saleDTO) {
        //Validation
        validate(saleDTO);


        //Sale
        saveSale(saleDTO);

        //Sale Details


    }

    private void saveSale(SaleDTO saleDTO) {
        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSaleDate());
        saleRepository.save(sale);

        //Sale Details

        saleDTO.getProducts().forEach(saleDetails -> {
            Product product = productService.getProductById(saleDetails.getProductId());
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setAmount(product.getSalePrice());
            saleDetail.setProduct(product);
            saleDetail.setSale(sale);
            saleDetail.setUnit(saleDetails.getProductQuantity());
            saleDetailRepository.save(saleDetail);

            //Cut Stock
            Integer availableUnit = product.getAvailableUnit() - saleDetail.getUnit();
            product.setAvailableUnit(availableUnit);
            productRepository.save(product);
        });
    }


/*  private void validateV2(SaleDTO saleDTO) {
//
//        saleDTO.getProducts().forEach(productSale -> {
//            Product product1 = productService.getProductById(productSale.getProductId());
//            if(product.getAvailableUnit() < productSale.getProductQuantity()){
//                throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] out of stock".formatted(product.getProductName()));
//            }
//        });
//    }*/


    private void validate(SaleDTO saleDTO) {

        List<Long> productIds = saleDTO.getProducts()
                .stream()
                .map(ProductSoldDTO::getProductId)
                .toList();
        //Validate Products
        productIds.forEach(productService::getProductById);

        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getProductId, Function.identity()));

        //Validate stock
        saleDTO.getProducts()
                .forEach(productSale -> {
                    Product product = productMap.get(productSale.getProductId());
                    if (product.getAvailableUnit() < productSale.getProductQuantity()) {
                        throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] out of stock".formatted(product.getProductName()));
                    }
                });
    }
}
