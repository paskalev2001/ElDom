package org.elDom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="employee")
public class Employee extends BaseEntity {
    @Column(name="firstName")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    @Column(name="egn")
    private String egn;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="hireDate")
    private String hireDate;

    @Column(name="active")
    private Boolean active;
}
