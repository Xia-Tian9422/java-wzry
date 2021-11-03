package com.blb.shop.domain;

import java.util.List;

public class PageInfo<T> {
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotaIPage() {
        return totaIPage;
    }

    public void setTotaIPage(int totaIPage) {
        this.totaIPage = totaIPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
    private int total;//总计数
    private int pageSize;//页码大小
    private int totaIPage;//总页数
    private int currentPage;//当前页
    private int prePage;//上一页
    private int nextPage;//下一页

    @Override
    public String toString() {
        return "PageInfo{" +
                "total=" + total +
                ", pageSize=" + pageSize +
                ", totaIPage=" + totaIPage +
                ", currentPage=" + currentPage +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", list=" + list +
                '}';
    }

    private List<T>list;

    public PageInfo() {
    }

    public PageInfo(int total, int pageSize, int currentPage) {
        this.total = total;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        //计算出总页数
        this.totaIPage = this.total % pageSize == 0 ? this.total / pageSize : this.total / pageSize + 1;
        //计算出上一页
        this.prePage = this.currentPage - 1 <= 0 ? 1 : currentPage - 1;
        //计算出下一页
        this.nextPage = this.currentPage + 1 >= this.totaIPage ? this.totaIPage : this.currentPage + 1;
    }
}
