<%--
    Servlet容器发送的信息对用户不友好，我们自己定义一个错误页，信息，更加友好。
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"   errorPage="error.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    forward:转发动作<hr>
        jsp:forward 到一个page="xx.jsp"
        <%
            Integer.parseInt("210000");
        %>
</body>
</html>
