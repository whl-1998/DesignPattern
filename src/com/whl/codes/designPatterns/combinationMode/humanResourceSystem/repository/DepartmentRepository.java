package com.whl.designPatterns.combinationMode.humanResourceSystem.repository;

import java.util.List;

public interface DepartmentRepository {
    /**
     * 获取传入id对应部门的子部门全部id
     * @param departmentId
     * @return
     */
    List<Long> getSubDepartmentIds(long departmentId);
}
