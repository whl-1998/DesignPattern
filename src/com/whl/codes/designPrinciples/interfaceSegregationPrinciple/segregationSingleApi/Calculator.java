package com.whl.designPrinciples.interfaceSegregationPrinciple.segregationSingleApi;

import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title: 用于计算一组容器中数据的最大值、最小值、平均值等
 * @Description:
 */
public class Calculator {
    public static Long max(List<Long> dataSet) {
        long res = Long.MIN_VALUE;
        for (long i : dataSet) {
            res = Math.max(res, i);
        }
        return res;
    }

    public static Long min(List<Long> dataSet) {
        long res = Long.MAX_VALUE;
        for (long i : dataSet) {
            res = Math.min(res, i);
        }
        return res;
    }

    public static Long average(List<Long> dataSet) {
        long res = 0;
        for (long i : dataSet) {
            res += i;
        }
        return res/dataSet.size();
    }

    public static Long sum(List<Long> dataSet) {
        long res = 0;
        for (long i : dataSet) {
            res += i;
        }
        return res;
    }
}
