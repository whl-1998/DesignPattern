package com.whl.designPrinciples.iocAndDi.junitSystem;

/**
 * @author whl
 * @version V1.0
 * @Title: 模拟测试类
 * @Description:
 */
public class UserServiceTest extends TestCase {
    private int sex;

    @Override
    public boolean doTest() {
        return sex == 1;
    }
}
