<%--
  Created by IntelliJ IDEA.
  User: 帅帅
  Date: 2021/7/16
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>酷玩周边商城-用户登录</title>
    <!-- 引入Bootstrap核心样式文件 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css"/>
    <!-- 引入jQuery核心js文件 -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <!-- 引入BootStrap核心js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
    <script>
        window.onload = function () {
            let elementById = document.getElementById("showCode");
            elementById.onclick = function () {
                let date = new Date().getTime();
                elementById.src = "${pageContext.request.contextPath}/CaptchaServlet?d=" + date;

            };


            $("#dl-form").validate({
                //定义验证控件
                rules: {
                    username: {
                        required: true,
                    },
                    password: {
                        required: true,
                        minlength: 5,
                        maxlength: 8,

                    },

                },
                //提示信息
                messages: {
                    username: {
                        required: "请输入用户名!!!"
                    },
                    password: {
                        required: "请输入密码!!!",
                        minlength: "最少输入5位",
                        maxlength: "最长输入8位"
                    },

                }, errorElement: "em",
                //element 一个使用的标签input
                errorPlacement: function (error, element) {

                    error.addClass("help-block");

                    element.parents(".col-sm-7").addClass("has-feedback");

                    if (element.prop("type") === "checkbox") {
                        error.insertAfter(element.parent("label"));
                    } else {
                        error.insertAfter(element);
                    }


                    if (!element.next("span")[0]) {
                        $("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter(element);
                    }
                },
                success: function (label, element) {

                    if (!$(element).next("span")[0]) {
                        $("<span class='glyphicon glyphicon-ok form-control-feedback'></span>").insertAfter($(element));
                    }
                },
                highlight: function (element, errorClass, validClass) {
                    $(element).parents(".col-sm-7").addClass("has-error").removeClass("has-success");
                    $(element).next("span").addClass("glyphicon-remove").removeClass("glyphicon-ok");
                },
                unhighlight: function (element, errorClass, validClass) {
                    $(element).parents(".col-sm-7").addClass("has-success").removeClass("has-error");
                    $(element).next("span").addClass("glyphicon-ok").removeClass("glyphicon-remove");
                }
            });
        };
    </script>
</head>

<body>
<jsp:include page="header.jsp"/>
<%--<%@include file="header.jsp"%>>--%>
<!--登录区域-->
<div class="container-fluid"
     style="height:460px;background: url('${pageContext.request.contextPath}/img/loginbg.jpg') no-repeat;">
    <div class="row">
        <div class="col-md-7">
        </div>
        <div class="col-md-5">
            <div class="panel panel-default" style="width: 400px; margin-top: 50px;">
                <div class="panel-heading">
                    <h3 class="panel-title">用户登录</h3>
                </div>
                <div class="panel-body">
                    <form id="dl-form" class="form-horizontal" action="${pageContext.request.contextPath}/user/login"
                          method="post">
                        <c:if test="${error!=null}">
                            <div class="alert alert-danger alert-dismissible" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                    ${error}
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label for="username" class="col-sm-3 control-label">用户名:</label>
                            <div class="col-sm-7">
                                <input type="text" name="username" value="${ cookie.username.value}"
                                       class="form-control"
                                       id="username" placeholder="请输入用户名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-3 control-label">密码:</label>
                            <div class="col-sm-7">
                                <input type="password" class="form-control" name="password" id="password"
                                       placeholder="请输入密码">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="captcha" class="col-sm-3 control-label">验证码:</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" name="captcha" id="captcha"
                                       placeholder="请输入验证码">
                            </div>
                            <div class="col-sm-4" style="padding: 0;">
                                <img id="showCode" height="34" src="${pageContext.request.contextPath}/CaptchaServlet"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-10">
                                <div class="checkbox">
                                    <label class="checkbox-inline">
                                        <input type="checkbox"
                                               name="jz"  ${ not empty cookie.username.name?"checked='true'":""}
                                               value="true"/>记住用户名
                                        <a href="register.jsp">注册新用户</a>
                                    </label>
                                </div>

                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-7">
                                <input class="btn btn-success  btn-block" type="submit" value="登录" name="submit"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!--底部-->
<jsp:include page="footer.jsp"/>
<%--<%@include file="footer.jsp"%>--%>
</body>

</html>