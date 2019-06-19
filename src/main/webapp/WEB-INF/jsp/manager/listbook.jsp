<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>图书列表</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>
    <%--<script src="${pageContext.request.contextPath}/js/bootstrapValidator.js"></script>--%>
    <style>
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            appearance: none;
            margin: 0;
        }
    </style>
</head>
<body>
<script type="text/javascript">
    function gotoPage(currentPage) {
        if (currentPage == null || currentPage <= 0 || currentPage > ${bean.totalPage}) {
            alert('请输入正确的页码');
            return;
        }
        var pageSize = document.getElementById("pageSize").value;
        if (pageSize !== '${bean.pageSize}') {
            currentPage = 1;
        }
        var form = document.getElementsByClassName('form-horizontal');
        form[0].action = '${pageContext.request.contextPath}/manager/listBook/' + currentPage + '/' + pageSize;
        form[0].submit();
    }
    function newRefresh() {
        var inputs = $('.panel-body').find("input");
        for (var i = 0; i < inputs.length; i++) {
            var inp = inputs[i];
            if (inp.type === 'checkbox') {
                inp.checked = '';
            } else if (inp.type === 'radio') {
                if (inp.value === '') {
                    inp.checked = 'checked';
                }
            } else {
                inputs[i].value = '';
            }
        }
        $('#form_0').bootstrapValidator('resetForm');
    }
    function noFuc(num) {
        num.value = num.value >= 0 ? num.value : 0;
    }
    function revalidate(validator,$p){
        var b = validator.isValidField($p);
        if (!b)
        {
            validator.revalidateField($p);
        }
    }

   $(function () {
        $('#form_0').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                "paramMap['price']": {
                    validators: {
                        callback: {
                            message: '价格范围有误',
                            callback: function (value, validator, $field) {
                                var p1 = document.getElementById('price1');
                                var p2 = document.getElementById('price2');
                                var $p;
                                if ($field.context.id === 'price2') {
                                    $p = $('#price1');
                                    revalidate(validator,$p);
                                }
                                if ($field.context.id === 'price1') {
                                    $p = $('#price2');
                                    revalidate(validator,$p);
                                }
                                if (p1.value !== '' && p2.value !== '') {
                                    return parseInt(p2.value) >= parseInt(p1.value);
                                } else {
                                    return true;
                                }
                            }
                        }
                    }
                },
                "paramMap['publicationDate']": {
                    validators: {
                        callback: {
                            message: '日期范围有误',
                            callback: function (value, validator, $field) {
                                var p1 = document.getElementById('publicationDate1');
                                var p2 = document.getElementById('publicationDate2');
                                var $p;
                                if ($field.context.id === 'publicationDate2') {
                                    $p = $('#publicationDate1');
                                    revalidate(validator,$p);
                                }
                                if ($field.context.id === 'publicationDate1') {
                                    $p = $('#publicationDate2');
                                    revalidate(validator,$p);
                                }
                                if (p1.value !== '' && p2.value !== '') {
                                    return new Date(p2.value) >= new Date(p1.value);
                                } else {
                                    return true;
                                }
                            }
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
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8 panel panel-default" style="padding: 0 0">
            <div class="panel-heading">
                <h3 class="panel-title">查询</h3>
            </div>
            <div class="panel-body">
                <form id="form_0" class="form-horizontal" role="form"
                      action="${pageContext.request.contextPath}/manager/listBook" method="post">
                    <div class="row">
                        <%--<div class="col-sm-2"></div>--%>
                        <div class="row form-group col-sm-6" style="margin:0 0 15px">
                            <label class="col-sm-3 control-label" style="font-size: larger" for="name">名称：</label>
                            <div class="col-sm-9">
                                <input class="form-control" type="text" id="name" value="${bean.paramMap['name'][0]}"
                                       name="paramMap['name']">
                            </div>
                        </div>
                        <div class="row form-group col-sm-6" style="margin:0 0 15px">
                            <label class="col-sm-3 control-label" style="font-size: larger" for="author">作者：</label>
                            <div class="col-sm-9">
                                <input class="form-control" type="text" id="author"
                                       value="${bean.paramMap['author'][0]}" name="paramMap['author']">
                            </div>
                        </div>
                        <%--<div class="col-sm-2"></div>--%>
                    </div>
                    <div id="select" class="collapse">
                        <div class="row">
                            <div class="form-group col-sm-12" style="margin:0 0 15px -97.5px">
                                <label class="col-sm-3 control-label" style="font-size: larger" for="price1">价格：</label>
                                <div id="price_input" class="col-sm-9 form-inline">
                                    <input class="form-control" type="number" onclick="noFuc(this)" id="price1"
                                           value="${bean.paramMap['price'][0]}" name="paramMap['price']"
                                           style="width: 150px"> ~
                                    <input class="form-control" type="number" onclick="noFuc(this)" id="price2"
                                           value="${bean.paramMap['price'][1]}" name="paramMap['price']"
                                           style="width: 150px">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-sm-12" style="margin:0 0 15px -97.5px">
                                <label class="col-sm-3 control-label" for="publicationDate1" style="font-size: larger">日期：</label>
                                <div class="col-sm-9 form-inline">
                                    <input class="form-control" type="date" id="publicationDate1"
                                           value="${bean.paramMap['publicationDate'][0]}"
                                           name="paramMap['publicationDate']">
                                    到
                                    <input class="form-control" type="date" id="publicationDate2"
                                           value="${bean.paramMap['publicationDate'][1]}"
                                           name="paramMap['publicationDate']">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-sm-12" style="margin:0 0 15px -97.5px">
                                <label class="col-sm-3 control-label" style="font-size: larger">标签：</label>
                                <div class="col-sm-9 form-inline">
                                    <c:forEach items="${tags}" var="tag">
                                        <label class="checkbox-inline" style="margin: 0 10px 0 0">
                                            <input type="checkbox" ${fn:contains(fn:join(bean.paramMap['tags'],','),tag)?'checked':''}
                                                   value="${tag}" name="paramMap['tags']">${tag}
                                        </label>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-sm-12" style="margin:0 0 15px -97.5px">
                                <label class="col-sm-3 control-label" style="font-size: larger">分类：</label>
                                <div class="col-sm-9 form-inline">
                                    <c:forEach items="${categories}" var="category" varStatus="statu">
                                        <label class="checkbox-inline" style="margin: 0 10px 0 0">
                                            <input type="checkbox" ${fn:contains(fn:join(bean.paramMap['categoryId'],','),category.id)?'checked':''}
                                                   value="${category.id}" name="paramMap['categoryId']">${category.name}
                                        </label>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-sm-12" style="margin:0 0 15px -97.5px">
                                <label class="col-sm-3 control-label" style="font-size: larger">发布：</label>
                                <div class="col-sm-9 form-inline">
                                    <label class="radio-inline">
                                        <input type="radio" name="paramMap['isDel']" value="" checked> 全部
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="paramMap['isDel']"
                                               value="0" ${bean.paramMap['isDel'][0]=='0'?'checked':''}> 是
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="paramMap['isDel']"
                                               value="1" ${bean.paramMap['isDel'][0]=='1'?'checked':''}> 否
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="row">
                            <div class="form-group col-sm-12 text-left" style="margin:0 0 15px -35.5px">
                                <button type="submit" class="btn btn-default" style="width: 100px">查&nbsp;&nbsp;询
                                </button>
                                <button type="button" class="btn btn-default" onClick="newRefresh()">重置</button>
                                <button type="button" class="btn btn-link" style="float: right;margin-right: -50px"
                                        data-toggle="collapse" data-target="#select">展开
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-sm-2"></div>
    </div>
    <div class="col-md-12 text-center" style="margin-bottom: 10px">
        共[${bean.totalRecord }]条记录，
        每页
        <select id="pageSize" onchange="gotoPage(${bean.currentPage})">
            <option value="5" ${fn:contains(bean.pageSize,5)?'selected = "selected"':''}>5</option>
            <option value="10" ${fn:contains(bean.pageSize,10)?'selected = "selected"':''}>10</option>
            <option value="20" ${fn:contains(bean.pageSize,20)?'selected = "selected"':''}>20</option>
        </select>
        条， 共[${bean.totalPage }]页，当前第[${bean.currentPage }]页
    </div>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <td>书名</td>
            <td>作者</td>
            <td>价格</td>
            <td>出版日期</td>
            <td>描述</td>
            <td>图片</td>
            <td>是否发布</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${bean.books}">
            <tr>
                <td>${book.name }</td>
                <td>${book.author }</td>
                <td>${book.price }</td>
                <td><fmt:formatDate value="${book.publicationdate}" pattern="yyyy-MM-dd"/></td>
                <td>${book.description }</td>
                <td><a href="/upload/${book.image}">查看图片</a></td>
                <td>${book.isdel==0?'是':'否'}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/manager/updateBook/${book.id}">修改</a>
                    <a href="${pageContext.request.contextPath}/manager/deleteBook/${book.id}"
                       onclick="return confirm('是否确定删除?')">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="col-sm-12 text-center">
        <div style="display: inline-block;">
            <ul class="pagination">
                <li class="${bean.previousPage==bean.currentPage?'disabled':''}">
                    <a href="javascript:void(0)" onclick="gotoPage(${bean.previousPage})">上一页</a>
                </li>
                <c:forEach var="pageNum" items="${bean.pageBar }">
                    <li class="${pageNum==bean.currentPage?'disabled':''}">
                        <a href="javascript:void(0)" onclick="gotoPage(${pageNum})">${pageNum}</a>
                    </li>
                </c:forEach>
                <li class="${bean.nextPage==bean.currentPage?'disabled':''}">
                    <a href="javascript:void(0)" onclick="gotoPage(${bean.nextPage})">下一页</a>
                </li>
            </ul>
        </div>
        <div style="display:inline-block;margin-bottom: 20px">
            <div class="input-group" style="width: 90px;">
                <input type="text" class="form-control" id="pageNum">
                <span class="input-group-btn">
                    <button class="btn btn-default" style="color: #337ac7"
                            onclick="gotoPage(document.getElementById('pageNum').value)">GO</button>
                </span>
            </div>
        </div>
    </div>


</div>
</body>
