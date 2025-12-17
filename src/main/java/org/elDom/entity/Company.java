package org.elDom.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "eik")
    private String eik;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;
}
