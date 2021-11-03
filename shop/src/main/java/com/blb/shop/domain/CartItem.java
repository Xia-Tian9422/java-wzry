package com.blb.shop.domain;
/*
* 购物项
* */
public class CartItem {

    public CartItem() {
    }

    public CartItem(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    //购买的商品
    private Item item;
    //购买的数量
    private int count;
    //小计
    private double subtotal;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtotal() {
        subtotal = item.getShop_price()*count;
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
