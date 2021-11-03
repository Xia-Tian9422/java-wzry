package com.blb.shop.service.impl;

import com.blb.shop.dao.UserDao;
import com.blb.shop.dao.impl.UserDaoImpl;
import com.blb.shop.domain.User;
import com.blb.shop.service.UserSerivice;

public class UserServiceImpl implements UserSerivice {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User userlogin(String username, String passowrd) {

        return userDao.userlogin(username, passowrd);
    }

    @Override
    public int userRegister(User user) {

        User user1 = userDao.userSelect(user.getUsername());
        if (user1 != null) {
            return 0;
        } else {
            return userDao.userRegister(user);
        }

    }

    @Override
    public User checkUsername(String username) {
        return userDao.checkUsername(username);
    }

//    @Override
//    public User userSelect(String userName) {
//        return userDao.userSelect(userName);
//    }

}

