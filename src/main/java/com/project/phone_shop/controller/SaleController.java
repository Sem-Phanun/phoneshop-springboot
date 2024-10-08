package com.project.phone_shop.controller;

import com.project.phone_shop.dto.SaleDTO;

import com.project.phone_shop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("sales")
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SaleDTO saleDTO) {
        saleService.sell(saleDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{saleId}/cancel")
    public ResponseEntity<?> cancelSale(@PathVariable Long saleId) {
        saleService.cancelSale(saleId);
        return ResponseEntity.ok().build();
    }
}
