package org.elDom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="apartment")
public class Apartment extends BaseEntity{
    @Column(name="number")
    private String number;

    @Column(name="floor")
    private Long floor;

    @Column(name="area")
    private BigDecimal area;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "buildings_id", nullable = false)  // провери името в DDL-а ти
    private Building building;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resident> residents = new ArrayList<>();

    // helper methods (силно препоръчително)
    public void addResident(Resident r) {
        residents.add(r);
        r.setApartment(this);
    }

    public void removeResident(Resident r) {
        residents.remove(r);
        r.setApartment(null);
    }

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();

    public void addPayment(Payment p) {
        payments.add(p);
        p.setApartment(this);
    }

    public void removePayment(Payment p) {
        payments.remove(p);
        p.setApartment(null);
    }

}
