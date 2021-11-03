package com.blb.shop.service;

import com.blb.shop.domain.ItemCat;

import java.util.List;

public interface ItemCatSerivice {
    public List<ItemCat> selectItemCat(int status);
    public ItemCat selectItemCatByCid(int id);
}
