package com.source.dinhtv.fashionecommercecore.model.datetime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@MappedSuperclass
public class Timestamps {
    @CreationTimestamp
    @Column(name="created_at", updatable = false)
    protected Date createdAt;
    @Column(name="updated_at", updatable = true)
    @UpdateTimestamp
    protected Date updatedAt;
}
