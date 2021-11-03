package com.blb.shop.dao.impl;

import com.blb.shop.dao.UserDao;
import com.blb.shop.domain.Item;
import com.blb.shop.domain.User;
import com.blb.shop.utils.DataSourceUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private  JdbcTemplate template = new JdbcTemplate(DataSourceUtils.getDataSource());

    @Override
    public User userlogin(String username, String passowrd) {
        String sql = "select * from user where username =? and password = ? ";
        try {
            User user =  template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username,passowrd);
        return  user;
        } catch (DataAccessException e) {
           // e.printStackTrace();
        }
        return null;
    }
    @Override
    public  int userRegister(User user){
        String sql  = "insert into user values (null,?,?,?,?,?)";
        int i = 0;
        try {
            i = template.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getEamil(), user.getTelephone());
            return  i;
        } catch (DataAccessException e) {

            e.printStackTrace();
        }
       return  0;
    }

    @Override
    public User userSelect(String userName) {
        String sql= "SELECT * FROM user WHERE username = ? ";

        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), userName);
        } catch (DataAccessException e) {
            //e.printStackTrace();
        }
        return user;
    }

    @Override
    public User checkUsername(String username) {
        String sql  = "select * from user where username=?";
        try {
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
            return  user;

        } catch (DataAccessException e) {

            //e.printStackTrace();
        }

        return  null;
    }


}
