package com.whl.codes.designPatterns.structural.proxyMode;

/**
 * @author whl
 * @version V1.0
 * @Title: 通过代理模式对这个类实现 "收集接口请求的原始数据" 功能的增强
 * @Description:
 */
public class UserController implements IUserController {
    public UserVo login(String telephone, String password) {
        //TODO:login
        return new UserVo();//TODO: 封装VO
    }

    public UserVo register(String telephone, String password) {
        //TODO: register
        return new UserVo();//TODO: 封装VO
    }
}
