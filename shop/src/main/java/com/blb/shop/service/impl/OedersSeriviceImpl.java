package com.blb.shop.service.impl;

import com.blb.shop.dao.OrdersDao;
import com.blb.shop.dao.impl.OrdersDaoImpl;
import com.blb.shop.domain.OrderItem;
import com.blb.shop.domain.Orders;
import com.blb.shop.domain.PageInfo;
import com.blb.shop.service.OrdersSerivice;

import java.util.List;

public class OedersSeriviceImpl implements OrdersSerivice {

    OrdersDao or = new OrdersDaoImpl();

    @Override
    public void submitOrders(Orders orders) {
        or.insertOrders(orders);
        for (OrderItem orderItem : orders.getOrderItems()) {
            or.insertOrderItem(orderItem);
        }
    }


    @Override
    public PageInfo<Orders> selectPageByUid(int uid, int currentPage, int pageSize) {

        //根据分类id得到该分类的总量
        int total = or.selectCountByUid(uid);
        //得到当前页数的起始数
        int startIndex = (currentPage - 1) * pageSize;
        //查询当前页的数据
        List<Orders> items = or.selectPageByUid(uid, startIndex, pageSize);

        for (Orders item : items) {
            item.setOrderItems( or.selectOrderItemsByOid(item.getOid()));
        }


        //创建分页对象
        PageInfo<Orders> pageInfo = new PageInfo<>(total, pageSize, currentPage);
        //设置当前页的集合数据
        pageInfo.setList(items);
        return pageInfo;

    }

    @Override
    public Orders selectOrderByOid(String oid) {

        return or.selectOrderByOid(oid);
    }

    @Override
    public int updateOrderInfo(Orders orders) {
        return or.updateOrderInfo(orders);
    }

}
