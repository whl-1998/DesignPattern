package com.whl.oop.abstractClassAndInterface.filterSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title: 对外提供接口
 * @Description:
 */
public class Application {
    private List<Filter> filters;

    public Application() {
        filters = new ArrayList<>();
    }

    /**
     * 执行过滤操作
     * @param req
     */
    public void handlerRpcRequest(RpcRequest req) {
        try {
            for (Filter filter : filters) {
                filter.doFilter(req);
            }
        } catch (RpcException e) {
            //TODO：处理过滤结果
        }
        //..省略其他处理逻辑
    }

    /**
     * 添加需要过滤的功能
     * @param filter
     */
    public void addFilter(Filter filter) {
        filters.add(filter);
    }
}
