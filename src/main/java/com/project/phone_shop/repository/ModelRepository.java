package com.project.phone_shop.repository;

import com.project.phone_shop.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer> {
}
