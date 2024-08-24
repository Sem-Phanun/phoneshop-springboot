package com.project.phone_shop.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.phone_shop.dto.ProductReportDTO;
import com.project.phone_shop.dto.report.ExpenseReportDTO;
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
        List<ProductReportDTO> productSold = reportService.getProductReport(startDate, endDate);
        return ResponseEntity.ok(productSold);
    }

    @GetMapping("v2/{startDate}/{endDate}")
    public ResponseEntity<?> productSoldV2(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate) {
        List<ProductSold> productSold = reportService.getProductSold(startDate, endDate);
        return ResponseEntity.ok(productSold);
    }

    @GetMapping("expense/{startDate}/{endDate}")
    public ResponseEntity<?> expenseReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate){
        List<ExpenseReportDTO> expenseReports = reportService.getExpenseReport(startDate, endDate);
        return ResponseEntity.ok(expenseReports);
    }
}
