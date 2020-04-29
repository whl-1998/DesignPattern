package com.whl.codes.designPatterns.creation.factory.factory_method;

import com.whl.codes.designPatterns.creation.factory.IRuleConfigParser;
import com.whl.codes.designPatterns.creation.factory.XmlRuleConfigParser;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class XmlRuleConfigParserFactory implements IRuleConfigParserFactory{
    @Override
    public IRuleConfigParser createParser() {
        //省略一些复杂的创建解析器的逻辑
        return new XmlRuleConfigParser();
    }
}
