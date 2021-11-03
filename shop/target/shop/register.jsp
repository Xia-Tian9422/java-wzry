<%--
  Created by IntelliJ IDEA.
  User: 帅帅
  Date: 2021/7/16
  Time: 21:08
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
    <title>酷玩周边商城-注册</title>
    <!-- 引入Bootstrap核心样式文件 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css"/>
    <!-- 引入jQuery核心js文件 -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <!-- 引入BootStrap核心js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>

    <script>
        $(document).ready(function () {
            $.validator.addMethod("checkRegister", function (value) {
                let isOk = false
                ;
                $.ajax({
                    url: "${pageContext.request.contextPath}/user/checkRegister",
                    type: "post",
                    data: {"username": value},
                    success: function (data) {
                        if (data.code == 1) {
                            isOk = false;
                        } else {
                            isOk = true;
                        }
                    }, async: false
                });
                return isOk;
            })
            $("#reg-form").validate({
                //定义验证控件
                rules: {
                    username: {
                        required: true,
                        minlength: 5,
                        maxlength: 8,
                        checkRegister: true
                    },
                    password: {
                        required: true,
                        minlength: 5,
                        maxlength: 8,

                    },
                    email: {
                        required: true,
                        email: true
                    }

                },
                //提示信息
                messages: {
                    username: {
                        required: "请输入用户名!!!",
                        minlength: "最少输入5位",
                        maxlength: "最长输入8位",
                        checkRegister: "用户名以重复！"
                    },
                    password: {
                        required: "请输入密码!!!",
                        minlength: "最少输入5位",
                        maxlength: "最长输入8位"
                    },
                    email: {
                        required: "请输入密码!!!",
                        email: "邮件输入错误！"
                    }

                }, errorElement: "em",
                //element 一个使用的标签input
                errorPlacement: function (error, element) {

                    error.addClass("help-block");

                    element.parents(".col-sm-10").addClass("has-feedback");

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
                    $(element).parents(".col-sm-10").addClass("has-error").removeClass("has-success");
                    $(element).next("span").addClass("glyphicon-remove").removeClass("glyphicon-ok");
                },
                unhighlight: function (element, errorClass, validClass) {
                    $(element).parents(".col-sm-10").addClass("has-success").removeClass("has-error");
                    $(element).next("span").addClass("glyphicon-ok").removeClass("glyphicon-remove");
                }
            });


        });
    </script>
</head>

<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid rg-container">

    <div class="rg-layout ">
        <div class="rg-left col-md-3 pt20">
            <p>新用户注册</p>
            <p>USER REGISTER</p>
        </div>
        <div class="col-md-6 mt80">
            <form class="form-horizontal" id="reg-form" action="${pageContext.request.contextPath}/user/register"
                  method="get">

                <c:if test="${error!=null}">
                    <div class="alert alert-danger alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                            ${error}
                    </div>
                </c:if>
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-10">
                        <input type="text" name="username" class="form-control" id="username" placeholder="请输入用户名">
                        <em id="username-info"></em>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" name="password" id="password"
                               placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" name="email" id="email" placeholder="请输入邮箱">
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="name" id="name" placeholder="请输入姓名">

                    </div>
                </div>
                <div class="form-group">
                    <label for="tel" class="col-sm-2 control-label">手机号</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="tel" id="tel" placeholder="请输入手机号">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">注册</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-3 rg-right pt20">
            <p class="pull-right">已有账号?
                <a href="login.html">立即登录</a>
            </p>
        </div>

    </div>

</div>

</div>
<!--底部-->
<jsp:include page="footer.jsp"/>
</body>

</html>
