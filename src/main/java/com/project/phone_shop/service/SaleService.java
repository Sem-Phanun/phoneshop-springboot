package com.project.phone_shop.service;

import com.project.phone_shop.dto.SaleDTO;
import com.project.phone_shop.entities.Sale;


public interface SaleService {
    void sell(SaleDTO saleDTO);
    Sale getById(Long saleId);
    void cancelSale(Long saleId);
}
