package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private ReportingStructureService reportingStructureService;

    @Override
    public ReportingStructure read(String employeeId) {
        LOG.debug("Creating ReportingStructure for [{}]", employeeId);

        if (employeeId == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }
        
        return reportingStructureService.read(employeeId);
    }
}
