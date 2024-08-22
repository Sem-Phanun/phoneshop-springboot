package com.project.phone_shop.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "sale_details")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_detail_id")
    private Long saleDetailId;

    @ManyToOne
    @JoinColumn(name = "sale_sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "sold_amout")
    private BigDecimal amount;

    @Column(name = "quantity")
    private Integer unit;
}
