package org.elDom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="payment")
public class Payment extends BaseEntity{
    @Column(name = "amount")
    private Double amount;

    @Column(name = "paymentDate")
    private String paymentDate;

    @Column(name = "periodMonth")
    private String periodMonth;

    @Column(name = "periodYear")
    private String periodYear;
}
