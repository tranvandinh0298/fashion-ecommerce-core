package com.source.dinhtv.fashionecommercecore.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.CATEGORY_TYPE;

@Entity
@DiscriminatorValue(CATEGORY_TYPE)
public class RegularCategory extends Category{
}
