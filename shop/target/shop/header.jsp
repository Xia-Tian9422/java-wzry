<%@ page import="com.blb.shop.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: 帅帅
  Date: 2021/7/16
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<header>
    <div class="container-fluid wz-notice">
        <div class="container">
            <li class="icon-notice"></li>
            <span>酷玩周边商城手办热销中！，点击 <a href="#">查看详情></a> </span>
        </div>
    </div>
    <nav class="container-fluid wz-info">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-xs-6">
                    <a class="icon-wzry pull-left" href="#">王者荣耀</a>
                    <div class="fl ico-comm i-solgan">可以触摸的欢乐时光</div>
                </div>
                <div class="col-md-4 hidden-xs">
                    <div class="search-box">
                        <input type="text" id="text" class="pull-left" placeholder="请输入想要找的宝贝"/>
                        <button type="submit" class="pull-left">搜索</button>
                    </div>
                </div>
                <div class="col-md-4 col-xs-6" style="margin-top: 40px;">
                    <ul class="list-inline text-right ">

                        <c:if test="${empty user}">
                            <li>
                                <a class="font1" href="${pageContext.request.contextPath}/login.jsp"><span
                                        class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;登录</a>
                            </li>
                        </c:if>
                        <c:if test="${not empty user}">
                            <li class="dropdown">
                                <span data-toggle="dropdown" class="font1" href="javascript:void(0);"> 欢迎您：${user.name} <span
                                        class="caret"></span></span>
                                <ul class="dropdown-menu">
                                    <li><a href="${pageContext.request.contextPath}/order/queryPageOrders?p=1"><span class="glyphicon glyphicon-list-alt"></span> 订单管理</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li>
                                        <a onclick="confirm('你确定退出嘛？')?href='${pageContext.request.contextPath}/user/loginOut':href='#'"
                                           ;><span class="glyphicon glyphicon-log-out"></span> 退出登录</a></li>
                                </ul>
                            </li>

                        </c:if>
                        <li>
                            <a class="font1" href="${pageContext.request.contextPath}/cart.jsp"><span
                                    class="glyphicon glyphicon-shopping-cart"></span>&nbsp;购物车</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
    <!--导航条-->
    <nav class="navbar navbar-inverse ">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/Index/index">商城首页</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="navbar-ul" class=" nav navbar-nav wz-nav font2">
                    <li><a href="*">ddd</a></li>
                </ul>
                <form class="hidden-md  hidden-lg navbar-right " role="search">
                    <div class="  form-group">
                        <input type="text" class="form-control" placeholder="请搜索商品..">
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</header>
<script>
    $(document).ready(function () {


        $("button[type=submit][class=pull-left]").click(function () {

            let name = $("#text").val();

            location.href = "${pageContext.request.contextPath}/Index/selectLikeItem?p=1&name=" + name;
        });
        $.post("${pageContext.request.contextPath}/Index/itemCat",

            function (data) {
                let str = "";
                for (let i in data) {
                    str += "<li> <a href='${pageContext.request.contextPath}/Index/queryItemPage?cid=" + data[i].cid + "&p=1'>" + data[i].name + "</a></li>";
                }
                console.log(str);
                $("#navbar-ul").html(str);
            }
        )
    })
</script>