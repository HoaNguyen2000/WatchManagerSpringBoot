package com.company.entity;

import com.company.common.enums.TypeWatchEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "products",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "slug")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Brands brands;

    private String slug;

    @Column(name = "old_price")
    private Double oldPrice;

    private Double price;

    private String type;

    @Column(columnDefinition = "TEXT")
    private String description;

}
