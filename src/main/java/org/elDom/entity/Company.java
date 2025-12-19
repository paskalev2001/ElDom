package org.elDom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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

    @OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    //@ToString.Exclude
    private List<Tax> taxes = new ArrayList<>();

    public void addTax(Tax tax) {
        taxes.add(tax);
        tax.setCompany(this);
    }

    public void removeTax(Tax tax) {
        taxes.remove(tax);
        tax.setCompany(null);
    }

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee e) {
        employees.add(e);
        e.setCompany(this);
    }

    public void removeEmployee(Employee e) {
        employees.remove(e);
        e.setCompany(null);
    }
}
