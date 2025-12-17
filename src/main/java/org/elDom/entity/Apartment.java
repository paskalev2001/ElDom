package org.elDom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="apartment")
public class Apartment extends BaseEntity{
    @Column(name="number")
    private String number;

    @Column(name="floor")
    private Long floor;

    @Column(name="area")
    private Double area;
}
