package com.source.dinhtv.fashionecommercecore.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.CATEGORY_TYPE;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.COLLECTION_TYPE;

@Entity
@DiscriminatorValue(COLLECTION_TYPE)
public class Collection extends Category {
}
