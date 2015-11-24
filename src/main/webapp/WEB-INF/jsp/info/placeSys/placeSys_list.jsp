<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script>
    require(['ConfirmWindow'], function($){
    });
</script>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <%-- 导航 --%>
    <ul class="nav nav-tabs">
        <li class="active"><a href="placeSys_list.html">推荐位关系数据表列表</a></li>
    </ul>

    <%-- 查询条件 --%>
    <div class="clearfix">
        <div class="panel panel-info">
            <div class="panel-heading">筛选</div>
            <div class="panel-body">
                <form action="" method="get" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">关联数据名称</label>

                        <div class="col-sm-8">
                            <input class="form-control" name="refName" id="refName" type="text" value="${param.refName}">
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">课程类型</label>
                        <div class="col-sm-8">
                            <select name="placeCode" id="placeCode" class="form-control">
                                <option value="">所有</option>
                                <c:forEach var="olPlace" items="${olPlaceList}">
                                	<option value="${olPlace.code}" ${param.placeCode == olPlace.code?'selected':''}>${olPlace.name}</option>
                                </c:forEach>
                            </select>
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
                        <th>位置名称</th>
                        <th>关联数据名称</th>
                        <th width="80px">排序号</th>
                        <th width="100px">发布状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                        <td title="${item.placeName}">${item.placeName}</td>
                        <td title="${item.refName}">${item.refName}</td>
                        <td>${item.sortNo}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.status == 0}">
                                    <span class="label label-info">未发布</span>
                                </c:when>
                                <c:when test="${item.status == 1}">
                                    <span class="label label-info">已发布</span>
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                            <a href="placeSys_view.html?id=${item.id}"class="btn btn-default btn-sm" title="查看">查看</a>
                            <a href="placeSys_update.html?id=${item.id}"class="btn btn-default btn-sm" title="编辑"><i class="fa fa-edit"> 编辑</i></a>
                            <a href="javascript:void(0);"class="btn btn-default btn-sm confirm" url ="placeSys_delete.html?id=${item.id}" title="删除"><i class="fa fa-remove"> 删除</i></a>

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