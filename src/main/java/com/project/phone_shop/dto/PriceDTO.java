package com.project.phone_shop.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceDTO {
    private BigDecimal price;
}
