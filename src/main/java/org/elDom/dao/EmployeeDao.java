package org.elDom.dao;

import org.elDom.entity.Employee;

import java.util.List;

public interface EmployeeDao extends GenericDao<Employee, Long> {

    List<Employee> findByCompanyOrderByName(Long companyId);

    /**
     * Служители на компанията, подредени по брой обслужвани сгради (DESC или ASC).
     * За да не връщаме "pair" типове, правим два метода.
     */
    List<Employee> findByCompanyOrderByBuildingsCountDesc(Long companyId);

    List<Employee> findByCompanyOrderByBuildingsCountAsc(Long companyId);

    long countBuildings(Long employeeId);

    List<Employee> searchByName(Long companyId, String text);
}
