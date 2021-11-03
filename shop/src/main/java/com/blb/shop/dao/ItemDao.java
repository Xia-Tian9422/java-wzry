package com.blb.shop.dao;

import com.blb.shop.domain.Item;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.List;

public interface ItemDao {
    public List<Item> selectItemByIflag(int iflag);

    public Item selectItemdesc(int id);

    public int selectItemCountByCid(int cid);

    public List<Item> selectItemPageByCid(int cid, int startIndex, int pageSize);

    public int selectItemCountByName(String name);
    //模糊查询数据
    public List<Item> selectLikeItem(String name ,int startIndex, int pageSize);
}
