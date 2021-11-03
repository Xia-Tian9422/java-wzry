package com.blb.shop.dao.impl;

import com.blb.shop.dao.ItemCatDao;
import com.blb.shop.domain.ItemCat;
import com.blb.shop.utils.DataSourceUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ItemCatImpl implements ItemCatDao {
    private JdbcTemplate template = new JdbcTemplate(DataSourceUtils.getDataSource());

    @Override
    public List<ItemCat> selectItemCat(int status) {
        String sql = "select * from item_cat where status=?";
        List<ItemCat> itemCats = template.query(sql, new BeanPropertyRowMapper<>(ItemCat.class), status);
        return itemCats;

    }

    @Override
    public ItemCat selectItemCatByCid(int cid) {
        String sql = "select * from item_cat where cid=?";
        ItemCat itemCat = template.queryForObject(sql, new BeanPropertyRowMapper<ItemCat>(ItemCat.class), cid);
        return  itemCat;
    }


}
