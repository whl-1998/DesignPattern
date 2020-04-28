package com.whl.designPrinciples.interfaceSegregationPrinciple.segregationInteface.viewConfigInfoByWeb;

import java.util.Map;

public interface Viewer {
    String outputInPlainText();

    Map<String, String> output();
}
