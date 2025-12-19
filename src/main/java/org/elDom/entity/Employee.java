package org.elDom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "companies_id", nullable = false) // <-- важно: точното име на колоната
    private Company company;
}
