package com.blb.shop.domain;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * 购物车
 * */
public class Cart {
    //定义map的集合key就是这项商品的id
    private Map<Integer, CartItem> cartItemMap = new LinkedHashMap<>();

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    //总价
    private double total;

    //添加购物车的方法
    public void addCartItem(CartItem cartItem) {
        //获取添加购物车的id
        int cid = cartItem.getItem().getId();

        //判断这个商品在购物车中是否存在
        if (cartItemMap.containsKey(cid)) {
            //获取原来的购物项
            CartItem cartItem_old = cartItemMap.get(cid);
            //更新购物车的数量
            cartItem_old.setCount(cartItem_old.getCount() +cartItem.getCount());
            //  更新总计
           total=total+cartItem.getSubtotal();
        }else {
            //如果没有购物车则添加一个购物车
            cartItemMap.put(cid,cartItem);
            //更新总计
            total=total+cartItem.getSubtotal();
        }

    }
    //清除购物车
    public  void delCartItem(int id){

        CartItem cartItem_rome = cartItemMap.remove(id);
        total=total-cartItem_rome.getSubtotal();
    }
    //清空购物车
    public  void  clear(){

        cartItemMap.clear();
        total =0;
    }

}
