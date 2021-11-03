package com.blb.shop.service.impl;

import com.blb.shop.dao.ItemCatDao;
import com.blb.shop.dao.impl.ItemCatImpl;
import com.blb.shop.domain.ItemCat;
import com.blb.shop.service.ItemCatSerivice;

import java.util.List;

public class ItemCatServiceImpl implements ItemCatSerivice {
    private ItemCatDao itemCatDao = new ItemCatImpl();

    @Override
    public List<ItemCat> selectItemCat(int status) {
        List<ItemCat> itemCats = itemCatDao.selectItemCat(status);
        return itemCats;
    }

    @Override
    public ItemCat selectItemCatByCid(int id) {
        return itemCatDao.selectItemCatByCid(id);
    }


}
