package org.elDom.dao;

import org.elDom.entity.Apartment;
import org.elDom.entity.Building;
import org.elDom.entity.Resident;

import java.util.List;

public interface BuildingDao extends GenericDao<Building, Long> {

    List<Apartment> findApartmentsByBuilding(Long buildingId);

    List<Resident> findResidentsByBuilding(Long buildingId);

    List<Building> findBuildingsByEmployee(Long employeeId);

    long countBuildingsByEmployee(Long employeeId);

    /**
     * Връща служителя (ID), който има най-малко зачислени сгради в дадена компания.
     * (за логиката при разпределяне)
     */
    Long findEmployeeIdWithMinBuildings(Long companyId);
}
