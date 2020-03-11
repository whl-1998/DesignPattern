package com.whl.designPrinciples.interfaceSegregationPrinciple.segregationApis;

public interface RestrictedUserService {
    boolean deleteUserByCellphone(String cellphone);
    boolean deleteUserById(long id);
}
