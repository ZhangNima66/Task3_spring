<%--
  Created by IntelliJ IDEA.
  User: ZhangNima
  Date: 2019/4/1
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>分类列表</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>
</head>

<body>
<script type="text/javascript">
    $(function () {
        $('#form_add').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                'name': {
                    validators: {
                        notEmpty: {
                            message: '类名不能为空'
                        },
                        stringLength: {
                            min: 2,
                            max: 30,
                            message: '分类限定2-15个字符'
                        }
                    }
                }
            }
        });
    });
</script>
<div class="container">
    <div class="text-center">
        <h2>图书列表</h2>
    </div>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <td>分类名称</td>
            <td>分类描述</td>
            <td>操作</td>
        </tr>
        </thead>
        <c:forEach var="category" items="${categories}">
            <c:if test="${category.id == id}">

                <tr>
                    <td colspan="3">
                        <div class="col-sm-12">
                            <form id="form_add" class="form-horizontal" role="form" style="margin: 0"
                                  action="${pageContext.request.contextPath}/manager/updateCategory/${category.id}"
                                  method="post">
                                <div class="form-group col-sm-5" style="margin-bottom: 0">
                                    <label for="name" class="control-label sr-only">类名：</label>
                                    <div>
                                        <input class="form-control" type="text" id="name" name="name"
                                               value="${category.name }">
                                    </div>
                                </div>
                                <div class="form-group col-sm-5" style="margin-bottom: 0; margin-right: 15px">
                                    <label for="description" class="control-label sr-only">描述：</label>
                                    <div>
                                        <input class="form-control" type="text" id="description" name="description"
                                               value="${category.description }">
                                    </div>
                                </div>
                                <div class="form-group col-sm-2" style="margin-bottom: 0">
                                    <div>
                                        <button class="btn btn-default" type="submit">提交更改</button>
                                        <a class="btn btn-default" href="${pageContext.request.contextPath}/manager/ListCategory">取消</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </td>
                </tr>
                <%--   </form>--%>
            </c:if>
            <c:if test="${category.id != id}">
                <tr>
                    <td>${category.name }</td>
                    <td>${category.description }</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/manager/updateCategory/${category.id}">修改</a>
                        <a href="${pageContext.request.contextPath}/manager/deleteCategory/${category.id}"
                           onclick="return confirm('是否确定删除?')">删除</a>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</div>
</body>
</html>
