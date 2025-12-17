package org.elDom.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="tax")
public class Tax extends BaseEntity{
    @Column(name="baseRatePerSqM")
    private Double baseRatePerSqM;

    @Column(name="extraPerPersonElevator")
    private Double extraPerPersonElevator;

    @Column(name="extraPerPet")
    private Double extraPerPet;

    @Column(name="validFrom")
    private String validFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
