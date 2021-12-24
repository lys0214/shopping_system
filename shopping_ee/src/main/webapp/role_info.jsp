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
                        <h2 class="title">角色信息管理</h2>
                        <p class="title-description">
                        </p>
                    </header>
                    <hr>
                    <button class="btn btn-primary" style="margin-right: 500px">添加角色</button>
                    <label >
                        角色：
                        <input type="text" name="roleName" class="" width="300px">
                    </label>
                    <button class="btn-info">搜索</button>
                </section>


                <table class="table table-bordered table-striped table-hover">
                    <thead>
                    <tr>
                        <th><input type="checkbox" name="ck"></th>
                        <th>角色ID</th>
                        <th>角色名称</th>
                        <th>角色权限</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.roles}" var="a">
                        <tr class="cen">
                            <td><input type="checkbox" name="ck" value="${a.roleId }"> </td>
                            <td>${a.roleId}</td>
                            <td >${a.roleName }</td>
                            <td>${a.roleMemo }</td>
                            <td>
                                <button class="btn-warning">编辑</button>
                                <button class="btn-danger">删除</button>
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