package com.source.dinhtv.fashionecommercecore.repository;

import com.source.dinhtv.fashionecommercecore.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CollectionRepository extends JpaRepository<Collection, Integer>, JpaSpecificationExecutor<Collection> {
}
