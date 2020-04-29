package com.whl.codes.designPatterns.creation.factory.factory_method;

import com.whl.codes.designPatterns.creation.factory.IRuleConfigParser;
import com.whl.codes.designPatterns.creation.factory.InvalidRuleConfigException;
import com.whl.codes.designPatterns.creation.factory.RuleConfig;

import java.io.File;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class RuleConfigSource {
    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);//获取配置文件后缀
        IRuleConfigParserFactory parserFactory = RuleConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension);
        if (parserFactory == null) {
            throw new InvalidRuleConfigException("Rule config file format is not supported: " + ruleConfigFilePath);
        }
        IRuleConfigParser parser = parserFactory.createParser();
        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    private String getFileExtension(String filePath) {
        String fileName = new File(filePath).getName();
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    public static void main(String[] args) {
        RuleConfigSource ruleConfig = new RuleConfigSource();
        ruleConfig.load("whl.xml");
    }
}
