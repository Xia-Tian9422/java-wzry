package com.blb.shop.web.servlet;

import com.blb.shop.domain.User;
import com.blb.shop.service.UserSerivice;
import com.blb.shop.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {


    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserSerivice userSerivice = new UserServiceImpl();
        User user = userSerivice.userlogin(username, password);
        String happy = (String) request.getSession().getAttribute("happy-captcha");
        String captcha = request.getParameter("captcha");
        if (!happy.equalsIgnoreCase(captcha)) {
            request.setAttribute("error", "验证码错误！");
            return "forward:/login.jsp";
        }

        if (user != null) {
            request.getSession().setAttribute("user", user);
            String jz = request.getParameter("jz");
            if (jz != null) {
                Cookie cookie = new Cookie("username", request.getParameter("username"));
                cookie.setMaxAge(60 * 60 * 24 * 7);
                cookie.setPath(request.getContextPath());
                response.addCookie(cookie);

            } else {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if ("username".equals(cookie.getName()))
                            cookie.setMaxAge(0);
                            cookie.setPath(request.getContextPath());
                        response.addCookie(cookie);
                    }
                }

            }
            return "redirect:/";
        } else {
            request.setAttribute("error", "错误请从新输入！");
            return "forward:/login.jsp";

        }
    }

    public String register(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {


        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        UserSerivice userSerivice = new UserServiceImpl();
        User user = new User();
        user.setTelephone(request.getParameter("tel"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setEamil(request.getParameter("email"));
        int i = userSerivice.userRegister(user);
        if (i > 0) {
          //  response.sendRedirect(request.getContextPath() + "/login.jsp");
            return "redirect:/login.jsp";
        } else {
            request.setAttribute("error","已有该用户请重新注册！");
           // request.getRequestDispatcher("/register.jsp").forward(request, response);
            return "forward:/register.jsp";
        }
    }

    public String loginOut(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getSession().invalidate();
     //   response.sendRedirect(request.getContextPath()+"/login.jsp");
        return "redirect:/login.jsp";

    }
    public String checkRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
      response.setContentType("application/json;charset=utf-8");

      //接收注册页面传出来的用户名
        String username = request.getParameter("username");
       //创建一个map集合
        Map<String,Object> rs = new HashMap<>();
        //创建实现类
        UserSerivice us = new UserServiceImpl();

        //返回数据库查询到的数据
        User user = us.checkUsername(username);
        if(user!=null){
            rs.put("code",1);
            rs.put("msg","该用户名太受欢迎！");

        }else{
            rs.put("code",0);
            rs.put("msg","该用户名可以使用！");
        }
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(response.getWriter(),rs);
        return null;

    }

}
