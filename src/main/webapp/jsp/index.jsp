<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="icon" href="../img/favicon.ico">
    <link rel="stylesheet" href="../boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="../jqgrid/css/jquery-ui.css">
    <link rel="stylesheet" href="../jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <script src="../boot/js/bootstrap.min.js"></script>
    <script src="../jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="../jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="../boot/js/ajaxfileupload.js"></script>
    <script src="../kindeditor/kindeditor-all-min.js"></script>
    <script src="../kindeditor/lang/zh-CN.js"></script>
    <script src="../echarts/echarts.min.js"></script>
    <script src="../echarts/china.js"></script>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js​"></script>
    <script>
        $(function () {

            $("#aa").click(function () {
                location.href="${app}/admin/edit"
            })
        })
    </script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">

            <p class="navbar-brand" href="#">持名法州管理系统</p>

        </div>

        <div class="navbar-right btn-lg">
            欢迎:<span class="small" style="color:blue">${sessionScope.admin.username}</span>&nbsp;&nbsp;&nbsp;退出登录
            <span class="glyphicon glyphicon-log-out" id="aa" style="cursor:pointer;"></span>
        </div>

    </div><!-- /.container-fluid -->
</nav>

<div class="container-fluid">
    <div class="container-fluid">
        <div class="row">
            <!--左侧手风琴-->
            <div class="col-sm-2">
                <div class="panel-group" id="accordion">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseOne">
                                    用户管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse ">
                            <div class="panel-body">
                                <a href="javascript:$('#lay-right').load('user.jsp')">用户注册分布图</a>
                            </div>
                            <div class="panel-body">
                                <a href="javascript:$('#lay-right').load('user1.jsp')">用户近七天注册量</a>
                            </div>
                            <div class="panel-body">
                                <a href="javascript:$('#lay-right').load('lts.jsp')">聊天室</a>
                            </div>

                        </div>

                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseThree">
                                    上师管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseThree" class="panel-collapse collapse">
                            <div class="panel-body">
                                <a href="">上师列表</a>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion"
                                   href="#collapse">
                                    文章管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapse" class="panel-collapse collapse">
                            <div class="panel-body">
                                <a href="javascript:$('#lay-right').load('article.jsp')">文章列表</a>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseThree1">
                                    专辑管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseThree1" class="panel-collapse collapse">
                            <div class="panel-body">
                                <a href="javascript:$('#lay-right').load('ablum.jsp')">专辑列表</a>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseThree2">
                                    轮播图管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseThree2" class="panel-collapse collapse">
                            <div class="panel-body">
                                <a href="javascript:$('#lay-right').load('lunbo.jsp')">轮播图列表</a>
                            </div>
                        </div>

                    </div>

                </div>
            </div>

            <div id="lay-right" class="col-sm-10">

                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header well-lg">

                            <p class="navbar-brand" href="#">欢迎来到持名法州后台管理系统</p>
                        </div>

                    </div><!-- /.container-fluid -->
                </nav>
                <div>
                    <img src="../img/shouye.jpg" class="img-responsive">
                </div>
            </div>

        </div>
    </div><br><br>

</div>
<nav class="navbar-default navbar-fixed-bottom">
    <p class="text-center">@百知教育 baizhi@zparkhr.com.cn</p>
</nav>
</body>

</html>