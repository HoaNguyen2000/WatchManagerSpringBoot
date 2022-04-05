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
public class Specification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "display_type")
    private String displayType;

    @Column(name = "display_size")
    private Double displaySize;

    @Column(name = "display_resolution")
    private String displayResolution;

    @Column(name = "display_dimension")
    private String displayDimension;

    private String battery;

    private String color;

    @Column(name = "launch_announced")
    private Date launchAnnounced;
}
