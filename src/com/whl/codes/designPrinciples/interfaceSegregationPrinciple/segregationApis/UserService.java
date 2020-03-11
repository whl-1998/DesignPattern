package com.whl.designPrinciples.interfaceSegregationPrinciple.segregationApis;

public interface UserService {
    boolean register(String cellphone, String password);
    boolean login(String cellphone, String password);
    UserInfo getUserInfoById(long id);
    UserInfo getUserInfoByCellphone(String cellphone);
}
