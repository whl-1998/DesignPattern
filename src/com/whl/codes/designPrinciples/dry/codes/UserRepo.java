package com.whl.codes.designPrinciples.dry.codes;

import com.whl.codes.designPrinciples.dry.codes.others.User;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class UserRepo {
    public boolean checkIfUserExisted(String email, String password) {
        //TODO: 去数据库检索邮箱以及对应的密码匹配的记录是否存在
        return false;
    }

    public User getUserByEmail(String email) {
        //TODO: 去数据库检索email对应的用户
        return new User();
    }
}
