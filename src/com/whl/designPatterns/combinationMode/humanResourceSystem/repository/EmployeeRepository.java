package com.whl.designPatterns.combinationMode.humanResourceSystem.repository;

import java.util.List;

public interface EmployeeRepository {
    Double getEmployeeSalary(Long employeeId);

    List<Long> getDeptEmployeeIds(Long deptId);
}
