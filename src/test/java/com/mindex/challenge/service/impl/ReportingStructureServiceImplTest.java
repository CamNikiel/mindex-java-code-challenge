package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

import com.mindex.challenge.dao.EmployeeRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {
    private Employee testEmployee;


    private String employeeUrl;
    private String reportingStructureIdUrl;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        reportingStructureIdUrl = "http://localhost:" + port + "/reportingStructure/{id}";
        testEmployee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertNotNull(testEmployee);
    }

    @Test
    public void testRead() {
        // Test functions using local objects (Not pulling objects from employee_database)
        assertTestMe(testEmployee);
        // Test functions using employee_database objects.
        ReportingStructure testReportingStructure1 = new ReportingStructure(testEmployee);
        ResponseEntity responseReportStructure1 = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, testEmployee.getEmployeeId());
        assertEquals(HttpStatus.OK, responseReportStructure1.getStatusCode());
        ReportingStructure actualReportStructure1 = (ReportingStructure) responseReportStructure1.getBody();
        // Read checks
        assertReportingStructureEquivalence(testReportingStructure1, actualReportStructure1);
    }

    private static void assertReportingStructureEquivalence(ReportingStructure expected, ReportingStructure actual) {
        Employee actualEmployee = actual.getEmployee();
        Employee expectedEmployee = expected.getEmployee();
        assertEquals(actualEmployee, expectedEmployee);
        assertEquals(expected.findNumReports(expectedEmployee), actual.findNumReports(actualEmployee));
        assertEquals(expected.findNumReports(expectedEmployee), 4);
        expected.findEmployeesList(expected.getEmployeesList());
        actual.findEmployeesList(actual.getEmployeesList());
        assertNotNull(expected.getEmployeesList());
        assertNotNull(actual.getEmployeesList());
        assertEquals(expected.getEmployeesList(), actual.getEmployeesList());
    }

    private static void assertTestMe(Employee testEmployee){
        ReportingStructure newReportingStructure = new ReportingStructure(testEmployee);
        assertNotNull(testEmployee);
        newReportingStructure.findEmployeesList(newReportingStructure.getEmployeesList());
        assertNotNull(newReportingStructure.getEmployeesList());
        assertEquals(newReportingStructure.findNumReports(testEmployee), 4);
    }
}
