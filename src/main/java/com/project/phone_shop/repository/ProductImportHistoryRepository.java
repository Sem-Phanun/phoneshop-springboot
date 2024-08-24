package com.project.phone_shop.repository;

import com.project.phone_shop.entities.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Long>, JpaSpecificationExecutor<ProductImportHistory> {

}
