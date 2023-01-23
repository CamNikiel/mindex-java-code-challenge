package com.mindex.challenge.data;

import java.util.ArrayList;
import java.util.List;

// Create an object that stores a list of employees, and collects their reports in atree format,
// keeping the order.
class EmployeeList{
    private Employee employee;
    private ArrayList<EmployeeList> employeesList;

    EmployeeList(Employee employee){
        this.employee = employee;
    }

    public void setEmployeeList(Employee employee){
        employeesList.add(new EmployeeList(employee));
    }

    public ArrayList<EmployeeList> getEmployeesList(){
        return this.getEmployeesList();
    }

    public Employee getEmployee(){
        return this.employee;
    }
}

public class ReportingStructure {

    private Employee employee;
    private EmployeeList employeesList;

    public ReportingStructure(Employee employee){
        this.employee = employee;
        this.employeesList = new EmployeeList(employee);
    }

    public Employee getEmployee(){
        return employee;
    }

    public void setEmployee(){
        this.employee = employee;
    }

    // Recursively find the total number of employees that are reports of the given employee.
    public int findNumReports(Employee employee){
        int totalReports = 0;
        List<Employee> directReports = employee.getDirectReports();
        if(directReports.size() != 0){
            for (Employee report: directReports){
                return totalReports += findNumReports(report);
            }
        }
        return totalReports;
    }

    // Recursively find the structure of employees that are reports of the given employee.
    public void findEmployeesList(EmployeeList employeeList){
        List<Employee> directReports = employee.getDirectReports();
        if (directReports.size() != 0){
            for (Employee report: directReports){
                // Check if employeeList is filled out, so the structure is not copied in there multiple times.
                employeeList.setEmployeeList(report);
                findEmployeesList(new EmployeeList(report));
            }
        }
    }

    public EmployeeList getEmployeesList(){
        return this.employeesList;
    }
}
