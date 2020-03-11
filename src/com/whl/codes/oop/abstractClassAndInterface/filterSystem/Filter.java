package com.whl.oop.abstractClassAndInterface.filterSystem;

public interface Filter {
    void doFilter(RpcRequest req) throws RpcException;
}
