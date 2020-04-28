package com.whl.codes.designPrinciples.dry.function;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description: 功能性重复
 */
public class IPUtils {
    //通过正则表达式判断
    public boolean isValidIp(String ipAddress) {
        if (ipAddress == null || ipAddress.length() == 0)
            return false;
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        return ipAddress.matches(regex);
    }

    //普通逻辑实现
    public boolean checkIfIpValid(String ipAddress) {
        if (ipAddress == null || ipAddress.length() == 0)
            return false;
        String[] ipUnits = ipAddress.split(".");
        if (ipUnits.length != 4) {
            return false;
        }
        for (int i = 0; i < 4; ++i) {
            int ipUnitIntValue;
            try {
                ipUnitIntValue = Integer.parseInt(ipUnits[i]);
            } catch (NumberFormatException e) {
                return false;
            }
            //ip地址返回必须是 0~255
            if (ipUnitIntValue < 0 || ipUnitIntValue > 255) {
                return false;
            }
            //ip地址头不允许为0
            if (i == 0 && ipUnitIntValue == 0) {
                return false;
            }
        }
        return true;
    }
}
