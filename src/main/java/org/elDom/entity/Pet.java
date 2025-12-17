package org.elDom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="pet")
public class Pet extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name="type")
    private String type;

    @Column(name = "usesCommonParts")
    private Boolean usesCommonParts;
}
