<%--
  Created by IntelliJ IDEA.
  User: ZhangNima
  Date: 2019/3/31
  Time: 0:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>添加分类</title>
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
    <div class="col-sm-offset-3 col-sm-6 col-sm-offset-3" style="margin-top: 100px">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">添加分类</h3>
            </div>
            <div class="panel-body">
                <div class="alert alert-danger"
                     style="padding: 8px 15px 8px;display: ${error_message ==null||error_message==''?'none':'block'}">
                    错误：${error_message}
                </div>
                <form id="form_add" class="form-horizontal" role="form" style="margin: 0"
                      action="${pageContext.request.contextPath}/manager/addCategory/add" method="post">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label" style="font-size: larger">类名：</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" id="name" name="name" value="${category.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-sm-2 control-label" style="font-size: larger">描述：</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="description" name="description"
                                      rows="3">${category.description}</textarea>
                        </div>
                    </div>
                    <div class="form-group" style="margin: 0 -15px 0">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default" style="width: 100px">添加分类</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>