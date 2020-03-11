package com.whl.designPatterns.combinationMode.humanResourceSystem;

/**
 * @author whl
 * @version V1.0
 * @Title: 部门类与员工类抽象出的父类
 * @Description:
 */
public abstract class HumanResource {
    protected long id;
    protected double salary;

    public HumanResource(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public abstract double calculateSalary();
}
