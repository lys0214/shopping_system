<%--
  Created by IntelliJ IDEA.
  User: 胜胜
  Date: 2021/11/30
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--引入本地样式文件--%>
    <link rel="stylesheet" href="../css/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body>
<h2>
    <a href="#" class="InitialPage"><i class="icon-dashboard"></i>数据概况</a>
</h2>
<ul>
    <li>
        <dl>
            <dt>
                <i class="icon-columns"></i>账户管理<i class="icon-angle-right"></i>
            </dt>
            <dd>
                <a href="AccountServlet?flag=selectAll">所有账户</a>
            </dd>

            <dd>
                <a href="countServlet?flag=selectRoles">卖家管理</a>
            </dd>
        </dl>
    </li>
    <li>
        <dl>
            <dt>
                <i class="icon-columns"></i>角色管理<i class="icon-angle-right"></i>
            </dt>
            <dd>
                <a href="role?flag=selectAll">所有角色</a>
            </dd>
        </dl>
    </li>
    <li>
        <dl>
            <dt>
                <i class="icon-columns"></i>系统记录<i class="icon-angle-right"></i>
            </dt>
            <dd>
                <a href="log?flag=showByPage">登录履历</a>
            </dd>

        </dl>
    </li>
    <li>
        <dl>
            <dt>
                <i class="icon-columns"></i>测试<i class="icon-angle-right"></i>
            </dt>
            <dd>
                <i class="fa fa-line-chart"> &nbsp;</i><a href="chart?flag=toChart">可视大图</a>
            </dd>
        </dl>
    </li>
</ul>
</body>
</html>
