package com.source.dinhtv.fashionecommercecore.model.datetime;

import com.source.dinhtv.fashionecommercecore.utils.CustomConstants;
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

    public void softDelete() {
        setDeletedAt(new Date());
    }

    public void restore() {
        setDeletedAt(null);
    }
}
