<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>后台管理系统-HTML5后台管理系统</title>
    <meta name="keywords"  content="设置关键词..." />
    <meta name="description" content="设置描述..." />
    <meta name="author" content="DeathGhost" />
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <link rel="icon" href="images/icon/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script src="javascript/jquery.js"></script>
    <script src="javascript/plug-ins/customScrollbar.min.js"></script>
    <script src="javascript/plug-ins/echarts.min.js"></script>
    <script src="javascript/plug-ins/layerUi/layer.js"></script>
    <script src="editor/ueditor.config.js"></script>
    <script src="editor/ueditor.all.js"></script>
    <script src="javascript/plug-ins/pagination.js"></script>
    <script src="javascript/public.js"></script>
</head>
<body>
<div class="main-wrap">
    <div class="side-nav">
        <div class="side-logo">
            <div class="logo">
				<span class="logo-ico">
					<i class="i-l-1"></i>
					<i class="i-l-2"></i>
					<i class="i-l-3"></i>
				</span>
                <strong>购物商城后台管理</strong>
            </div>
        </div>

        <nav class="side-menu content mCustomScrollbar" data-mcs-theme="minimal-dark">
            <jsp:include page="component/sideMenu.jsp"/>
        </nav>

        <footer class="side-footer">© DeathGhost 版权所有</footer>

    </div>
    <div class="content-wrap">
        <header class="top-hd">
            <div class="hd-lt">
                <a class="icon-reorder"></a>
            </div>
            <div class="hd-rt">
                <div class="hd-rt">
                    <%--	顶部导航栏--%>
                    <jsp:include page="component/top.jsp"/>
                </div>
            </div>
        </header>
        <main class="main-cont content mCustomScrollbar">
            <div class="page-wrap">
                <!--开始::内容-->
                <section class="page-hd">
                    <header>
                        <h2 class="title">账户信息管理</h2>
                        <p class="title-description">
                        </p>
                    </header>
                    <hr>
                </section>

                <table class="table table-bordered table-striped table-hover">
                    <thead>
                    <tr>
                        <th><input type="checkbox" name="ck"></th>
                        <th>账户名字</th>
                        <th>真实名字</th>
                        <th>账户电话</th>
                        <th>账户角色</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.list }" var="a">
                        <tr class="cen">
                            <td><input type="checkbox" name="ck" value="${a.accountId }"> </td>
                            <td >${a.accountName }</td>
                            <td>${a.accountRealName }</td>
                            <td>${a.accountPhone }</td>
                            <td>${a.role.roleName }</td>
                            <td>
                                <a title="编辑" class="mr-5">编辑</a>
                                <a title="详情" class="mr-5">详情</a>
                                <a title="删除">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <!--开始::结束-->
            </div>
        </main>
        <footer class="btm-ft">

        </footer>
    </div>
</div>

</body>
</html>