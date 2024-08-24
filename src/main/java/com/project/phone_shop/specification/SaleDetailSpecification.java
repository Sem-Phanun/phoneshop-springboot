package com.project.phone_shop.specification;


import com.project.phone_shop.entities.Sale;
import com.project.phone_shop.entities.SaleDetail;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@RequiredArgsConstructor
public class SaleDetailSpecification implements Specification<SaleDetail> {
    private final SaleDetailFilter saleDetailFilter;

    @Override
    public Predicate toPredicate(Root<SaleDetail> saleDetailRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        Join<SaleDetail, Sale> sale = saleDetailRoot.join("sale");
        if (Objects.nonNull(saleDetailFilter.getStartDate())){
            cb.greaterThanOrEqualTo(sale.get("soldDate"), saleDetailFilter.getStartDate());
        }
        if (Objects.nonNull(saleDetailFilter.getEndDate())){
            cb.lessThan(sale.get("soldDate"), saleDetailFilter.getEndDate());
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
