<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/3/8
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>住院处</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/servlet/ProcServlet">
    住院号：<input type="text" value="" name="zyh">
    <input type="submit" value="提交">
</form>
<h1></h1>
</body>
</html>
