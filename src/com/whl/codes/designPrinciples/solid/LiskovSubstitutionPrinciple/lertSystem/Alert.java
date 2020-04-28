package com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem;

import com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.alertHandler.AlertHandler;
import com.whl.codes.designPrinciples.solid.LiskovSubstitutionPrinciple.lertSystem.fromApiSystem.ApiStatInfo;

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
