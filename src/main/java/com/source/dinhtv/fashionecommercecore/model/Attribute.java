package com.source.dinhtv.fashionecommercecore.model;

import com.source.dinhtv.fashionecommercecore.model.datetime.SoftDeleting;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name="attributes")
public class Attribute extends SoftDeleting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min=1,message = "Tên thuộc tính phải lớn 1 ký tự")
    @Column(name="name")
    private String name;

//    public Attribute() {
//    }
//
//    public Attribute(Integer id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Attribute(String name) {
//        this.name = name;
//    }
//
//    public Attribute(Date createdAt, Date updatedAt, String name) {
//        super(createdAt, updatedAt);
//        this.name = name;
//    }
//
//    public Attribute(Date createdAt, Date updatedAt, Integer id, String name) {
//        super(createdAt, updatedAt);
//        this.id = id;
//        this.name = name;
//    }

//    public Attribute(Date createdAt, Date updatedAt, Date deletedAt, String name) {
//        super(createdAt, updatedAt, deletedAt);
//        this.name = name;
//    }
//
//    public Attribute(Date createdAt, Date updatedAt, Date deletedAt, Integer id, String name) {
//        super(createdAt, updatedAt, deletedAt);
//        this.id = id;
//        this.name = name;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
