package com.blb.shop.service;

import com.blb.shop.domain.User;

public interface UserSerivice {
    //用户登入
    public User userlogin(String username, String passowrd);

    //用户注册
    public int userRegister(User user);

    //        //用户重复查询
//        public  User userSelect(String  userName);
    //查询注册是否有相同用户
    public User checkUsername(String username);

}
