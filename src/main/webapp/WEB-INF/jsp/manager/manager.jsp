<%--
  Created by IntelliJ IDEA.
  User: ZhangNima
  Date: 2019/3/30
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>后台管理</title>

</head>

<frameset rows="150px,*">
    <frame name="head"
           src="${pageContext.request.contextPath }/manager/head">
    <frameset cols="200px,*">
        <frame name="left"
               src="${pageContext.request.contextPath }/manager/left">
        <frame name="right" src="">
    </frameset>
</frameset>

</html>
