package com.source.dinhtv.fashionecommercecore.model;

import com.source.dinhtv.fashionecommercecore.model.datetime.SoftDeleting;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="attribute_options")
public class AttributeOption extends SoftDeleting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(name="attribute_id")
    private Integer attributeId;
    @NotBlank
    @Column(name="value")
    private String value;

//    public AttributeOption() {
//    }
//
//    public AttributeOption(Integer attributeId, String value) {
//        this.attributeId = attributeId;
//        this.value = value;
//    }
//
//    public AttributeOption(Date createdAt, Date updatedAt, Integer attributeId, String value) {
//        super(createdAt, updatedAt);
//        this.attributeId = attributeId;
//        this.value = value;
//    }

//    public AttributeOption(Date createdAt, Date updatedAt, Date deletedAt, Integer attributeId, String value) {
//        super(createdAt, updatedAt, deletedAt);
//        this.attributeId = attributeId;
//        this.value = value;
//    }
//
//    public AttributeOption(Integer id, Integer attributeId, String value) {
//        this.id = id;
//        this.attributeId = attributeId;
//        this.value = value;
//    }

//    public AttributeOption(Date createdAt, Date updatedAt, Integer id, Integer attributeId, String value) {
//        super(createdAt, updatedAt);
//        this.id = id;
//        this.attributeId = attributeId;
//        this.value = value;
//    }
//
//    public AttributeOption(Date createdAt, Date updatedAt, Date deletedAt, Integer id, Integer attributeId, String value) {
//        super(createdAt, updatedAt, deletedAt);
//        this.id = id;
//        this.attributeId = attributeId;
//        this.value = value;
//    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getAttributeId() {
//        return attributeId;
//    }
//
//    public void setAttributeId(Integer attributeId) {
//        this.attributeId = attributeId;
//    }
//
//    public String getValue() {
//        return value;
//    }
//
//    public void setValue(String value) {
//        this.value = value;
//    }
}
