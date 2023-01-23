package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

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

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationUrl;
    private String compensationIdUrl;
    private Employee testEmployee;

    @Autowired
    private CompensationService compensationService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
        testEmployee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }

    @Test
    public void testCreateRead() {
        Compensation testCompensation1 = new Compensation(testEmployee.getEmployeeId());
        testCompensation1.setDate(new Date(3843748));
        testCompensation1.setSalary(90000.10);

        assertCompensationLocalTest(testCompensation1);

        // Create checks
        ResponseEntity createResponseCompensation1 = restTemplate.postForEntity(compensationUrl, testCompensation1, Compensation.class);
        assertEquals(HttpStatus.OK, createResponseCompensation1.getStatusCode());
        Compensation createdCompensation1 = (Compensation) createResponseCompensation1.getBody();
        assertNotNull(createdCompensation1.getEmployeeId());
        assertCompensationEquivalence(testCompensation1, createdCompensation1);

        // Read checks
        ResponseEntity readResponseCompensation1 = restTemplate.getForEntity(compensationIdUrl, Compensation.class, createdCompensation1.getEmployeeId());
        assertEquals(HttpStatus.OK, readResponseCompensation1.getStatusCode());
        Compensation readCompensation = (Compensation) readResponseCompensation1.getBody();
        assertCompensationEquivalence(createdCompensation1, readCompensation);
    }

    // Function to compare Compensation objects from the CompensationRepository
    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
        assertEquals(expected.getSalary(), actual.getSalary());
    }

    // Function to test if local Compensation objects are created.
    private static void assertCompensationLocalTest(Compensation compensation) {
        assertNotNull(compensation.getEffectiveDate());
        assertNotNull(compensation.getEmployeeId());
        assertNotNull(compensation.getSalary());
    }
}