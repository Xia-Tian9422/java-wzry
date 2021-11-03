package com.blb.shop.dao;

import com.blb.shop.domain.ItemCat;

import java.util.List;

public interface ItemCatDao {
    public List<ItemCat> selectItemCat(int status);

    public ItemCat selectItemCatByCid(int id);


}
