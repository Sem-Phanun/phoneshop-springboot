package com.project.phone_shop.specification;

import lombok.Data;

import java.time.LocalDate;
@Data
public class SaleDetailFilter {
    private LocalDate startDate;
    private LocalDate endDate;
}
