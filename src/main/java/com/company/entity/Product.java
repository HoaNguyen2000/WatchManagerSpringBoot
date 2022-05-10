package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "products",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "slug")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "image_link")
    private String imageLink;

    @ManyToOne
    private Brands brands;

    private String slug;

    @Column(name = "old_price")
    private Double oldPrice;

    private Double price;

    private String type;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Product(String name,
                   Brands brands,
                   String slug,
                   Double oldPrice,
                   Double price,
                   String type,
                   String description,
                   String imageLink) {
        this.name = name;
        this.brands = brands;
        this.slug = slug;
        this.oldPrice = oldPrice;
        this.price = price;
        this.type = type;
        this.description = description;
        this.imageLink = imageLink;
    }
}
