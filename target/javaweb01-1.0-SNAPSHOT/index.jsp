<%@ page contentType="text/html;charset=UTF-8"  isErrorPage="true" %>
<!-- page指令，指示jsp容器转换器如何翻译成.java-->
<html>
<head>
    <title>通过我们的WEB.xml来进行部署首页</title>
</head>
<body>

<%@include file="/WEB-INF/index2.jsp"%>

<%--<jsp:include page=""></jsp:include>--%>

        <% //语法元素

            String name = "mumu";
//            request
//response
             // application
             pageContext.getServletContext();

            //response.getOutputStream();
//            session

//config----pageContext.getServletConfig();
//page=this
//pageContext



//       out ,pageContext,exception  定义成错误页，不然出不来。默认是false。


pageContext.setAttribute("","");
pageContext.getAttribute("");
pageContext.setAttribute("name","mumu",PageContext.APPLICATION_SCOPE);

        %><!-- 语法元素-->


        <%=name %>


</body>
</html>
