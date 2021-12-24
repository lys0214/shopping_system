<%--
  Created by IntelliJ IDEA.
  User: 胜胜
  Date: 2021/11/30
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <li>
        <a href="#" target="_blank"><i class="icon-home"></i>系统访问次数${applicationScope.countTimes}</a>
    </li>
    <li>
        <a><i class="icon-random"></i>在线人数：<span class="badge">${applicationScope.onLine}</span></a>
    </li>
    <li>
        <a><i class="icon-user"></i>用户:${sessionScope.realName}</a>
    </li>
    <li>
        <a><i class="icon-bell-alt"></i>系统消息</a>
    </li>
    <li>
        <a href="login.jsp" id="JsSignOut"><i class="icon-signout"></i>安全退出</a>
    </li>
</ul>
</body>
</html>
