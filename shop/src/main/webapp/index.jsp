
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.getRequestDispatcher("/Index/index").forward(request, response);
%>
</body>
</html>
