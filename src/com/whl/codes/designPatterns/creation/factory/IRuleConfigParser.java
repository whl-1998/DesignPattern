package com.whl.codes.designPatterns.creation.factory;

public interface IRuleConfigParser {
    /**
     * 解析配置文件, 获取到配置规则
     * @param configText
     * @return
     */
    RuleConfig parse(String configText);
}
