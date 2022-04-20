package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "specification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Specification extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "product_id")
    private long productId;
    @Column(name = "display_type")
    private String displayType;

    @Column(name = "display_size")
    private Double displaySize;

    private String battery;

    @Column(name = "launch_announced")
    private Date launchAnnounced;
}
