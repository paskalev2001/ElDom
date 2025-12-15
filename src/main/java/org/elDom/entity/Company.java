package org.elDom.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company extends BaseEntity {
    @Column(name = "name")
    private String name;
}
