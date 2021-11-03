package com.blb.shop.web.servlet;

import com.blb.shop.domain.Cart;
import com.blb.shop.domain.CartItem;
import com.blb.shop.domain.Item;
import com.blb.shop.domain.User;
import com.blb.shop.service.ItemSerivice;
import com.blb.shop.service.UserSerivice;
import com.blb.shop.service.impl.ItemServiceImpl;
import com.blb.shop.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet {


    public String add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //获取商品id和购买数量
        int id = Integer.parseInt(request.getParameter("id"));
        int buyNum = Integer.parseInt(request.getParameter("buyNum"));

        //根据商品id 调用service得到要购买商品得对象
        ItemSerivice itemSerivice = new ItemServiceImpl();
        Item items = itemSerivice.selectItemdesc(id);
        //创建购物车
        CartItem cartItem = new CartItem(items, buyNum);

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //r如果没有购物车
        if (cart == null) {
            cart = new Cart();
        }
        //添加购物车
        cart.addCartItem(cartItem);
        // 将购物车上传到session中
        request.getSession().setAttribute("cart", cart);
       // response.sendRedirect(request.getContextPath() + "/cart.jsp");
        return "redirect:/cart.jsp";
    }

    public String del(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //获取商品id
        int  id = Integer.parseInt(request.getParameter("id"));
        //接收购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        cart.delCartItem(id);
        // 将购物车上传到session中

       // response.sendRedirect(request.getContextPath()+"/cart.jsp");
        return "redirect:/cart.jsp";

    }

    public String clear(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //接收购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //清空购物车
        cart.clear();
        //重定向
        //response.sendRedirect(request.getContextPath()+"/cart.jsp");
        return "redirect:/cart.jsp";
    }


}
