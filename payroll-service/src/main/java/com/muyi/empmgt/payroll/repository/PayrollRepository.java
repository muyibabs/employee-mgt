package com.muyi.empmgt.payroll.repository;

import com.muyi.empmgt.payroll.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Integer> {

    Payroll findByEmployeeIdAndYearAndMonth(Integer empId, Integer year, Integer month);
    List<Payroll> findByYearAndMonth(Integer year, Integer month);
}
