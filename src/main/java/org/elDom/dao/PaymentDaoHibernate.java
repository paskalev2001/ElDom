package org.elDom.dao;

import org.elDom.entity.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PaymentDaoHibernate extends GenericDaoHibernate<Payment, Long> implements PaymentDao {

    public PaymentDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory, Payment.class);
    }

    @Override
    public Optional<Payment> findByApartmentAndPeriod(Long apartmentId, int year, int month) {
        try (Session session = sessionFactory.openSession()) {
            Payment p = session.createQuery(
                            "select p from Payment p " +
                                    "where p.apartment.id = :apId and p.periodYear = :y and p.periodMonth = :m",
                            Payment.class)
                    .setParameter("apId", apartmentId)
                    .setParameter("y", year)
                    .setParameter("m", month)
                    .uniqueResult();
            return Optional.ofNullable(p);
        }
    }

    @Override
    public List<Payment> findByBuilding(Long buildingId, int year, int month) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "select p from Payment p " +
                                    "where p.building.id = :bId and p.periodYear = :y and p.periodMonth = :m " +
                                    "order by p.paymentDate desc",
                            Payment.class)
                    .setParameter("bId", buildingId)
                    .setParameter("y", year)
                    .setParameter("m", month)
                    .list();
        }
    }

    @Override
    public BigDecimal sumByCompany(Long companyId, int year, int month) {
        try (Session session = sessionFactory.openSession()) {
            BigDecimal sum = session.createQuery(
                            "select coalesce(sum(p.amount), 0) from Payment p " +
                                    "where p.company.id = :cId and p.periodYear = :y and p.periodMonth = :m",
                            BigDecimal.class)
                    .setParameter("cId", companyId)
                    .setParameter("y", year)
                    .setParameter("m", month)
                    .uniqueResult();
            return sum == null ? BigDecimal.ZERO : sum;
        }
    }

    @Override
    public BigDecimal sumByEmployee(Long employeeId, int year, int month) {
        try (Session session = sessionFactory.openSession()) {
            BigDecimal sum = session.createQuery(
                            "select coalesce(sum(p.amount), 0) from Payment p " +
                                    "where p.employee.id = :eId and p.periodYear = :y and p.periodMonth = :m",
                            BigDecimal.class)
                    .setParameter("eId", employeeId)
                    .setParameter("y", year)
                    .setParameter("m", month)
                    .uniqueResult();
            return sum == null ? BigDecimal.ZERO : sum;
        }
    }
}
