<%--
  Created by IntelliJ IDEA.
  User: ZhangNima
  Date: 2019/3/30
  Time: 23:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>后台左侧导航页面</title>

    <style type="text/css">
        .dc {
            display: none;
            margin-left: 10px;
        }
    </style>

    <script language="javascript">
        function test(node) {
            var li = node.parentNode;
            var e = li.getElementsByTagName('div')[0];
            e.style.display = e.style.display === 'block' ? 'none' : 'block' ;
        }
    </script>
</head>

<body>
<ul style="margin-top: 50px">
    <li>
        <a href="javascript:void(0)" onclick="test(this)">分类管理</a>
        <div class="dc">
            <a href="${pageContext.request.contextPath }/manager/addCategory" target="right">添加分类</a><br/>
            <a href="${pageContext.request.contextPath }/manager/ListCategory"  target="right">查看分类</a><br/>
        </div>

    </li>
    <li style="margin-top: 50px">
        <a href="javascript:void(0)" onclick="test(this)">图书管理</a>
        <div class="dc">
            <a href="${pageContext.request.contextPath }/manager/addBook"  target="right">添加图书</a><br/>
            <a href="${pageContext.request.contextPath }/manager/listBook"  target="right">查看图书</a>
        </div>

    <%--</li>
    <li style="margin-top: 50px">
        <a href="javascript:void(0)" onclick="test(this)">订单管理</a>
        <div class="dc">
            <a href="${pageContext.request.contextPath }/manager/OrderServlet?method=list&state=false"  target="right">待处理订单</a><br/>
            <a href="${pageContext.request.contextPath }/manager/OrderServlet?method=list&state=true"  target="right">已发货订单</a><br/>
        </div>
    </li>--%>
</ul>
</body>
</html>
