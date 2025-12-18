package org.elDom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Apartment> apartments = new ArrayList<>();

    public void addApartment(Apartment a) {
        apartments.add(a);
        a.setBuilding(this);
    }

    public void removeApartment(Apartment a) {
        apartments.remove(a);
        a.setBuilding(null);
    }
}
