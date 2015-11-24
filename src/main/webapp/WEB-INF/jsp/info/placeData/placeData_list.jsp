<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script>
require(['ConfirmWindow','bootstrapQ'], function($){
	
});
</script>

<div class="col-xs-12 col-sm-9 col-lg-10">
    <%-- 导航 --%>
    <ul class="nav nav-tabs">
        <li class="active"><a href="placeData_list.html">广告位列表</a></li>
        <li><a href="placeData_add.html"><i class="fa fa-plus"></i> 增加广告位</a></li>
    </ul>

    <%-- 查询条件 --%>
    <div class="clearfix">
        <div class="panel panel-info">
            <div class="panel-heading">筛选</div>
            <div class="panel-body">
                <form action="" method="get" class="form-horizontal" role="form">
                	<div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">位置分类</label>

                        <div class="col-sm-8">
                            <select name="code" class="form-control">
                                <option value="">所有</option>
                                <c:forEach var="e" items="${allCodes}">
                               		<option value="${e.key}" ${param.code == e.key?'selected':''}>${e.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">开始时间</label>

                        <div class="col-sm-8">
                            <script type="text/javascript">
                                require(["daterangepicker"], function($){
                                    $(function(){
                                        var elm = $("#startTime");
                                        $(elm).daterangepicker({
                                            startDate: $(elm).prev().prev().val(),
                                            endDate: $(elm).prev().val(),
                                            format: "YYYY-MM-DD",
                                            showDropdowns: true
                                        }, function(start, end){
                                            $(elm).find(".date-title").html(start.toDateStr() + " 至 " + end.toDateStr());
                                            $(elm).prev().prev().val(start.toDateStr());
                                            $(elm).prev().val(end.toDateStr());
                                        });
                                    });
                                });
                            </script>
                            <input name="beginStartTime" type="hidden" value="<fmt:formatDate value="${beginStartTime}" pattern="yyyy-MM-dd" />">
                            <input name="endStartTime" type="hidden" value="<fmt:formatDate value="${endStartTime}" pattern="yyyy-MM-dd" />">
                            <button class="btn btn-default daterange" id="startTime" type="button" data-original-title="" title=""><span class="date-title"><fmt:formatDate value="${beginStartTime}" pattern="yyyy-MM-dd" /> 至 <fmt:formatDate value="${endStartTime}" pattern="yyyy-MM-dd" /></span> <i class="fa fa-calendar"></i></button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">状态</label>

                        <div class="col-sm-8">
                            <select name="status" id="status" class="form-control">
                                <option value="">所有</option>
                                <option value="0"<c:if test="${param.status eq '0'}"> selected="selected"</c:if>>不可用</option>
                                <option value="1"<c:if test="${param.status eq '1'}"> selected="selected"</c:if>>可用</option>
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
                        <th>开始时间</th>
                        <th>排序号</th>
                        <th>状态</th>
                        <th width="330px;">操作</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                        <td>${item.codeName}</td>
                        <td><fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd" /></td>
                        <td>${item.sortNo}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.status == 0}">
                                    <span class="label label-info">不可用</span>
                                </c:when>
                                <c:when test="${item.status == 1}">
                                    <span class="label label-info">可用</span>
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                            <a href="placeData_view.html?id=${item.id}"class="btn btn-default btn-sm" title="查看">查看</a>
                            <a href="placeData_update.html?id=${item.id}"class="btn btn-default btn-sm" title="编辑"><i class="fa fa-edit"> 编辑</i></a>
                            <c:if test="${item.status eq 0 }">
                            	<a href="javascript:void(0)" class="btn btn-default btn-sm confirm" title="可用" url="placeData_status.html?id=${item.id}"><i class="fa fa-chevron-up"> 可用</i></a>
                            </c:if>
                            <c:if test="${item.status eq 1 }">
                            	<a href="javascript:void(0)" class="btn btn-default btn-sm confirm" title="不可用" url="placeData_status.html?id=${item.id}"><i class="fa fa-chevron-down"> 不可用</i></a>
                            </c:if>
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