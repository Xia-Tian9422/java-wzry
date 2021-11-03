package com.blb.shop.web.servlet;

import com.alibaba.druid.support.json.JSONUtils;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.blb.shop.domain.*;
import com.blb.shop.service.OrdersSerivice;
import com.blb.shop.service.impl.OedersSeriviceImpl;
import com.blb.shop.utils.AlipayConfig;
import com.blb.shop.utils.UuidUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.jar.JarOutputStream;

import static java.lang.System.out;
import static java.lang.System.setOut;

@WebServlet("/order/*")
public class OrdersServlet extends BaseServlet {
    private OrdersSerivice serivice = new OedersSeriviceImpl();

    public String submitOrder(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("error", "请先登入再下单！");
            //redirect
            return "redirect:/login.jsp";
        }
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null && cart.getCartItemMap().size() > 0) {
            Orders orders = new Orders();
            orders.setOid(UuidUtil.getUuid());
            orders.setOrdertime(new Date());
            orders.setTotal(cart.getTotal());
            orders.setState(1);//未付款
            orders.setUser(user);
            //订单项
            for (Map.Entry<Integer, CartItem> integerCartItemEntry : cart.getCartItemMap().entrySet()) {
                //创建一个订单项
                OrderItem orderItem = new OrderItem();
                orderItem.setItemid(UuidUtil.getUuid());
                orderItem.setItem(integerCartItemEntry.getValue().getItem());
                orderItem.setCount(integerCartItemEntry.getValue().getCount());//购买的数量
                orderItem.setSubtotal(integerCartItemEntry.getValue().getSubtotal());
                orderItem.setOrders(orders);
                orders.getOrderItems().add(orderItem);
            }
            serivice.submitOrders(orders);

            cart.clear();

            request.setAttribute("orders", orders);
            return "forward:/order.jsp";
        } else {
            return "forward:/error.jsp";
        }

    }

    public String queryPageOrders(HttpServletRequest request, HttpServletResponse response) {


        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("error", "请先登入再付款！");
            //redirect
            return "redirect:/login.jsp";
        }

        int p = Integer.parseInt(request.getParameter("p"));
        PageInfo<Orders> ordersPageInfo = serivice.selectPageByUid(user.getUid(), p, 3);
        request.setAttribute("fff", ordersPageInfo);

        return "forward:/order-list.jsp";
    }

    public String pay(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String oid = request.getParameter("oid");

        Orders orders = serivice.selectOrderByOid(oid);
        String pay = request.getParameter("pay");
        out.println(orders.getState());
        if (orders.getState() == 1 && pay == null) {
            request.setAttribute("orders", orders);
            return "forward:/order.jsp";
        }
        if (pay != null) {
            String address = request.getParameter("address");
            String name = request.getParameter("name");
            String telephone = request.getParameter("telephone");

            out.println(address);
            out.println(name);
            out.println(telephone);


            if (address != null && name != null && telephone != null) {
                orders.setAddress(address);
                orders.setName(name);
                orders.setTelephone(telephone);
                serivice.updateOrderInfo(orders);

                out.println(serivice.updateOrderInfo(orders));
            }
        }


//获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = orders.getOid();
        //付款金额，必填
        double total_amount = orders.getTotal();
        //订单名称，必填
        String subject = "购买了" + orders.getOrderItems().size() + "项";
        //商品描述，可空


        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //输出
        response.getWriter().println(result);


        return null;

    }


    public String callback(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, UnsupportedEncodingException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }

        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        request.setAttribute("signVerified", signVerified);
        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            //商户订单号
            String out_trade_no = request.getParameter("out_trade_no");

            //支付宝交易号
            String trade_no = request.getParameter("trade_no");
            out.println(out_trade_no);;
            //付款金额
            String total_amount = request.getParameter("total_amount");

            request.setAttribute("out_trade_no", out_trade_no);
            request.setAttribute("trade_no", trade_no);
            request.setAttribute("total_amount", total_amount);


        } else {
            //支付失败

        }
        return "forward:/pay-result.jsp";
    }
}
