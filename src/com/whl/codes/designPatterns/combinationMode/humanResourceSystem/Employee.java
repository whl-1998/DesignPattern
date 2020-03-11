package com.whl.designPatterns.combinationMode.humanResourceSystem;

/**
 * @author whl
 * @version V1.0
 * @Title: 员工类
 * @Description:
 */
public class Employee extends HumanResource{
    public Employee(long id, double salary) {
        super(id);
        this.salary = salary;
    }

    /**
     * 获取员工个人薪资
     * @return
     */
    @Override
    public double calculateSalary() {
        return salary;
    }
}
