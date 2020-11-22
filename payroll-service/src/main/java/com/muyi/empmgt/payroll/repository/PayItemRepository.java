package com.muyi.empmgt.payroll.repository;

import com.muyi.empmgt.payroll.model.PayItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayItemRepository extends JpaRepository<PayItem, Integer> {

}
