package com.project.phone_shop.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductImportDTO {

    @NotNull(message = "product id can't be null")
    private Long productId;

    @Min(value = 1, message = "import unit must be greater than 0")
    private Integer importUnit;
    @DecimalMin(value = "0.000001d", message = "import price must be greater than 0")
    private BigDecimal importPrice;

    @NotNull(message = "import date can't be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime importDate;
}
