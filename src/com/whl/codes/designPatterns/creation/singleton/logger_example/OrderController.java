package com.whl.codes.designPatterns.creation.singleton.logger_example;

import java.io.IOException;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class OrderController {
    private Logger logger = Logger.getInstance();

    public void create(OrderVo order) throws IOException {
        //省略业务逻辑
        logger.log("create a order: " + order.toString());
    }
}
