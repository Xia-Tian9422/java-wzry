package com.blb.shop.dao.impl;

import com.blb.shop.dao.ItemDao;
import com.blb.shop.domain.Item;
import com.blb.shop.domain.ItemCat;
import com.blb.shop.utils.DataSourceUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.*;

public class ItemDaoImpl implements ItemDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceUtils.getDataSource());

    @Override
    public List<Item> selectItemByIflag(int iflag) {
        try {
            //数据库查询语句
            String sql = "select * from item WHERE iflag=? ORDER BY created  DESC LIMIT 6";
            List<Item> items = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Item>(Item.class), iflag);
            //返回数据库查询到得集合
            return items;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    //根据id查询列表图片
    public Item selectItemdesc(int id) {

        String sql = "select * FROM item where id=?";

        Item items = (Item) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Item>(Item.class), id);
        return items;
    }
    @Override
    public int selectItemCountByCid(int cid) {

        String sql = "select count(*) from item where cid=?";
        int item = jdbcTemplate.queryForObject(sql, Integer.class, cid);
        return item;
    }
    //查询返回的条数
    @Override
    public List<Item> selectItemPageByCid(int cid, int startIndex, int pageSize) {
        String sql = "select * from item where cid=? LIMIT ?,?";
        List<Item> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Item>(Item.class), cid, startIndex, pageSize);
        return query;
    }

    @Override
    public List<Item> selectLikeItem(String name, int startIndex, int pageSize) {
        String sql = "select* from item where name LIKE ?LIMIT ?,?";
        List<Item> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Item>(Item.class), name, startIndex, pageSize);
        return query;
    }

    @Override
    public int selectItemCountByName(String name) {

        String sql = "select count(*) from item where name like ?";
        int item = jdbcTemplate.queryForObject(sql, Integer.class, name);
        return item;
    }
}
