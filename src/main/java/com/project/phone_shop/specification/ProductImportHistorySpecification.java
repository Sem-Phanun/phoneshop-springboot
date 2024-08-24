package com.project.phone_shop.specification;

import com.project.phone_shop.entities.ProductImportHistory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class ProductImportHistorySpecification implements Specification<ProductImportHistory> {
    private final ProductImportHistoryFilter filter;
    @Override
    public Predicate toPredicate(Root<ProductImportHistory> importHistoryRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(filter.getStartDate())){
            cb.greaterThanOrEqualTo(importHistoryRoot.get("dateImport"), filter.getStartDate());
        }
        if (Objects.nonNull(filter.getEndDate())){
            cb.lessThanOrEqualTo(importHistoryRoot.get("dateImport"), filter.getEndDate());
        }
        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
