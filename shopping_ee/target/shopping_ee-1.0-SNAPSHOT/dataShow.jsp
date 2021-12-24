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
    <script src="javascript/public.js"></script><script src="javascript/jquery.js"></script>
    <script src="javascript/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="js/amcharts.js" type="text/javascript"></script>
    <script src="js/serial.js" type="text/javascript"></script>
    <script src="js/pie.js" type="text/javascript"></script>
    <style>
        .dataStatistic {
            width: 400px;
            height: 200px;
            border: 1px solid #ccc;
            margin: 0 auto;
            margin: 10px;
            overflow: hidden
        }

        #cylindrical {
            width: 400px;
            height: 200px;
            margin-top: -15px
        }

        #line {
            width: 400px;
            height: 200px;
            margin-top: -15px
        }

        #pie {
            width: 400px;
            height: 200px;
            margin-top: -15px
        }
    </style>
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

                <div class="panel panel-default">
                    <section class="page-hd">
                        <header>
                            数据可视化
                        </header>
                        <hr>
                    </section>
                    <div class="panel-bd" style="text-align: center">
                        <!--统计图-->
                        <section style="overflow:hidden">
                            <!--柱状图-->
                            <div class="dataStatistic fl">
                                <div id="cylindrical">
                                </div>
                            </div>
                            <!--线性图-->
                            <div class="dataStatistic fl">
                                <div id="line">
                                </div>
                            </div>
                            <!--饼状图-->
                            <div class="dataStatistic fl">
                                <div id="pie">
                                </div>
                            </div>
                        </section>
                    <%--    --%>

                    </div>
                </div>
            </div>
        </main>
        <footer class="btm-ft">

        </footer>
    </div>
</div>

</body>
<script type="text/javascript">
    $(document).ready(function(e) {
        $.ajax({
            // 加载文档  查询数据
            type:"get",
            url:"http://localhost:8080/shopping_ee_war_exploded/AccountServlet",
            async:true,
            data:{flag:"getAll"},
            timeout:2000,
            // dataType:"text",//json
            dataType:"json",
            success:function(data,status,xhr){
                console.log(data);
                // 调用生成方法
                GetSerialChart(data["dayTimes"]);
                MakeChart(data["roleCounts"]);
                makeAmCharts(data["selectTimes"]);
            }
        });
    });

    //折线图
    function makeAmCharts(json){
        var chart = new AmCharts.AmSerialChart();
        chart.dataProvider = json;
        chart.categoryField = "name";
        chart.angle = 30;
        chart.depth3D = 20;
        //标题
        //chart.addTitle("3D折线图Demo", 15);
        var graph = new AmCharts.AmGraph();
        chart.addGraph(graph);
        graph.valueField = "value";
        //背景颜色透明度
        graph.fillAlphas = 0.3;
        //类型
        graph.type = "line";
        //圆角
        graph.bullet = "round";
        //线颜色
        graph.lineColor = "#8e3e1f";
        //提示信息
        graph.balloonText = "[[name]]共登录系统 [[value]]次";
        var categoryAxis = chart.categoryAxis;
        categoryAxis.autoGridCount = false;
        categoryAxis.gridCount = json.length;
        categoryAxis.gridPosition = "start";
        chart.write("line");

    }

    //多角度集成


    //柱状图
    function GetSerialChart(json) {
        chart = new AmCharts.AmSerialChart();
        chart.dataProvider = json;
        //json数据的key
        chart.categoryField = "name";
        //不选择
        chart.rotate = false;
        //值越大柱状图面积越大
        chart.depth3D = 10;
        //柱子旋转角度角度
        chart.angle = 30;
        var mCtCategoryAxis = chart.categoryAxis;
        mCtCategoryAxis.axisColor = "#efefef";
        //背景颜色透明度
        mCtCategoryAxis.fillAlpha = 0.5;
        //背景边框线透明度
        mCtCategoryAxis.gridAlpha = 0;
        mCtCategoryAxis.fillColor = "#efefef";
        var valueAxis = new AmCharts.ValueAxis();
        //左边刻度线颜色
        valueAxis.axisColor = "#ccc";
        //标题
        valueAxis.title = "每日登录统计";
        //刻度线透明度
        valueAxis.gridAlpha = 0.2;
        chart.addValueAxis(valueAxis);
        var graph = new AmCharts.AmGraph();
        graph.title = "value";
        graph.valueField = "value";
        graph.type = "column";
        //鼠标移入提示信息
        graph.balloonText = "时间:[[category]],人数:[[value]]";
        //边框透明度
        graph.lineAlpha = 0.3;
        //填充颜色
        graph.fillColors = "#b9121b";
        graph.fillAlphas = 1;

        chart.addGraph(graph);

        // CURSOR
        var chartCursor = new AmCharts.ChartCursor();
        chartCursor.cursorAlpha = 0;
        chartCursor.zoomable = false;
        chartCursor.categoryBalloonEnabled = false;
        chart.addChartCursor(chartCursor);

        chart.creditsPosition = "top-right";

        //显示在Main div中
        chart.write("cylindrical");
    }

    //饼图
    //根据json数据生成饼状图，并将饼状图显示到div中
    function MakeChart(value) {
        chartData = eval(value);
        //饼状图
        chart = new AmCharts.AmPieChart();
        chart.dataProvider = chartData;
        //标题数据
        chart.titleField = "name";
        //值数据
        chart.valueField = "value";
        //边框线颜色
        chart.outlineColor = "#fff";
        //边框线的透明度
        chart.outlineAlpha = .8;
        //边框线的狂宽度
        chart.outlineThickness = 1;
        chart.depth3D = 20;
        chart.angle = 30;
        chart.write("pie");
    }
</script>
</html>