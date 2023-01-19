package com.mindex.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
		
	}

	public ReportingStructure getReportingStructure(int employeeId) {
		Employee employee = findByEmployeeId(employeeId);
		int numOfReports = getNumOfReports(employee);
		return new ReportingStructure(employee, numOfReports);
	}

	public int getNumOfReports(Employee employee) {
		List<Employee> directReports = employee.getDirectReports();
		int numOfDirectReports = directReports.size();
		int totalReports = 0;
		for (i = 0; i < numOfDirectReports; i++){
			if (directReports[i].getDirectReports().size() == 0){
				totalReports+=1;
			} else {
				totalReports+=1;
				getNumOfReports(directReports[i]);
			}
		}
	}
}
