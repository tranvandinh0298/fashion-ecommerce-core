package com.source.dinhtv.fashionecommercecore.model.datetime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@MappedSuperclass
public class SoftDeleting extends Timestamps {
    @Column(name="deleted_at")
    protected Date deletedAt;

//    public SoftDeleting() {
//        super();
//    }
//
//    public SoftDeleting(Date createdAt, Date updatedAt) {
//        super(createdAt, updatedAt);
//    }
//
//    public SoftDeleting(Date createdAt, Date updatedAt, Date deletedAt) {
//        super(createdAt, updatedAt);
//        this.deletedAt = deletedAt;
//    }
//
//    public Date getDeletedAt() {
//        return deletedAt;
//    }
//
//    public void setDeletedAt(Date deletedAt) {
//        this.deletedAt = deletedAt;
//    }
//
//    public void setDeletedAt() {this.deletedAt = new Date();}

    public void restore() {
        this.deletedAt = null;
    }
}
