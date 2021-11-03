package com.blb.shop.service;

import com.blb.shop.dao.OrdersDao;
import com.blb.shop.dao.impl.OrdersDaoImpl;
import com.blb.shop.domain.OrderItem;
import com.blb.shop.domain.Orders;
import com.blb.shop.domain.PageInfo;

import java.util.List;

public interface OrdersSerivice {

    //添加订单
    public void submitOrders(Orders orders);

    //添加订单项
    //public int insertOrderItem(OrderItem orderItem);


    //查当前页面
    public PageInfo<Orders> selectPageByUid(int uid, int startIndex, int pageSize);

    //根据商品编号查询到用户的发送地址、电话、姓名是否为空

    //根据商品编号查询到用户的发送地址、电话、姓名是否为空
    public Orders selectOrderByOid(String oid);
    //更新订单收货信息
    public int updateOrderInfo(Orders orders);
}
