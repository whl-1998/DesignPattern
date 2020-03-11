package com.whl.designPrinciples.interfaceSegregationPrinciple.segregationApis;

/**
 * @author whl
 * @version V1.0
 * @Title: UserServiceImpl不需要被强迫依赖 "delete" 相关的接口
 * @Description:
 */
public class UserServiceImpl implements UserService {
    @Override
    public boolean register(String cellphone, String password) {
        return false;
    }

    @Override
    public boolean login(String cellphone, String password) {
        return false;
    }

    @Override
    public UserInfo getUserInfoById(long id) {
        return null;
    }

    @Override
    public UserInfo getUserInfoByCellphone(String cellphone) {
        return null;
    }
}
