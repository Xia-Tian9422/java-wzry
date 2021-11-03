package com.blb.shop.web.servlet;

import com.blb.shop.domain.Item;
import com.blb.shop.domain.ItemCat;
import com.blb.shop.domain.PageInfo;
import com.blb.shop.domain.User;
import com.blb.shop.service.ItemCatSerivice;
import com.blb.shop.service.ItemSerivice;
import com.blb.shop.service.UserSerivice;
import com.blb.shop.service.impl.ItemCatServiceImpl;
import com.blb.shop.service.impl.ItemServiceImpl;
import com.blb.shop.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet("/Index/*")
public class IndexServlet extends BaseServlet {

    ItemSerivice itemSerivice = new ItemServiceImpl();
    ItemCatSerivice itemCatSerivice = new ItemCatServiceImpl();

    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        List<Item> rm_items = itemSerivice.selectItemByIflag(1);
        List<Item> yh_items = itemSerivice.selectItemByIflag(2);
        request.setAttribute("rm_items", rm_items);
        request.setAttribute("yh_items", yh_items);
        //   request.getRequestDispatcher("/index_.jsp").forward(request,response);
        return "forward:/index_.jsp";
    }

    public String item(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        ItemSerivice itemSerivice = new ItemServiceImpl();
        int id = Integer.parseInt(request.getParameter("id"));
        Item items = itemSerivice.selectItemdesc(id);
        request.setAttribute("items", items);
        // request.getRequestDispatcher("/item.jsp").forward(request,response);
        return "forward:/item.jsp";

    }

    //获取导航栏数据
    public String itemCat(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        List<ItemCat> itemCats = itemCatSerivice.selectItemCat(1);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), itemCats);
        return null;
    }

    public String queryItemPage(HttpServletRequest request, HttpServletResponse response) {

        int cid = Integer.parseInt(request.getParameter("cid"));
        int p = Integer.parseInt(request.getParameter("p"));

        PageInfo<Item> pageInfo = itemSerivice.selectItemPageByCid(cid, p, 6);
        request.setAttribute("item", pageInfo);
        ItemCat itemCat = itemCatSerivice.selectItemCatByCid(cid);
        request.setAttribute("itemCat", itemCat);
        return "forward:/category.jsp";
    }


    public String selectLikeItem(HttpServletRequest request, HttpServletResponse response) {

        int p = Integer.parseInt(request.getParameter("p"));
        String name = request.getParameter("name");
        PageInfo<Item> pageInfo = itemSerivice.selectLikeItem("%" + name + "%", p, 6);
        request.setAttribute("likeItems", pageInfo);
        request.setAttribute("name", name);
        return "forward:/search.jsp";
    }

}

