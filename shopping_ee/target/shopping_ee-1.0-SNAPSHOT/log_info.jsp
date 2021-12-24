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
                        <h2 class="title">系统登录记录</h2>
                        <h5>共 <span style="font-size: larger">${requestScope.pages.allRecord}</span>条</h5>
                        <p class="title-description">
                        </p>
                    </header>
                    <hr>
                </section>

                <table class="table table-bordered table-striped table-hover">
                    <thead>
                    <tr>
                        <th><input type="checkbox" name="ck"></th>
                        <th>NO</th>
                        <th>账户名</th>
                        <th>真实名字</th>
                        <th>IP</th>
                        <th>时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.logs}" var="a">
                        <tr class="cen">
                            <td><input type="checkbox" name="ck" value="${a.accountId }"> </td>
                            <td >${a.logId }</td>
                            <td>${a.account.accountName }</td>
                            <td>${a.account.accountRealName }</td>
                            <td>${a.logIp }</td>
                            <td>${a.logTime}</td>
                            <td>
                                <button class="btn-warning">编辑</button>
                                <button class="btn-danger">删除</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%--<div class="pagination" style="text-align: center"></div>--%>
                <!--开始::结束-->
                <div style="text-align: center;">
                    <button class="btn-info" onclick="javascript:window.location.href='log?flag=showByPage&current=1'">首页</button>
                    <button class="btn-info" onclick="javascript:window.location.href='log?flag=showByPage&current=${requestScope.pages.showPage-1}'"
            ${requestScope.pages.showPage==1?"disabled":""}
                    >上一页</button>
                    <c:forEach step="1" begin="1" end="${requestScope.pages.pageCount}" var="a">
                            <a href="log?flag=showByPage&current=${a}"  ${requestScope.pages.showPage==a?"style='color:yellow;background-color:red'":""}
                            >${a}</a>
                        </c:forEach>
                    <button class="btn-info" onclick="javascript:window.location.href='log?flag=showByPage&current=${requestScope.pages.showPage+1}'" ${requestScope.pages.showPage==requestScope.pages.pageCount?"disabled":""}>下一页</button>
                    <button class="btn-info" onclick="javascript:window.location.href='log?flag=showByPage&current=${requestScope.pages.pageCount}'" >尾页</button>

                </div>
            </div>
            <%--<div style="text-align: center">
                &lt;%&ndash;    分页&ndash;%&gt;
                <button onclick="javascript:window.location.href='log?flag=showByPage&current=1'">首页</button>
                <button>上一页</button>
                <c:forEach begin="1" end="${requestScope.page.pageCount}" var="a" step="1">
                    <a href="log?flag=showByPage&current=${a}">${a}</a>
                </c:forEach>
                <button>下一页</button>
                <button onclick="javascript:window.location.href='log?flag=showByPage&current=${requestScope.page.pageCount}'">尾页</button>
            </div>--%>

        </main>

        <footer class="btm-ft">

        </footer>
    </div>
</div>

</body>
<script>
    $(".pagination").createPage({
        pageCount:5,
        current:1,
        backFn:function(p){
            var request = new XMLHttpRequest();
            request.open("GET", "http://localhost:8080/shopping_ee_war_exploded/log?flag=showByPage&current="+p);
            request.send();
            request.onload = function () {
                console.log(request.responseText)
                console.log(request.readyState)
            };

        }
    });
</script>
</html>