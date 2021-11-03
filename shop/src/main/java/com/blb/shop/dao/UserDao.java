package com.blb.shop.dao;

        import com.blb.shop.domain.User;

        import java.util.List;

public interface UserDao {
    public User userlogin(String username, String passowrd);
    public  int userRegister(User user);
    public User userSelect(String  userName);
    public  User checkUsername(String username);
}
