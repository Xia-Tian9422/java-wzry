package com.blb.shop.dao.impl;

import com.blb.shop.domain.Item;
import com.blb.shop.domain.OrderItem;
import com.blb.shop.utils.DataSourceUtils;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MyRowMapper implements RowMapper {


    //ResultSet resultSet 当前查询对象的结果
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        //创建订单项
        OrderItem orderItem = new OrderItem();
        orderItem.setCount(resultSet.getInt("count"));
        orderItem.setSubtotal(resultSet.getDouble("subtotal"));
        Item item = new Item();
        item.setImage(resultSet.getString("image"));
        item.setName(resultSet.getString("name"));
        item.setShop_price(resultSet.getDouble("shop_price"));
        orderItem.setItem(item);
        return orderItem;
    }
}
