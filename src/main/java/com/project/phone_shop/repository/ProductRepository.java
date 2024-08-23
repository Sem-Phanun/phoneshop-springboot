package com.project.phone_shop.repository;

import com.project.phone_shop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,  JpaSpecificationExecutor<Product> {

    //Optional<Product> findByModelIdAndColorId(Long modelId, Long colorId);
    @Query("SELECT p FROM Product p WHERE p.model.id = :modelId AND p.color.id = :colorId")
    Optional<Product> findByModelIdAndColorId(@Param("modelId") Long modelId, @Param("colorId") Long colorId);
}
