package com.blb.shop.service.impl;

import com.blb.shop.dao.ItemDao;
import com.blb.shop.dao.impl.ItemDaoImpl;
import com.blb.shop.domain.Item;
import com.blb.shop.domain.PageInfo;
import com.blb.shop.service.ItemSerivice;

import java.util.List;

public class ItemServiceImpl implements ItemSerivice {
    private ItemDao itemDao = new ItemDaoImpl();

    @Override
    public List<Item> selectItemByIflag(int iflag) {
        return itemDao.selectItemByIflag(iflag);
    }

    @Override
    public Item selectItemdesc(int id) {
        return itemDao.selectItemdesc(id);
    }


    @Override
    public int selectItemCountByCid(int cid) {
        int i = itemDao.selectItemCountByCid(cid);
        return i;
    }

    @Override
    public PageInfo<Item> selectItemPageByCid(int cid, int currentPage, int pageSize) {
        //根据分类id得到该分类的总量
        int total = itemDao.selectItemCountByCid(cid);
        //得到当前页数的起始数
        int startIndex = (currentPage - 1) * pageSize;
        //查询当前页的数据
        List<Item> items = itemDao.selectItemPageByCid(cid, startIndex, pageSize);
        //创建分页对象
        PageInfo<Item> pageInfo = new PageInfo<>(total, pageSize, currentPage);
        //设置当前页的集合数据
        pageInfo.setList(items);
        return pageInfo;
    }




    @Override
    public  PageInfo<Item>  selectLikeItem(String name,int currentPage, int pageSize) {

        //根据分类id得到该分类的总量
        int total = itemDao.selectItemCountByName(name);
        //得到当前页数的起始数
        int startIndex = (currentPage - 1) * pageSize;
        //查询当前页的数据
        List<Item> items = itemDao.selectLikeItem(name, startIndex, pageSize);
        //创建分页对象
        PageInfo<Item> pageInfo = new PageInfo<>(total, pageSize, currentPage);
        //设置当前页的集合数据
        pageInfo.setList(items);
        return  pageInfo;
    }
}
