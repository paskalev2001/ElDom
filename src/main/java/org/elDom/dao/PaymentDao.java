package org.elDom.dao;

import org.elDom.entity.Payment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PaymentDao extends GenericDao<Payment, Long> {
    Optional<Payment> findByApartmentAndPeriod(Long apartmentId, int year, int month);
    List<Payment> findByBuilding(Long buildingId, int year, int month);
    BigDecimal sumByCompany(Long companyId, int year, int month);
    BigDecimal sumByEmployee(Long employeeId, int year, int month);
}