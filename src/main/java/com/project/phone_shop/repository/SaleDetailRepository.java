package com.project.phone_shop.repository;

import com.project.phone_shop.entities.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {
    @Query("SELECT sd FROM SaleDetail sd WHERE sd.sale.saleId = :saleId")
    List<SaleDetail> findBySaleId(Long saleId);
}
