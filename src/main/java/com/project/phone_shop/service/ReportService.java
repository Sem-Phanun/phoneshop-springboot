package com.project.phone_shop.service;

import com.project.phone_shop.projection.ProductSold;


import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
}
