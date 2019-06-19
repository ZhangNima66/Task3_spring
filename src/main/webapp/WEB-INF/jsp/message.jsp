<%--
  Created by IntelliJ IDEA.
  User: maxuezhi
  Date: 2019/5/9
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>消息</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="col-sm-offset-3 col-sm-6 col-sm-offset-3" style="margin-top: 100px">
        <div class="panel panel-success" style="display:${success_message ==null||success_message==''?'none':'block'};">
            <div class="panel-heading">
                <h3 class="panel-title">${success_message}</h3>
            </div>
            <div class="panel-body">
                <a href="${pageContext.request.contextPath}/manager/addBook">继续添加</a>
                <a href="${pageContext.request.contextPath}/manager/listBook">查看书籍列表</a>
            </div>
        </div>
        <div class="panel panel-success" style="display:${c_success_message ==null||c_success_message==''?'none':'block'};">
            <div class="panel-heading">
                <h3 class="panel-title">${c_success_message}</h3>
            </div>
            <div class="panel-body">
                <a href="${pageContext.request.contextPath}/manager/addCategory">继续添加</a>
                <a href="${pageContext.request.contextPath}/manager/ListCategory">查看种类列表</a>
            </div>
        </div>
        <div class="panel panel-success" style="display:${update_message ==null||update_message==''?'none':'block'};">
            <div class="panel-heading">
                <h3 class="panel-title">${update_message}</h3>
            </div>
            <div class="panel-body">
                <a href="${pageContext.request.contextPath}/manager/listBook">返回书籍列表</a>
            </div>
        </div>
    </div>
</div>


</body>
</html>
