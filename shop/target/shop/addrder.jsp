<%--
  Created by IntelliJ IDEA.
  User: 帅帅
  Date: 2021/8/2
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>英雄联盟周边商城-订单支付</title>
    <!-- 引入Bootstrap核心样式文件 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css"/>
    <!-- 引入jQuery核心js文件 -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <!-- 引入BootStrap核心js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
    <script>
        window.location = function () {
            $("#yz-form").validate({
                //定义验证控件
                rules: {
                    telephone: {
                        required: true,
                    },
                    Name: {
                        required: true,
                        minlength: 11,
                        maxlength: 11,

                    },
                    address: {
                        required: true,
                    }


                },
                //提示信息
                messages: {
                    telephone: {
                        required: "请输入电话号码!!!"
                    },
                    Name: {
                        required: "请输入用户名",

                    },
                    address: {
                        required: "请输入地址",
                    }

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
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<form id="yz-form" class="form-horizontal list-group-item" action="${pageContext.request.contextPath}/order/pay">
    <h3 class="list-group-item-heading">配送至:</h3>
    <div class="form-group">
        <label for="address" class="col-sm-1 control-label">收货地址:</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" id="address">
        </div>
    </div>
    <div class="form-group">
        <label for="Name" class="col-sm-1 control-label">收货人:</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" id="Name">
        </div>
    </div>
    <div class="form-group">
        <label for="telephone" class="col-sm-1 control-label">联系方式:</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" id="telephone">
        </div>
    </div>
    <div class="form-group">
        <label for="zf" class="col-sm-1 control-label">支付方式:</label>
        <div class="col-md-3 ">
            <input type="submit" id="zf" class="btn btn-success btn-lg" value="支付宝支付">
        </div>
    </div>
</form>

</div>

<div class="list-group">

</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>
