package com.blb.shop.test;

import com.blb.shop.dao.ItemCatDao;
import com.blb.shop.dao.ItemDao;
import com.blb.shop.dao.OrdersDao;
import com.blb.shop.dao.UserDao;
import com.blb.shop.dao.impl.*;
import com.blb.shop.domain.*;
import com.blb.shop.service.ItemCatSerivice;
import com.blb.shop.service.ItemSerivice;
import com.blb.shop.service.impl.ItemCatServiceImpl;
import com.blb.shop.service.impl.ItemServiceImpl;
import com.blb.shop.utils.DataSourceUtils;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

public class DaoTest {
    @Test
    public void fun1() {

        UserDao dao = new UserDaoImpl();
        User user = dao.userlogin("xb001", "888888");
        System.out.println(user);
    }


    @Test
    public void fun2() {
        UserDao dao = new UserDaoImpl();
        User user = new User();
        user.setEamil("1248@qq.com");
        user.setName("张三");
        user.setPassword("123456");
        user.setUsername("zs001");
        user.setTelephone("12346874897");
        int i = dao.userRegister(user);
        if (i > 0) {
            System.out.println("注册成功");
        } else {
            System.out.println("注册失败");
        }


    }

    @Test
    public void fun3() {

        ItemDao itemDao = new ItemDaoImpl();
        List<Item> items = itemDao.selectItemByIflag(1);
        if (items != null) {
            System.out.println("正确");
        } else {
            System.out.println("错误");
        }

    }

    @Test
    public void fun4() {
        UserDao userDao = new UserDaoImpl();
        User wc001 = userDao.userSelect("xb001");
        System.out.println(wc001);
    }

    @Test
    public void fun5() {
        UserDao userDao = new UserDaoImpl();
        User wc001 = userDao.checkUsername("wc001");
        System.out.println(wc001);
    }

//    @Test
//    public void fun6() {
//        ItemDao  itemDao= new ItemDaoImpl();
//        List<Item> items = itemDao.selectItemPageByCid(1, 0, 6);
//        System.out.println(items);
//    }


    @Test
    public void fun7() {
        ItemSerivice itemSerivice = new ItemServiceImpl();
        int i = itemSerivice.selectItemCountByCid(1);
        System.out.println(i);
        PageInfo<Item> pageInfo = itemSerivice.selectItemPageByCid(1, 2, 6);
        System.out.println(pageInfo);
    }

    @Test
    public void fun8() {
        ItemDao itemDao = new ItemDaoImpl();
        List<Item> items = itemDao.selectLikeItem("%Q%", 1, 6);
        System.out.println(items);
    }

    @Test
    public void fun9() {
        ItemSerivice itemSerivice = new ItemServiceImpl();
        PageInfo<Item> pageInfo = itemSerivice.selectLikeItem("%Q%", 1, 6);
        System.out.println(pageInfo);
    }

    @Test
    public void fun10() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceUtils.getDataSource());
        String sql = "select * from order_item ot ,item it WHERE ot.id=it.id and ot.oid=?";
        List query = jdbcTemplate.query(sql, new RowMapper<OrderItem>() {
            @Override
            public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {
                OrderItem orderItem = new OrderItem();
                orderItem.setCount(resultSet.getInt("count"));
                return orderItem;
            }
        }, "1b74a0a667ed4b57b3690cbc438ea4b1");
        System.out.println(query);
    }

    @Test
    public void fun11() {
//        OrdersDao ordersDao= new OrdersDaoImpl();
//       // List<OrderItem> orderItems = ordersDao.selectOrderItemsByOid(1);
//        System.out.println(orderItems);
//        for (OrderItem orderItem : orderItems) {
//            System.out.println(orderItem.getSubtotal());
//            System.out.println(orderItem.getItem().getImage());
//        }

        OrdersDao ordersDao = new OrdersDaoImpl();
//        Orders orders = ordersDao.selectOrderByOid("41785714fdee4cb19f3a6a95909c6c0d");
//        System.out.println(orders);
        Orders orders = new Orders();
        orders.setOid("3254c6a5aecd4a5199bc4a27b22f848c");
        orders.setTelephone("111");
        orders.setName("123");
        orders.setAddress("68468");
        int i = ordersDao.updateOrderInfo(orders);
        System.out.println(i);
    }

}
