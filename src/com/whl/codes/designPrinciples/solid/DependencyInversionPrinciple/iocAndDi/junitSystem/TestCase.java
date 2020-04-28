package com.whl.designPrinciples.iocAndDi.junitSystem;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public abstract class TestCase {
    public void run() {
        if (doTest()) {
            System.out.println("test is succeed");
        } else {
            System.out.println("test is fail");
        }
    }

    public abstract boolean doTest();
}
