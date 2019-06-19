<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>添加书籍</title>
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
                    validators:{
                        notEmpty:{
                            message:'书名不能为空'
                        },
                        stringLength:{
                            min:2,
                            max:30,
                            message:'书名限定2-30个字符'
                        }
                    }
                },
                'author': {
                    validators: {
                        notEmpty: {
                            message: '作者不能为空'
                        },
                        stringLength: {
                            min: 1,
                            max: 20,
                            message: '作者名最长20个字符'
                        }
                    }
                },
                price: {
                    validators: {
                        notEmpty: {
                            message: '售价不能为空'
                        },
                        regexp: { //使用正则
                            regexp: /^[+-]?([0-9]*\.?[0-9]+|[0-9]+\.?[0-9]*)([eE][+-]?[0-9]+)?$/, //验证是不是数字
                            message: '请输入正确的价格'
                        }
                    }
                },
                publicationdate:{
                    validators: {
                        notEmpty: {
                            message: '出版日期不能为空'
                        }
                    }
                },
                image_pic: {
                    validators: {
                        notEmpty: {
                            message: '请选择一张图片'
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
                <h3 class="panel-title">添加书籍</h3>
            </div>
            <div class="panel-body">
                <div class="alert alert-danger" style="padding: 8px 15px 8px;display: ${error_message ==null||error_message==''?'none':'block'}">
                    错误：${error_message}
                </div>
                <form id="form_add" class="form-horizontal" role="form" style="margin: 0"
                      action="${pageContext.request.contextPath }/manager/add"
                      method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label" style="font-size: larger">书名：</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" id="name" name="name" value="${book.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="author" class="col-sm-2 control-label" style="font-size: larger">作者：</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" id="author" name="author" value="${book.author}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="price" class="col-sm-2 control-label" style="font-size: larger">售价：</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" id="price" name="price" value="${book.price}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="publicationdate" class="col-sm-2 control-label" style="font-size: larger">日期：</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="date" id="publicationdate" name="publicationdate"
                                   value="<fmt:formatDate value="${book.publicationdate}" pattern="yyyy-MM-dd"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="image_pic" class="col-sm-2 control-label" style="font-size: larger">图片：</label>
                        <div class="col-sm-10">
                            <input type="file" id="image_pic" name="image_pic">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" style="font-size: larger">标签：</label>
                        <div class="col-sm-10 form-inline">
                            <c:forEach items="${tags}" var="tag">
                                <label class="checkbox-inline" style="margin: 0 10px 0 0">
                                    <input type="checkbox" ${fn:contains(fn:join(check_tag,','),tag)?'checked':''}
                                           value="${tag}" name="tags">${tag}
                                </label>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="categoryId" class="col-sm-2 control-label" style="font-size: larger">分类：</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="categoryId" name="categoryId">
                                <c:forEach var="category" items="${categoryAll}">
                                    <option value="${category.id }" ${book.categoryId ==category.id ?'selected':''}>${category.name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" style="font-size: larger">发布：</label>
                        <div class="col-sm-10 form-inline">
                            <label class="radio-inline">
                                <input type="radio" name="isdel" value="0" ${book.isdel!=1?'checked':''}> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isdel" value="1" ${book.isdel==1?'checked':''}> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group" style="margin: 0 -15px 0">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default" style="width: 100px">添加</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
