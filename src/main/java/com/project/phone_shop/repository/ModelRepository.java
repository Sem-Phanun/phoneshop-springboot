package com.project.phone_shop.repository;

import com.project.phone_shop.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByBrandId(Long brandId);
    Optional<Model> findModelByName(String name);
}
