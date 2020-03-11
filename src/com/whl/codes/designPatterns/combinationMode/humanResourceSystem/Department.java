package com.whl.designPatterns.combinationMode.humanResourceSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title: 部门类
 * @Description:
 */
public class Department extends HumanResource{
    private List<HumanResource> subNodes;

    public Department(long id) {
        super(id);
        subNodes = new ArrayList<>();
    }

    /**
     * 获取部门全体员工薪资
     * @return
     */
    @Override
    public double calculateSalary() {
        double totalSal = 0;
        for (HumanResource hr : subNodes) {
            totalSal += hr.calculateSalary();
        }
        this.salary = totalSal;
        return totalSal;
    }

    public void addSubNode(HumanResource hr) {
        subNodes.add(hr);
    }
}
