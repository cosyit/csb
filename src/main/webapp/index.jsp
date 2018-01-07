<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<!-- page指令，指示jsp容器转换器如何翻译成.java-->
<html>
<head>
<jsp:useBean id="today" class="java.util.Date"></jsp:useBean>
    <title><%=today%></title>
</head>
<body>

<!-- 资源引入发生在页面转换的时候。.jsp   -发生在这个过程上- .java     .class
对扩展名无所谓：反正把你当成字符串，放入到.java 文件中。
-->
<%@include file="/WEB-INF/index2.jsp"%>

<%
//    <jsp:useBean> 源码翻译
    Date today2=null;
    //1.首先取，看能否取到。
    today2=(Date)pageContext.getAttribute("toady",PageContext.REQUEST_SCOPE);
    if(today2==null){
        today2=new java.util.Date();
        pageContext.setAttribute("today2",today2,PageContext.REQUEST_SCOPE);
    }
%>

<jsp:useBean id="employee_zsb" class="com.cosyit.entity.Employee"/>
<jsp:setProperty name="employee_zsb" property="firstName" value="尚斌"></jsp:setProperty>

小徒弟名字:<jsp:getProperty name="employee_zsb" property="firstName"></jsp:getProperty>

<%-- 资源引入发生在请求的时候。--%>
<jsp:include page="/WEB-INF/demo1.jspf"></jsp:include>
<jsp:forward page="/WEB-INF/forward.jsp"></jsp:forward>
</body>
</html>
