<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<form action=${pageContext.request.contextPath}${pageContext.request.servletPath}/servlet/bazyServlet method="post">
    病人id：<input type="text" name="brid" value=""><br>
    主页id：<input type="text" name="zyid" value=""><br>
    1病人id：<input type="text" name="brid1" value=""><br>
    1主页id：<input type="text" name="zyid1" value=""><br>
    费用和：<input type="text" name="fyh"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
