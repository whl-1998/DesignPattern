package com.whl.designPrinciples.openClosePrincipe.alertSystem;

import com.whl.designPrinciples.openClosePrincipe.alertSystem.alertHandler.AlertHandler;
import com.whl.designPrinciples.openClosePrincipe.alertSystem.fromApiSystem.ApiStatInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class Alert {
    private List<AlertHandler> alertHandlers;

    public Alert() {
        alertHandlers = new ArrayList<>();
    }

    /**
     * 添加告警规则
     * @param alertHandler
     */
    public void addAlertHandler(AlertHandler alertHandler) {
        alertHandlers.add(alertHandler);
    }

    //传入一个接口信息apiStatInfo实例, 执行alertHandlers中的告警校验逻辑
    public void check(ApiStatInfo apiStatInfo) {
        for (AlertHandler ah : alertHandlers) {
            ah.check(apiStatInfo);
        }
    }
}
