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
            <h2 class="title">用户信息编辑</h2>
            <p class="title-description">

            </p>
          </header>
          <hr>
        </section>
        <form action="AccountServlet?flag=sure_update" method="post">
        <div class="form-group-col-2">
          <div class="form-label">账户名：</div>
          <div class="form-cont">
            <input type="tel" name="accountName"  class="form-control form-boxed" style="width:300px;" disabled value="${requestScope.account.accountName}">
            <span class="word-aux"><i class="icon-warning-sign"></i>账户名不允许修改</span>
          </div>
        </div>
        <div class="form-group-col-2">
          <div class="form-label">密码：</div>
          <div class="form-cont">
            <input type="tel" name="accountPwd"  class="form-control form-boxed" value="${requestScope.account.accountPwd}" style="width:300px;">
          </div>
        </div>
        <%--  隐藏用户ID--%>
          <input type="hidden" value="${requestScope.account.accountId}" name="accountId">
        <div class="form-group-col-2">
          <div class="form-label">真实名字：</div>
          <div class="form-cont">
            <input type="tel" name="accountRealName" class="form-control form-boxed" value="${requestScope.account.accountRealName}" style="width:300px;">
          </div>
        </div>
        <div class="form-group-col-2">
          <div class="form-label">手机号：</div>
          <div class="form-cont">
            <input type="tel" name="accountPhone" placeholder="" class="form-control form-boxed" value="${requestScope.account.accountPhone}" style="width:300px;">
            <span class="word-aux"><i class="icon-warning-sign"></i>请正确输入11位手机号码</span>
          </div>
        </div>
        <div class="form-group-col-2">
          <div class="form-label">生日：</div>
          <div class="form-cont">
            <input type="date" name="accountBirthday"  class="form-control form-boxed" value="${requestScope.account.accountBirthday}" style="width:300px;">
          </div>
        </div>
        <div class="form-group-col-2">
          <div class="form-label">角色：</div>
          <div class="form-cont">
            <select class="form-control form-boxed" name="roleId"  style="width:300px;">
              <c:forEach items="${requestScope.roleList}" var="r">
                <option value="${r.roleId}" ${requestScope.account.role.roleId==r.roleId?'selected':''}>
                  ${r.roleName}
                </option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="form-group-col-2">
          <div class="form-label"></div>
          <div class="form-cont">
            <input type="submit" class="btn btn-primary" value="修改" />
            <input onclick="javascript:window.location.href='AccountServlet?flag=selectAll'" type="button" class="btn btn-primary" value="返回" />
          </div>
        </div>
        </form>
        <!--开始::结束-->
      </div>
    </main>
    <footer class="btm-ft">

    </footer>
  </div>
</div>

</body>
</html>