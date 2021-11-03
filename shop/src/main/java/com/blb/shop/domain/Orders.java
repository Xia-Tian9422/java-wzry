package com.blb.shop.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orders {

    private String oid;//订单编号
    private Date ordertime;//
    private  double total;//总计
    private  int state;//1未付款 2已付款
    private  String address;
    private  String name;//名字
    private  String telephone;//电话
//菜单 订单 和订单项  一对多
    private List<OrderItem> orderItems = new ArrayList<>();
    //订单和用的关系 多对一 一个订单只能有一个用户
    private  User user;

    public Orders() {
    }

    public Orders(String oid, Date ordertime, double total, int state, String address, String name, String telephone, List<OrderItem> orderItems, User user) {
        this.oid = oid;
        this.ordertime = ordertime;
        this.total = total;
        this.state = state;
        this.address = address;
        this.name = name;
        this.telephone = telephone;
        this.orderItems = orderItems;
        this.user = user;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

