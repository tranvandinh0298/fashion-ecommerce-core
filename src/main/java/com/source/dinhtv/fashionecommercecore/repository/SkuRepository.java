package com.source.dinhtv.fashionecommercecore.repository;


import com.source.dinhtv.fashionecommercecore.model.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuRepository extends JpaRepository<Sku, Integer>, JpaSpecificationExecutor<Sku> {
}
