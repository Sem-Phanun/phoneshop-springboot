package com.project.phone_shop.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.phone_shop.projection.ProductSold;
import com.project.phone_shop.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("reports")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("{startDate}/{endDate}")
    public ResponseEntity<?> productSold(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate) {
        List<ProductSold> productSold = reportService.getProductSold(startDate, endDate);
        return ResponseEntity.ok(productSold);
    }
}
