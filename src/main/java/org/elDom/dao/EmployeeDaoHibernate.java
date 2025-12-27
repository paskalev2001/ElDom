package org.elDom.dao;

import org.elDom.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class EmployeeDaoHibernate extends GenericDaoHibernate<Employee, Long> implements EmployeeDao {

    public EmployeeDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory, Employee.class);
    }

    @Override
    public List<Employee> findByCompanyOrderByName(Long companyId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "select e from Employee e " +
                                    "where e.company.id = :cId " +
                                    "order by e.lastName asc, e.firstName asc",
                            Employee.class)
                    .setParameter("cId", companyId)
                    .list();
        }
    }

    @Override
    public List<Employee> findByCompanyOrderByBuildingsCountDesc(Long companyId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "select e from Employee e " +
                                    "left join e.buildings b " +
                                    "where e.company.id = :cId " +
                                    "group by e.id " +
                                    "order by count(b.id) desc, e.lastName asc, e.firstName asc",
                            Employee.class)
                    .setParameter("cId", companyId)
                    .list();
        }
    }

    @Override
    public List<Employee> findByCompanyOrderByBuildingsCountAsc(Long companyId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "select e from Employee e " +
                                    "left join e.buildings b " +
                                    "where e.company.id = :cId " +
                                    "group by e.id " +
                                    "order by count(b.id) asc, e.lastName asc, e.firstName asc",
                            Employee.class)
                    .setParameter("cId", companyId)
                    .list();
        }
    }

    @Override
    public long countBuildings(Long employeeId) {
        try (Session session = sessionFactory.openSession()) {
            Long cnt = session.createQuery(
                            "select count(b) from Building b where b.employee.id = :eId",
                            Long.class)
                    .setParameter("eId", employeeId)
                    .uniqueResult();
            return cnt == null ? 0L : cnt;
        }
    }

    @Override
    public List<Employee> searchByName(Long companyId, String text) {
        String q = text == null ? "" : text.trim().toLowerCase();
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "select e from Employee e " +
                                    "where e.company.id = :cId and " +
                                    "(lower(e.firstName) like :q or lower(e.lastName) like :q) " +
                                    "order by e.lastName asc, e.firstName asc",
                            Employee.class)
                    .setParameter("cId", companyId)
                    .setParameter("q", "%" + q + "%")
                    .list();
        }
    }
}
