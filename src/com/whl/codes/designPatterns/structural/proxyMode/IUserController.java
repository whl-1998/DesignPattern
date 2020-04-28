package com.whl.codes.designPatterns.structural.proxyMode;


/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public interface IUserController {
    UserVo login(String telephone, String password);

    UserVo register(String telephone, String password);
}
