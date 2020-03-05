package com.whl.designPatterns.combinationMode.humanResourceSystem;

import com.whl.designPatterns.combinationMode.humanResourceSystem.repository.DepartmentRepository;
import com.whl.designPatterns.combinationMode.humanResourceSystem.repository.EmployeeRepository;

import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title: 组合模式
 * @Description: 将员工与部门组织成树形结构, 表示 "部分 - 整体" 的层次结构
 *               组合模式让客户端能够统一员工类与部门类的处理逻辑
 */
public class Demo {
    private static final long ORGANIZATION_ROOT_ID = 1001;
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    public Demo(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public void buildOrganization() {
        Department rootDept = new Department(ORGANIZATION_ROOT_ID);
        buildOrganization(rootDept);
    }

    public void buildOrganization(Department department) {
        //从数据库获取到指定部门的所有子部门id
        List<Long> subDeptIds = departmentRepository.getSubDepartmentIds(department.getId());
        //组合部门
        for (long subDeptId : subDeptIds) {
            Department subDept = new Department(subDeptId);
            department.addSubNode(subDept);
            buildOrganization(subDept);
        }
        //从数据库获取指定部门下的所有员工id
        List<Long> employeeIds = employeeRepository.getDeptEmployeeIds(department.getId());
        //组合员工
        for (Long employeeId : employeeIds) {
            double salary = employeeRepository.getEmployeeSalary(employeeId);
            department.addSubNode(new Employee(employeeId, salary));
        }
    }

    public double getDeptSalary(HumanResource hr) {
        return hr.calculateSalary();
    }
}
