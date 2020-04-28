package com.whl.codes.designPrinciples.dry.codes;

import com.whl.codes.designPrinciples.dry.codes.others.EmailValidation;
import com.whl.codes.designPrinciples.dry.codes.others.PasswordValidation;
import com.whl.codes.designPrinciples.dry.codes.others.User;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description: 校验格式的逻辑统一放在service层处理, 避免出现重复执行的情况
 */
public class UserService {
    private UserRepo userRepo;//通过依赖注入或者IOC框架注入

    public User login(String email, String password) {
        //校验邮箱密码格式, 目的是为了避免不必要的IO读写
        if (!EmailValidation.validate(email)) {
            throw new RuntimeException("邮箱格式错误");
        }
        if (!PasswordValidation.validate(password)) {
            throw new RuntimeException("密码格式错误");
        }
        User user = userRepo.getUserByEmail(email);
        if (user == null || !password.equals(user.getPassword())) {
            throw new RuntimeException("校验失败");
        }
        return user;
    }
}
