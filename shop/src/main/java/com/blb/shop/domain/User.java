package com.blb.shop.domain;

public class User {
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEamil() {
        return eamil;
    }

    public void setEamil(String eamil) {
        this.eamil = eamil;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public User( String username, String password, String name, String eamil, String telephone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.eamil = eamil;
        this.telephone = telephone;
    }
    public User() {
    }
    private  int uid;//用户编号
    private  String username;//用户名
    private  String password;//密码
    private  String name;//真实姓名
    private  String eamil; //邮箱
    private  String telephone;//手机号

    @Override
    public String
    toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", eamil='" + eamil + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
