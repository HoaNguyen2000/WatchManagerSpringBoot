package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToOne
    private Product product;

    @Column(name = "display_type")
    private String displayType;

    @Column(name = "display_size")
    private Double displaySize;

    private String battery;

    @Column(name = "launch_announced")
    private Date launchAnnounced;
}
