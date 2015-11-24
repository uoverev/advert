<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script>
    require(['ConfirmWindow'], function($){
    });
</script>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <%-- 导航 --%>
    <ul class="nav nav-tabs">
        <li class="active"><a href="place_list.html">前台位置数据表列表</a></li>
    </ul>

    <%-- 查询条件 --%>
    <div class="clearfix">
        <div class="panel panel-info">
            <div class="panel-heading">筛选</div>
            <div class="panel-body">
                <form action="" method="get" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">位置名</label>

                        <div class="col-sm-8">
                            <input class="form-control" name="name" id="name" type="text" value="${param.name}">
                        </div>
                        <div class="col-xs-12 col-sm-2 col-lg-1">
                            <button class="btn btn-default"><i class="fa fa-search"></i> 搜索</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <%--列表--%>
    <div class="panel panel-default">
        <div class="table-responsive panel-body">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>位置名</th>
                        <th>状态</th>
                        <th>更新时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                        <td>${item.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.status == 0}">
                                    <span class="label label-info">失效</span>
                                </c:when>
                                <c:when test="${item.status == 1}">
                                    <span class="label label-info">生效</span>
                                </c:when>
                            </c:choose>
                        </td>
                        <td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd" /></td>
                        <td>
                            <a href="place_view.html?id=${item.id}"class="btn btn-default btn-sm" title="查看">查看</a>
                            <a href="place_update.html?id=${item.id}"class="btn btn-default btn-sm" title="编辑"><i class="fa fa-edit"> 编辑</i></a>
                            <a href="javascript:void(0)"class="btn btn-default btn-sm confirm" title="删除" url="place_delete.html?id=${item.id}"><i class="fa fa-remove"> 删除</i></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%--分页--%>
            <feinno-frame:pagination/>
        </div>
    </div>
</div>