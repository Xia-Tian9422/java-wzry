package com.blb.shop.dao;

import com.blb.shop.domain.OrderItem;
import com.blb.shop.domain.Orders;

import java.util.List;

public interface OrdersDao {
    //添加订单
    public int insertOrders(Orders orders);

    //添加订单项
    public int insertOrderItem(OrderItem orderItem);

    //根据用户的id查询出用户的商品总数
    public int selectCountByUid(int uid);

    //查当前页面
    List<Orders> selectPageByUid(int uid, int startIndex, int pageSize);

    //根据商品id查询到的商品
    List<OrderItem> selectOrderItemsByOid(String oid);

    //根据商品编号查询到用户的发送地址、电话、姓名是否为空
    public Orders selectOrderByOid(String oid);
    //更新订单收货信息
    public int updateOrderInfo(Orders orders);

}
