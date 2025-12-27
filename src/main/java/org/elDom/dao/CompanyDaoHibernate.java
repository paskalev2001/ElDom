package org.elDom.dao;

import org.elDom.entity.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.List;

public class CompanyDaoHibernate extends GenericDaoHibernate<Company, Long> implements CompanyDao {

    public CompanyDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory, Company.class);
    }

    @Override
    public List<Company> findAllOrderByIncomeDesc() {
        try (Session session = sessionFactory.openSession()) {
            // Връщаме Company entity-та, групирани по company, сортирани по SUM(payment.amount)
            return session.createQuery(
                            "select c from Company c " +
                                    "left join Payment p on p.company.id = c.id " +
                                    "group by c.id " +
                                    "order by coalesce(sum(p.amount), 0) desc, c.name asc",
                            Company.class)
                    .list();
        }
    }

    @Override
    public List<Company> findAllOrderByIncomeDesc(int year, int month) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "select c from Company c " +
                                    "left join Payment p on p.company.id = c.id " +
                                    "and p.periodYear = :y and p.periodMonth = :m " +
                                    "group by c.id " +
                                    "order by coalesce(sum(p.amount), 0) desc, c.name asc",
                            Company.class)
                    .setParameter("y", year)
                    .setParameter("m", month)
                    .list();
        }
    }

    @Override
    public BigDecimal totalIncome(Long companyId) {
        try (Session session = sessionFactory.openSession()) {
            BigDecimal sum = session.createQuery(
                            "select coalesce(sum(p.amount), 0) from Payment p where p.company.id = :cId",
                            BigDecimal.class)
                    .setParameter("cId", companyId)
                    .uniqueResult();
            return sum == null ? BigDecimal.ZERO : sum;
        }
    }

    @Override
    public BigDecimal totalIncome(Long companyId, int year, int month) {
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
}
