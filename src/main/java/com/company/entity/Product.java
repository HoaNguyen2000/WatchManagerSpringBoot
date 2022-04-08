package com.company.entity;

import com.company.common.enums.TypeWatchEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "watches",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "slug")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "brand_id")
    private Long brandId;

    private String slug;

    @Column(name = "old_price")
    private Double oldPrice;

    private Double price;

    private TypeWatchEnum type;

    @Column(columnDefinition = "TEXT")
    private String description;

}
