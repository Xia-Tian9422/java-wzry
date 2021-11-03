package com.blb.shop.dao.impl;

import com.blb.shop.dao.OrdersDao;
import com.blb.shop.domain.Item;
import com.blb.shop.domain.OrderItem;
import com.blb.shop.domain.Orders;
import com.blb.shop.utils.DataSourceUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceUtils.getDataSource());

    @Override
    public int insertOrders(Orders orders) {

        String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
        Object[] params = {
                orders.getOid(),
                orders.getOrdertime(),
                orders.getTotal(),
                orders.getState(),
                orders.getAddress(),
                orders.getName(),
                orders.getTelephone(),
                orders.getUser().getUid()
        };


        int update = jdbcTemplate.update(sql, params);

        return update;
    }

    @Override
    public int insertOrderItem(OrderItem orderItem) {

        String sql = "insert into order_item values(?,?,?,?,?)";
        Object[] params = {
                orderItem.getItemid(),
                orderItem.getCount(),
                orderItem.getSubtotal(),
                orderItem.getItem().getId(),
                orderItem.getOrders().getOid()

        };

        int update = jdbcTemplate.update(sql, params);
        return update;
    }

    //根据用户的Id来查询到用户所有的商品
    @Override
    public int selectCountByUid(int uid) {
        String sql = "select count(*) from orders where orders.uid=?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, uid);
        return integer;
    }

    //分页查询用户下的订单
    @Override
    public List<Orders> selectPageByUid(int uid, int startIndex, int pageSize) {
        String sql = "select * from orders where uid=? ORDER BY ordertime DESC LIMIT ?,? ";
        List<Orders> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Orders>(Orders.class), uid, startIndex, pageSize);
        return query;
    }

    //根据订单id查询订单项集合
    @Override
    public List<OrderItem> selectOrderItemsByOid(String oid) {
        String sql = "SELECT os.subtotal ,os.count ,om.image,om.shop_price,om.`name` from order_item os ,item om where os.id=om.id and os.oid=?";
        List<OrderItem> query = jdbcTemplate.query(sql, new RowMapper<OrderItem>() {

            @Override
            public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {
                OrderItem orderItem = new OrderItem();
                orderItem.setCount(resultSet.getInt("count"));
                orderItem.setSubtotal(resultSet.getDouble("subtotal"));
                Item item = new Item();
                item.setImage(resultSet.getString("image"));
                item.setShop_price(resultSet.getDouble("shop_price"));
                item.setName(resultSet.getString("name"));

                orderItem.setItem(item);
                return orderItem;
            }
        }, oid);
        return query;
    }

    @Override
    public Orders selectOrderByOid(String oid) {
        String sql = "select *  from orders where oid = ? ";
        try {

            Orders orders = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Orders>(Orders.class), oid);
            List<OrderItem> orderItems = selectOrderItemsByOid(orders.getOid());
            orders.setOrderItems(orderItems);
            return orders;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public int updateOrderInfo(Orders orders) {
        String sql = "update  orders set address = ? ,name = ? , telephone = ? where oid = ?";

        int i = jdbcTemplate.update(sql, orders.getAddress(),orders.getName(),orders.getTelephone(),orders.getOid());
        return i;
    }
}
