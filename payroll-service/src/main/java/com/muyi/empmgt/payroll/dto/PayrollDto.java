package com.muyi.empmgt.payroll.dto;

import com.muyi.empmgt.payroll.model.PayItem;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

public class PayrollDto {

    private Integer id;
    private Integer employeeId;
    private Integer year;
    private Integer month;

    private Set<PayItem> payitems;

    private String createdBy;
    private String modifiedBy;
    private Date createdDate;
    private Date modifiedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Set<PayItem> getPayitems() {
        return payitems;
    }

    public void setPayitems(Set<PayItem> payitems) {
        this.payitems = payitems;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
