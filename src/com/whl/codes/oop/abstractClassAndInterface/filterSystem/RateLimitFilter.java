package com.whl.oop.abstractClassAndInterface.filterSystem;

/**
 * @author whl
 * @version V1.0
 * @Title: 限流过滤器
 * @Description:
 */
public class RateLimitFilter implements Filter{
    @Override
    public void doFilter(RpcRequest req) throws RpcException {
        //TODO：限流逻辑
    }
}
