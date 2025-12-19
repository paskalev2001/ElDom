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

            // Creating company associated with a Tax
            Company company = new Company();
            company.setName("TestCo");

            Tax tax = new Tax();
            tax.setCompany(company);

            company.getTaxes().add(tax);

            session.persist(company); // cascade = ALL => ще запише и Tax

            // Adding a building with appartment and resident
            Building b = new Building();
            b.setAddress("Sofia, Test 1");

            Apartment ap = new Apartment();
            ap.setNumber("12");
            ap.setFloor(3L);
            ap.setArea(new BigDecimal("78.50"));

            b.addApartment(ap);

            Resident owner = new Resident();
            owner.setFirstName("Ivan");
            owner.setLastName("Ivanov");
            owner.setBirthDate(LocalDate.of(1990, 5, 10));
            owner.setUsesElevator(true);
            owner.setRole(ResidentRole.OWNER);

            ap.addResident(owner);

            session.persist(b);

            // Smoke Test adding company and associating with employee
            Company c = new Company();
            c.setName("TestCo");

            Employee e1 = new Employee();
            e1.setFirstName("Ivan");
            e1.setLastName("Petrov");

            c.addEmployee(e1);

            session.persist(c); // cascade => ще запише и Employee


            tx.commit();
        }

        //session.close();
    }
}