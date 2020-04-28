package com.whl.designPrinciples.interfaceSegregationPrinciple.segregationApis;

/**
 * @author whl
 * @version V1.0
 * @Title: 删除逻辑放在RestrictedUserServiceImpl中, 保证接口隔离原则
 * @Description:
 */
public class RestrictedUserServiceImpl implements RestrictedUserService{

    @Override
    public boolean deleteUserByCellphone(String cellphone) {
        return false;
    }

    @Override
    public boolean deleteUserById(long id) {
        return false;
    }
}
