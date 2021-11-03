<%@ page import="java.util.List" %>
<%@ page import="com.blb.shop.domain.Item" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: 帅帅
  Date: 2021/7/16
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>酷玩周边商城</title>
    <!-- 引入Bootstrap核心样式文件 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css"/>
    <!-- 引入jQuery核心js文件 -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <!-- 引入BootStrap核心js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


</head>

<body>
<!-- 头部 -->
<jsp:include page="header.jsp"/>
<!--广告轮播图-->
<div class="container-fluid" style="padding: 0px;">
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            <li data-target="#carousel-example-generic" data-slide-to="3"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item">
                <img src="${pageContext.request.contextPath}/img/banner1.jpg" alt="...">
            </div>
            <div class="item ">
                <img src="${pageContext.request.contextPath}/img/banner2.jpg" alt="...">
            </div>
            <div class="item">
                <img src="${pageContext.request.contextPath}/img/banner3.jpg" alt="...">
            </div>
            <div class="item active">
                <img src="${pageContext.request.contextPath}/img/banner4.jpg" alt="...">
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
<!--主区域-->
<div class="container" style="margin-top: 10px;">
    <div class="row">
        <div class="col-md-12 col-xs-12">
            <div class="page-header">
                <h3><strong>热卖商品 </strong><small>Hot commodities</small></h3>
            </div>
        </div>
    </div>
    <div class="row text-center goods-list">
        <%--        //从域中取出热门商品的数据--%>
        <c:if test="${not empty rm_items }">
            <c:forEach items="${rm_items}" var="rm">
                <div class="col-md-4 col-xs-4">
                    <a class="thumbnail" href="${pageContext.request.contextPath}/Index/item?id=${rm.id}"> <img
                            class="img-responsive " src="${pageContext.request.contextPath}/upload/${rm.image}"/>
                        <p class="item-name">${rm.name}</p>

                        <p class="item-price"><strong>￥${rm.shop_price}</strong></p>
                    </a>
                </div>
            </c:forEach>
        </c:if>
    </div>
    <div class="row">
        <div class="col-md-12 col-xs-12">
            <div class="page-header">
                <h3><strong>优惠商品</strong><small> Preferential commodities</small></h3>
            </div>
        </div>
    </div>
    <div class="row text-center goods-list">
        <%--从域中去除优惠数据--%>
        <c:if test="${not empty yh_items }">
            <c:forEach items="${yh_items}" var="rm">
                <div class="col-md-4 col-xs-4">
                    <a class="thumbnail" href="${pageContext.request.contextPath}/Index/item?id=${rm.id}"> <img
                            class="img-responsive " src="${pageContext.request.contextPath}/upload/${rm.image}"/>
                        <p class="item-name">${rm.name}</p>
                        <p class="item-price"><strong>￥${rm.shop_price}</strong></p>
                    </a>
                </div>
            </c:forEach>
        </c:if>
    </div>

    <!--底部-->
    <jsp:include page="footer.jsp"/>
</body>
</html>