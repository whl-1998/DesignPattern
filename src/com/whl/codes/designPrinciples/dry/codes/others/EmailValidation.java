package com.whl.codes.designPrinciples.dry.codes.others;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class EmailValidation {
    public static boolean validate(String email) {
        return !(email.isEmpty() || email.length() == 0);
    }
}
