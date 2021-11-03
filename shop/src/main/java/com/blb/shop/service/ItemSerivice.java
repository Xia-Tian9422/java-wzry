package com.blb.shop.service;

import com.blb.shop.domain.Item;
import com.blb.shop.domain.PageInfo;

import java.util.List;

public interface ItemSerivice {
    public List<Item> selectItemByIflag(int iflag);

    public Item selectItemdesc(int id);


    public int selectItemCountByCid(int cid);

    public PageInfo<Item> selectItemPageByCid(int cid, int currentPage, int pageSize);

    //模糊查询数据
    public  PageInfo<Item>  selectLikeItem(String name, int currentPage, int pageSize);

}
