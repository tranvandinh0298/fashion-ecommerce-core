package com.source.dinhtv.fashionecommercecore.model;

import com.source.dinhtv.fashionecommercecore.model.datetime.SoftDeleting;
import com.source.dinhtv.fashionecommercecore.utils.CustomConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image extends SoftDeleting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(name="caption")
    private String caption;
    @NotBlank
    @Column(name="address")
    private String address;
    @NotNull
    @Column(name="status")
    private Integer status;

    public Image(String caption, String address, Integer status) {
        this.caption = caption;
        this.address = address;
        this.status = status;
    }


}
