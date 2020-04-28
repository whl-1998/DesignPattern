package com.whl.codes.designPrinciples.dry.logical;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description: 复用了str校验逻辑
 */
public class UserAuthenticator {
    public void authenticate(String username, String password) {
        if (!isValidUsername(username)) {
            throw new RuntimeException("用户名格式不合法");
        }
        if (!isValidPassword(password)) {
            throw new RuntimeException("密码格式不合法");
        }
        //...省略其他代码...
    }

    private boolean isValidUsername(String username) {
        if (!onlyContains(username)) {
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password) {
        // 只允许包含小写字符
        if (!password.equals(password.toLowerCase())) {
            return false;
        }
        if (!onlyContains(password)) {
            return false;
        }
        return true;
    }

    /**
     * 要求str不为空, 且字符串长度为4 ~ 64之间, 且只允许包含指定字符
     * @param str
     * @return
     */
    private boolean onlyContains(String str) {
        if (str == null) {
            return false;
        }
        // check length: 4~64
        int length = str.length();
        if (length < 4 || length > 64) {
            return false;
        }
        // contains only a~z, 0~9, dot
        for (int i = 0; i < length; ++i) {
            char c = str.charAt(i);
            if (!(c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.') {
                return false;
            }
        }
        return false;
    }
}
