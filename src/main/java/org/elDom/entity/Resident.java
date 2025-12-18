package org.elDom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "resident")
public class Resident extends BaseEntity{
    @Column(nullable=false, length=100)
    private String firstName;

    @Column(nullable=false, length=100)
    private String lastName;

    @Column(length=10)
    private String egn;

    @Column(nullable=false)
    private LocalDate birthDate;

    @Column(nullable=false)
    private boolean usesElevator;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private ResidentRole role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "apartments_id", nullable = false) // провери името в DDL-а ти
    private Apartment apartment;
}
