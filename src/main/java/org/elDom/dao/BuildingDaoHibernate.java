package org.elDom.dao;

import org.elDom.entity.Apartment;
import org.elDom.entity.Building;
import org.elDom.entity.Resident;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class BuildingDaoHibernate extends GenericDaoHibernate<Building, Long> implements BuildingDao {

    public BuildingDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory, Building.class);
    }

    @Override
    public List<Apartment> findApartmentsByBuilding(Long buildingId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "select a from Apartment a " +
                                    "where a.building.id = :bId " +
                                    "order by a.floor asc, a.number asc",
                            Apartment.class)
                    .setParameter("bId", buildingId)
                    .list();
        }
    }

    @Override
    public List<Resident> findResidentsByBuilding(Long buildingId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "select r from Resident r " +
                                    "join r.apartment a " +
                                    "where a.building.id = :bId " +
                                    "order by r.lastName asc, r.firstName asc",
                            Resident.class)
                    .setParameter("bId", buildingId)
                    .list();
        }
    }

    @Override
    public List<Building> findBuildingsByEmployee(Long employeeId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "select b from Building b " +
                                    "where b.employee.id = :eId " +
                                    "order by b.address asc",
                            Building.class)
                    .setParameter("eId", employeeId)
                    .list();
        }
    }

    @Override
    public long countBuildingsByEmployee(Long employeeId) {
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
    public Long findEmployeeIdWithMinBuildings(Long companyId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "select e.id " +
                                    "from Employee e " +
                                    "left join Building b on b.employee.id = e.id " +
                                    "where e.company.id = :cId and (e.active = true or e.active is null) " +
                                    "group by e.id " +
                                    "order by count(b.id) asc, e.id asc",
                            Long.class)
                    .setParameter("cId", companyId)
                    .setMaxResults(1)
                    .uniqueResult();
        }
    }


}
