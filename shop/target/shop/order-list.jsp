<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 帅帅
  Date: 2021/7/18
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>英雄联盟周边商城-订单列表</title>
    <!-- 引入Bootstrap核心样式文件 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css"/>
    <!-- 引入jQuery核心js文件 -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <!-- 引入BootStrap核心js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>

<body>
<jsp:include page="header.jsp"/>
<div class="container" style="margin-top: 20px;">
<c:if test="${!empty fff.list}">
    <C:forEach items="${fff.list}" var="fff">
    <div class="list-group">
        <h4 class="list-group-item-heading">订单号: <span class="label label-success">${fff.oid}</span> 状态

            <c:if test="${fff.state==1}">

            <a href="${pageContext.request.contextPath}/order/pay?oid=${fff.oid}" class="btn btn-warning btn-sm">未付款</a>

            </c:if>
            <c:if test="${ fff.state!=1}">

            <span class='label label-success'>已付款</span>

            </c:if>

        <div class="list-group-item">
            <table class="table table-condensed">
                <thead>
                <tr>
                    <th>图片</th>
                    <th>商品</th>
                    <th>单价(元)</th>
                    <th>数量</th>
                    <th>小计</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${fff.orderItems}" var="od">


                <tr>
                    <td><img src="${pageContext.request.contextPath}/upload/${od.item.image}" class="buyimg"/></td>
                    <td>${od.item.name}</td>
                    <td>¥${od.item.shop_price}</td>
                    <td>${od.count}</td>
                    <td>¥${od.subtotal}</td>
                </tr>

                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="text-right list-group-item" style="margin-bottom: 10px;">
            <address>
                收货人:${ empty fff.name?"***":fff.name}  <br/>
                电话：${ empty fff.telephone?"***":fff.telephone} <br/>
                地址：${ empty fff.address?"***":fff.address}
            </address>
            商品金额：<span class="xiangq_font3">￥${fff.total}</span>
        </div>
    </div>
    </C:forEach>


    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>

                <a href="${pageContext.request.contextPath}/order/queryPageOrders?p=${fff.prePage}"
                   aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach begin="1" end="${fff.totaIPage}" var="i">

                <li  ${fff.currentPage==i?"class='active'":""}><a
                        href="${pageContext.request.contextPath}/order/queryPageOrders?p=${i}">${i}</a>
                </li>

            </c:forEach>

            <li>
                <a href="${pageContext.request.contextPath}/order/queryPageOrders?p=${fff.nextPage}"
                   aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</c:if>
</div>
<c:if test="${empty fff.list}">
    <div class="jumbotron">
        <p class="text-center">
            您的购物车中还没有商品,
            <a class="" href="${pageContext.request.contextPath}/"> 去逛逛吧</a>
        </p>
    </div>
</c:if>
<!--底部-->
<jsp:include page="footer.jsp"/>
</body>

</html>