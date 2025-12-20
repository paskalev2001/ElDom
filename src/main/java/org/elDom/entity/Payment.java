package org.elDom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name="payment",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_payment_apartment_period", columnNames = {"apartments_id", "periodYear", "periodMonth"})
        }
)
public class Payment extends BaseEntity{
    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "paymentDate")
    private LocalDate paymentDate;

    @Column(name = "periodMonth")
    private Integer periodMonth;

    @Column(name = "periodYear")
    private Integer periodYear;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "apartments_id", nullable = false)
    private Apartment apartment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "buildings_id", nullable = false)   // или building_id според БД
    private Building building;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "companies_id", nullable = false) // или company_id според БД
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employees_id", nullable = false) // или employee_id според БД
    private Employee employee;

}
