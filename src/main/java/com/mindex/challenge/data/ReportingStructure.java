package com.mindex.challenge.data;

import java.util.List;

public class ReportingStructure {

    private Employee employee;
    private int numOfReports;
    private int totalReports = 0;

    public ReportingStructure(Employee employee){
        this.employee = employee;
    }

    public Employee getEmployee(){
        return employee;
    }

    public void setEmployee(){
        this.employee = employee;
    }

    public int getNumOfReports(){
        return numOfReports;
    }

    public void setNumOfReports(){
        this.numOfReports = findNumReports(this.employee);
    }

    public int findNumReports(Employee employee){
        List<Employee> directReports = employee.getDirectReports();
        if (directReports.size() == 0){
            return 0;
        } else {
            for (Employee report: directReports){
                totalReports += findNumReports(report);
            }
        }
        return totalReports;
    }
}
