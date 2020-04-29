package com.whl.codes.designPatterns.creation.factory.factory_method;

import com.whl.codes.designPatterns.creation.factory.IRuleConfigParser;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public interface IRuleConfigParserFactory {
    IRuleConfigParser createParser();
}
