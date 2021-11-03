package com.blb.shop.web.servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //  System.out.println("执行");
        //分析url获取要执行的方法
        String url = req.getRequestURI();

        //截取路径里需要的字符
        String substring = url.substring(url.lastIndexOf("/") + 1);
          //System.out.println(substring);
        try {
            //根据方法获取到method的对象
            Method method = this.getClass().getMethod(substring, HttpServletRequest.class, HttpServletResponse.class);
            String invoke = (String) method.invoke(this, req, resp);
         //   System.out.println(invoke);
            if (invoke != null) {
                //抽取路径
                String substring1 = invoke.substring(invoke.lastIndexOf(":") + 1);
               //System.out.println(substring1);
                if (invoke.contains("forward")) {
                  //  System.out.println("转发");
                    req.getRequestDispatcher(substring1).forward(req, resp);

                } else if (invoke.contains("redirect")) {
                    //重定向
                    resp.sendRedirect(req.getContextPath() + substring1);
                }

            }

        } catch (Exception e) {
           // e.printStackTrace();
        }
    }
}
