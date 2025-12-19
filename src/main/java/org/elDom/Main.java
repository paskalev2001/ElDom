package org.elDom;

import org.elDom.configuration.SessionFactoryUtil;
import org.elDom.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.elDom.configuration.SessionFactoryUtil.*;

public class Main {
    public static void main(String[] args) {
        //Session session = SessionFactoryUtil.getSessionFactory().openSession();
        // Smoke tests
        SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            /*
             * 1. Company + Tax
             */
            Company company = new Company();
            company.setName("TestCo");

            Tax tax = new Tax();
            tax.setCompany(company);
            company.getTaxes().add(tax);

            session.persist(company); // cascade => записва и Tax

            /*
             * 2. Building + Apartment + Resident
             *    (Building задължително има Company!)
             */
            Building building = new Building();
            building.setAddress("Sofia, Test 1");
            building.setCompany(company); // ВАЖНО

            Apartment apartment = new Apartment();
            apartment.setNumber("12");
            apartment.setFloor(3L);
            apartment.setArea(new BigDecimal("78.50"));

            building.addApartment(apartment);

            Resident owner = new Resident();
            owner.setFirstName("Ivan");
            owner.setLastName("Ivanov");
            owner.setBirthDate(LocalDate.of(1990, 5, 10));
            owner.setUsesElevator(true);
            owner.setRole(ResidentRole.OWNER);

            apartment.addResident(owner);

            session.persist(building); // cascade => apartment + resident

            /*
             * 3. Company + Employee
             */
            Company company2 = new Company();
            company2.setName("ServiceCo");

            Employee employee = new Employee();
            employee.setFirstName("Ivan");
            employee.setLastName("Petrov");

            company2.addEmployee(employee);

            session.persist(company2); // cascade => employee

            /*
             * 4. Company + Building (втори пример)
             */
            Company company3 = new Company();
            company3.setName("FirmA");

            Building building2 = new Building();
            building2.setAddress("Sofia, ul. Test 1");
            building2.setFloors(8L);

            company3.addBuilding(building2);

            session.persist(company3); // cascade => building

            tx.commit();
        }

        //session.close();
    }
}