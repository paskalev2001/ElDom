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
//            Building building = new Building();
//            building.setAddress("Sofia, Test 1");
//            building.setCompany(company); // ВАЖНО
//
//            Apartment apartment = new Apartment();
//            apartment.setNumber("12");
//            apartment.setFloor(3L);
//            apartment.setArea(new BigDecimal("78.50"));
//
//            building.addApartment(apartment);
//
//            Resident owner = new Resident();
//            owner.setFirstName("Ivan");
//            owner.setLastName("Ivanov");
//            owner.setBirthDate(LocalDate.of(1990, 5, 10));
//            owner.setUsesElevator(true);
//            owner.setRole(ResidentRole.OWNER);
//
//            apartment.addResident(owner);
//
//            session.persist(building); // cascade => apartment + resident

            /*
             * 3. Company + Employee
             */
//            Company company2 = new Company();
//            company2.setName("ServiceCo");
//
//            Employee employee = new Employee();
//            employee.setFirstName("Ivan");
//            employee.setLastName("Petrov");
//
//            company2.addEmployee(employee);
//
//            session.persist(company2); // cascade => employee
//
//            /*
//             * 4. Company + Building (втори пример)
//             */
//            Company company3 = new Company();
//            company3.setName("FirmA");
//
//            Building building2 = new Building();
//            building2.setAddress("Sofia, ul. Test 1");
//            building2.setFloors(8L);
//
//            company3.addBuilding(building2);
//
//            session.persist(company3); // cascade => building


            /*
             * 4. Building + Employee + Company
             */
            Company c = new Company();
            c.setName("FirmA");

            Employee e = new Employee();
            e.setFirstName("Maria");
            e.setLastName("Georgieva");
            c.addEmployee(e);

            Building b = new Building();
            b.setAddress("Sofia, ul. Example 5");

            c.addBuilding(b);     // задава b.company = c
            e.addBuilding(b);     // задава b.employee = e

            session.persist(c);   // ако Company има cascade към employees и buildings => ще запише всичко
            // ако няма cascade към buildings, добави: session.persist(b);

            /*
             * 5. Apartment + Payment to existing building
             */

//            Apartment ap = new Apartment();
//            ap.setNumber("15");
//            ap.setFloor(4L);
//            ap.setArea(new BigDecimal("82.00"));
//            ap.setBuilding(b); // вече съществуваща сграда
//
//            Payment payment = new Payment();
//            payment.setAmount(new BigDecimal("120.00"));
//            payment.setPaymentDate(LocalDate.now());
//            payment.setPeriodMonth(5);
//            payment.setPeriodYear(2025);
//
//            ap.addPayment(payment);
////
////            session.persist(ap); // cascade => записва и Payment
//
//            /*
//             * 6. Apartment + Payment to existing building
//             */
//
//            Payment p = new Payment();
//            p.setAmount(new BigDecimal("140.00"));
//            p.setPaymentDate(LocalDate.now());
//            p.setPeriodMonth(6);
//            p.setPeriodYear(2026);
//
//            p.setApartment(ap);  // вече имаш/създаваш апартамент
//            p.setBuilding(b);    // същата сграда, към която е апартаментът
//
//            session.persist(p);

            // 1) Company
            //Company company = new Company();
            company.setName("SmokeCo");
            company.setEik("123456789");
            company.setAddress("Sofia");
            company.setPhone("0888000000");
            company.setEmail("smoke@co.bg");

            // 2) Tax -> Company
            //Tax tax = new Tax();
            tax.setCompany(company);
            // ако имаш полета за такса (примерно rate), ги сетни тук
            // tax.setBaseRate(...)

            company.addTax(tax);

            // 3) Employee -> Company
            Employee employee = new Employee();
            employee.setFirstName("Maria");
            employee.setLastName("Georgieva");
            employee.setActive(true);

            company.addEmployee(employee);

            // 4) Building -> Company + Employee
            Building building = new Building();
            building.setAddress("Sofia, ul. Example 5");
            building.setFloors(8L);

            company.addBuilding(building);      // задава building.company
            employee.addBuilding(building);     // задава building.employee

            // 5) Apartment -> Building
            Apartment apartment = new Apartment();
            apartment.setNumber("12");
            apartment.setFloor(3L);
            apartment.setArea(new BigDecimal("78.50"));

            building.addApartment(apartment);

            // 6) Resident -> Apartment
            Resident resident = new Resident();
            resident.setFirstName("Ivan");
            resident.setLastName("Ivanov");
            resident.setBirthDate(LocalDate.of(1990, 5, 10));
            resident.setUsesElevator(true);
            resident.setRole(ResidentRole.BOTH);

            apartment.addResident(resident);

            // 7) Payment -> Apartment + Building
            Payment payment = new Payment();
            payment.setAmount(new BigDecimal("120.00"));
            payment.setPaymentDate(LocalDate.now());
            payment.setPeriodMonth(12);
            payment.setPeriodYear(2025);

            // важно: задаваме и двете връзки (NOT NULL)
            payment.setApartment(apartment);
            payment.setBuilding(building);

            // ако си добавил Apartment.addPayment(...) helper:
            // apartment.addPayment(payment);

            // Persist най-горния root
            session.persist(company);

            // Ако нямаш cascade от Apartment към Payment, persist-ни Payment отделно:
            session.persist(payment);

            tx.commit();
        }

        //session.close();
    }
}