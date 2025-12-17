package org.elDom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="building")
public class Building extends BaseEntity{

    @Column(name="address")
    private String address;

    @Column(name="floors")
    private Long floors;

    @Column(name="apartmentsCount")
    private Long apartmentsCount;

    @Column(name = "totalArea")
    private Double totalArea;

    @Column(name = "commonArea")
    private Double commonArea;


}
