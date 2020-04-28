package com.whl.codes.designPrinciples.dry.codes.others;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class PasswordValidation {
    public static boolean validate(String password) {
        return !(password.isEmpty() || password.length() == 0);
    }
}
