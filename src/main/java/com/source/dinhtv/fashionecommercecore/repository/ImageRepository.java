package com.source.dinhtv.fashionecommercecore.repository;

import com.source.dinhtv.fashionecommercecore.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {


}
