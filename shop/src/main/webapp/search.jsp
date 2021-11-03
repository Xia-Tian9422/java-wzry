<%--
  Created by IntelliJ IDEA.
  User: 帅帅
  Date: 2021/7/18
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>酷玩周边商城-商品列表</title>
    <!-- 引入Bootstrap核心样式文件 -->
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css"/>
    <!-- 引入jQuery核心js文件 -->
    <script src="<%=request.getContextPath()%>/js/jquery-1.11.3.min.js"></script>
    <!-- 引入BootStrap核心js文件 -->
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</head>

<body>
<jsp:include page="header.jsp"/>
<!--主区域-->
<div class="container" style="margin-top:25px;">
    <div class="row">
        <div class="col-md-12">
            <div class="row  text-center cat-item-list">
                <c:forEach items="${likeItems.list}" var="item">
                    <div class="col-md-4">
                        <a href="${pageContext.request.contextPath}/Index/item?id=${item.id}" class="thumbnail"> <img
                                class="img-responsive" src="${pageContext.request.contextPath}/upload/${item.image}"/>
                            <p class="item-name">${item.name}</p>
                            <p class="item-price"><strong>¥${item.shop_price}</strong></p>
                        </a>
                    </div>
                </c:forEach>
            </div>
            <div class="row">
                <div class="col-md-4 col-md-offset-8">

                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li>

                                <a href="${pageContext.request.contextPath}/Index/selectLikeItem?p=${likeItems.prePage}&name=${name}"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:forEach begin="1" end="${likeItems.totaIPage}" var="i">

                                <li  ${likeItems.currentPage==i?"class='active'":""}><a
                                        href="${pageContext.request.contextPath}/Index/selectLikeItem?p=${i}&name=${name}">${i}</a>
                                </li>

                            </c:forEach>

                            <li>
                                <a href="${pageContext.request.contextPath}/Index/selectLikeItem?p=${likeItems.nextPage}&name=${name}"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>

        </div>

    </div>

</div>
<!--底部-->
<jsp:include page="footer.jsp"/>
</body>

</html>