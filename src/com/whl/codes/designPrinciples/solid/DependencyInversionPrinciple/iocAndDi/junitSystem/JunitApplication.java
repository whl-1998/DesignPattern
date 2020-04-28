package com.whl.designPrinciples.iocAndDi.junitSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title: 模拟Junit框架
 * @Description:
 */
public class JunitApplication {
    //任务容器, 包含了测试任务的所有Bean
    private static final List<TestCase> testCases = new ArrayList<>();

    //添加测试样例到容器
    public static void register(TestCase testCase) {
        testCases.add(testCase);
    }

    //遍历容器, 执行测试逻辑
    public static final void main(String[] args) {
        for (TestCase testCase : testCases) {
            testCase.run();
        }
    }
}
