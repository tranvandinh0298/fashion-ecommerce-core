package com.source.dinhtv.fashionecommercecore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity(name="attribute_options")
public class AttributeOption {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Attribute attribute;

    private String value;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public AttributeOption() {
    }

    public AttributeOption(Integer attributeId, String value) {
        this.attributeId = attributeId;
        this.value = value;
    }

    public AttributeOption(Integer id, Integer attributeId, String value) {
        this.id = id;
        this.attributeId = attributeId;
        this.value = value;
    }

    public AttributeOption(Integer id, Integer attributeId, String value, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.attributeId = attributeId;
        this.value = value;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
