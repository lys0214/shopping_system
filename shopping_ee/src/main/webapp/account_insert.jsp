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
    <script src="js/jquery-3.6.js"></script>
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
                        <h2 class="title">新建用户信息</h2>
                        <p class="title-description">

                        </p>
                    </header>
                    <hr>
                </section>
                <form action="AccountServlet?flag=add" method="post">
                    <div class="form-group-col-2">
                        <div class="form-label">账户名：</div>
                        <div class="form-cont">
                            <input type="tel" id="accountName" name="accountName"  class="form-control form-boxed" style="width:300px;" >
                            <span id="mess" class="word-aux"></span>
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">密码：</div>
                        <div class="form-cont">
                            <input type="tel" name="accountPwd"  class="form-control form-boxed"  style="width:300px;">
                        </div>
                    </div>
                    <%--  隐藏用户ID--%>
                    <input type="hidden"  name="accountId">
                    <div class="form-group-col-2">
                        <div class="form-label">真实名字：</div>
                        <div class="form-cont">
                            <input type="tel"   name="accountRealName" class="form-control form-boxed"  style="width:300px;">
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">手机号：</div>
                        <div class="form-cont">
                            <input type="tel" name="accountPhone" placeholder="" class="form-control form-boxed"  style="width:300px;">
                            <span class="word-aux"><i class="icon-warning-sign"></i>请正确输入11位手机号码</span>
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">生日：</div>
                        <div class="form-cont">
                            <input type="date" name="accountBirthday"  class="form-control form-boxed"  style="width:300px;">
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label">角色：</div>
                        <div class="form-cont">
                            <select class="form-control form-boxed" name="roleId"  style="width:300px;">
                                <option>请选择</option>
                                <c:forEach items="${requestScope.roleList}" var="r">
                                    <option value="${r.roleId()}">
                                            ${r.roleName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group-col-2">
                        <div class="form-label"></div>
                        <div class="form-cont">
                            <input type="submit" class="btn btn-primary" value="新建" />
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
<%--<script>
    var xmlHttpRequest;
    function checkName() {
        var accountName=document.getElementById("accountName").value;
        console.log(accountName)
    //    创建XMLHttpRequest对象
        if (window.ActiveXObject) {
            //    IE内核
            xmlHttpRequest = new ActiveXObject("Micrsoft.XMLHTTP");
        } else {
            xmlHttpRequest = new XMLHttpRequest();
        }
    //    发送路径
        var url = "AccountServlet?flag=checkName&accountName=" + accountName;
    //    打开路径
        xmlHttpRequest.open("GET",url, true);
        xmlHttpRequest.send(null);
    //    callBack自定义函数，当状态改变，调用该函数，没有小括号
        xmlHttpRequest.onreadystatechange=callBack;
        /*
        * readyState用来返回当前请求的状态
        * 0未初始化， 1打开状态，2 发送状态 3.正在接收 4.已加载
        * status属性用来返回服务器响应的状态码
        * 200 OK 表示响应正常 404未找到
        * onreadyStateChange事件触发，当任何一个状态发生变化，都会触发到
        *responseText响应文本格式
        * responseXML XML格式*/
    }
    function  callBack(){
    //    完全响应正常
        if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
        //    获取响应数据，然后做判断
            var msg=xmlHttpRequest.responseText;
            if (msg == "ok") {
                document.getElementById("mess").innerHTML = "<i class='icon-warning-sign'></i><span style='color: forestgreen'>可以注册</span>";
            } else {
                document.getElementById("mess").innerHTML = "<i class='icon-warning-sign'></i><span style='color: red'>该用户名已经被注册</span>";
            }
        }
    }
</script>--%>
<%--调用ajax方法检测用户名是否已经存在--%>
<script>
    $(function (){
        $("#accountName").blur(function (){
            $.ajax({
                type:"get",
                url:'AccountServlet',
                async:true,
                data:{
                    flag:"checkName",
                    accountName:$("#accountName").val()
                },
                timeout:2000,
                dataType:"text",
                success:function (data,status,xhr){
                    if (data=='ok'){
                        $("#mess").html("用户名可用")
                    }else {
                        $("#mess").html("用户名已被注册")
                    }
                },
                error:function (xhr,errorStatus,errorText){

                },
            //    最后都会执行
                complete:function (){
                    console.log("都会执行")
                }
            })
        })
    })
</script>
</html>