package com.project.phone_shop.specification;

import com.project.phone_shop.entities.Brand;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class BrandSpecification implements Specification<Brand> {

    private final BrandFilter brandFilter;

    List<Predicate> predicates = new ArrayList<>();


    @Override
    public Predicate toPredicate(Root brand, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        if (brandFilter.getId() != null) {
            Predicate id = brand.get("id").in(brandFilter.getId());
            predicates.add(id);
        }
        if (brandFilter.getName() != null) {
//            Predicate name = brand.get("name").in(brandFilter.getName());
//            predicates.add(name);
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(brand.get("name")), "%" + brandFilter.getName().toUpperCase() + "%"));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
