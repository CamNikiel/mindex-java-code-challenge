package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {

    private String employeeId;
    private Double salary;
    private Date effactiveDate;

    public Compensation(){

    }

    public void setEmployeeId(String employeeId){
        this.employeeId = employeeId;
    }

    public void setSalary(Double salary){
        this.salary = salary;
    }

    public void setDate(Date effactiveDate){
        this.effactiveDate = effactiveDate;
    }

    public String getEmployeeId(){
        return this.employeeId;
    }

    public Double getSalary(){
        return this.salary;
    }

    public Date getEffectiveDate(){
        return this.effactiveDate;
    }
}
