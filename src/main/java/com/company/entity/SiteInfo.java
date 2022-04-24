package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "site_info")
public class SiteInfo extends BaseEntity {
    @Id
    private Long id;

    private String author;

    private String phone;

    private String email;

    private String address;

    @Column(name = "logo_link")
    private String logoLink;
}
