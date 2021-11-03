package com.blb.shop.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Item {
// `id` INT  AUTO_INCREMENT,
//  `name` VARCHAR(255) DEFAULT NULL,
//  `market_price` DOUBLE DEFAULT NULL,
//  `shop_price` DOUBLE DEFAULT NULL,
//  `num` INT NOT NULL COMMENT '库存数量',
//  `image` VARCHAR(255) DEFAULT NULL,
//  `idesc` VARCHAR(255) DEFAULT NULL,
//  `iflag` TINYINT(4) DEFAULT NULL, -- 1 热门  2 优惠
//  `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '商品状态，1-上架，2-下架，3-删除',
//  `created` DATETIME NOT NULL COMMENT '创建时间',
//  `updated` DATETIME NOT NULL COMMENT '更新时间',
//  `cid` INT DEFAULT NULL,

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(double market_price) {
        this.market_price = market_price;
    }

    public double getShop_price() {
        return shop_price;
    }

    public void setShop_price(double shop_price) {
        this.shop_price = shop_price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIdesc() {
        return idesc;
    }

    public void setIdesc(String idesc) {
        this.idesc = idesc;
    }

    public int getIflag() {
        return iflag;
    }

    public void setIflag(int iflag) {
        this.iflag = iflag;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", market_price=" + market_price +
                ", shop_price=" + shop_price +
                ", num=" + num +
                ", image='" + image + '\'' +
                ", idesc='" + idesc + '\'' +
                ", iflag=" + iflag +
                ", created=" + created +
                ", updated=" + updated +
                ", cid=" + cid +
                '}';
    }

    private int id;
    private String name;
    private double market_price;
    private double shop_price;
    private int num;
    private String image;
    private String idesc;
    private int iflag;
    private Date created;
    private Date updated;
    private int cid;

    public Item(int id, String name, double market_price, double shop_price, int num, String image, String idesc, int iflag, Date created, Date updated, int cid) {
        this.id = id;
        this.name = name;
        this.market_price = market_price;
        this.shop_price = shop_price;
        this.num = num;
        this.image = image;
        this.idesc = idesc;
        this.iflag = iflag;
        this.created = created;
        this.updated = updated;
        this.cid = cid;
    }
}
