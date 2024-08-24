package com.project.phone_shop.repository;

import com.project.phone_shop.entities.Sale;
import com.project.phone_shop.projection.ProductSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "select p.product_id as productId, p.product_name as productName, sum(sd.quantity) unit, sum(sd.quantity * sd.sold_amount) totalAmount from sale_details sd\n" +
            "inner join sales s on sd.sale_id = s.sale_id \n" +
            "inner join products p on p.product_id = sd.product_id\n" +
            "where date(s.sold_date) >= :startDate and date(s.sold_date) <= :endDate\n" +
            "group by p.product_id, p.product_name", nativeQuery = true)
    List<ProductSold> findProductSold(LocalDate startDate, LocalDate endDate);
}
