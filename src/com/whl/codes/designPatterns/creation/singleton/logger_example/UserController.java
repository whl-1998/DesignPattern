package com.whl.codes.designPatterns.creation.singleton.logger_example;

import java.io.IOException;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class UserController {
    private Logger logger = Logger.getInstance();

    public void login(String username, String password) throws IOException {
        //省略业务逻辑
        logger.log(username + " is logging");
    }
}
