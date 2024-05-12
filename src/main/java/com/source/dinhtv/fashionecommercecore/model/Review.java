package com.source.dinhtv.fashionecommercecore.model;

import com.source.dinhtv.fashionecommercecore.model.datetime.SoftDeleting;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_reviews")
public class Review extends SoftDeleting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @NotNull
    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

    @NotNull
    @Column(name="rate")
    private Integer rate;

    @NotBlank
    @Column(name="review")
    private String review;

    @NotNull
    @Column(name="status")
    private String status;
}
