package org.elDom.dao;

import org.elDom.entity.Company;

import java.math.BigDecimal;
import java.util.List;

public interface CompanyDao extends GenericDao<Company, Long> {

    /**
     * Сортира фирмите по общ приход (всички плащания).
     */
    List<Company> findAllOrderByIncomeDesc();

    /**
     * Сортира фирмите по приход за конкретен период (година+месец).
     */
    List<Company> findAllOrderByIncomeDesc(int year, int month);

    /**
     * Общо събрани суми за компания (всички периоди).
     */
    BigDecimal totalIncome(Long companyId);

    /**
     * Общо събрани суми за компания за даден период.
     */
    BigDecimal totalIncome(Long companyId, int year, int month);
}
