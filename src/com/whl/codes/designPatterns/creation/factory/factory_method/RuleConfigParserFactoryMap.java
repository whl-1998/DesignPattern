package com.whl.codes.designPatterns.creation.factory.factory_method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class RuleConfigParserFactoryMap {
    private static final Map<String, IRuleConfigParserFactory> cachedFactories = new HashMap<>();

    //缓存了不同文件后缀的工厂类
    static {
        cachedFactories.put("json", new JsonRuleConfigParserFactory());
        cachedFactories.put("xml", new XmlRuleConfigParserFactory());
        cachedFactories.put("yaml", new YamlRuleConfigParserFactory());
        cachedFactories.put("properties", new PropertiesRuleConfigParserFactory());
    }

    //传入参数是配置文件类型, 根据不同的格式从缓存中获取到不同的工厂对象并返回
    public static IRuleConfigParserFactory getParserFactory(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        IRuleConfigParserFactory parserFactory = cachedFactories.get(type.toLowerCase());
        return parserFactory;
    }
}
