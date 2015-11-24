<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12 col-sm-9 col-lg-10">
    <%-- 导航 --%>
    <ul class="nav nav-tabs">
        <li class="active"><a href="sys_olog_list.html">系统日志列表</a></li>
    </ul>

    <%-- 查询条件 --%>
    <div class="clearfix">
        <div class="panel panel-info">
            <div class="panel-heading">筛选</div>
            <div class="panel-body">
                <form action="" method="get" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">模块</label>

                        <div class="col-sm-8">
                            <input class="form-control" name="module" id="module" type="text" value="${param.module}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">模块名称</label>

                        <div class="col-sm-8">
                            <input class="form-control" name="moduleName" id="moduleName" type="text" value="${param.moduleName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">操作</label>

                        <div class="col-sm-8">
                            <input class="form-control" name="action" id="action" type="text" value="${param.action}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">操作员</label>

                        <div class="col-sm-8">
                            <input class="form-control" name="operateUser" id="operateUser" type="text" value="${param.operateUser}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">操作员ID</label>

                        <div class="col-sm-8">
                            <input class="form-control" name="operateUserId" id="operateUserId" type="text" value="${param.operateUserId}">
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
                        <th>主键</th>
                        <th>模块</th>
                        <th>模块名称</th>
                        <th>操作</th>
                        <th>操作名称</th>
                        <th>执行时间</th>
                        <th>操作时间</th>
                        <th>操作员</th>
                        <th>操作员ID</th>
                        <th>请求参数</th>
                        <th>操作结果</th>
                        <th>消息</th>
                        <th>客户端信息</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.module}</td>
                        <td>${item.moduleName}</td>
                        <td>${item.action}</td>
                        <td>${item.actionName}</td>
                        <td>${item.executeMilliseconds}</td>
                        <td><fmt:formatDate value="${item.operateTime}" pattern="yyyy-MM-dd" /></td>
                        <td>${item.operateUser}</td>
                        <td>${item.operateUserId}</td>
                        <td>${item.requestParameters}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.operateResult == 1}">
                                    <span class="label label-info">成功</span>
                                </c:when>
                                <c:when test="${item.operateResult == 2}">
                                    <span class="label label-info">失败</span>
                                </c:when>
                            </c:choose>
                        </td>
                        <td>${item.operateMessage}</td>
                        <td>${item.clientInformations}</td>
                        <td>
                            <a href="sys_olog_view.html?id=${item.id}"class="btn btn-default btn-sm" title="查看">查看</a>
                            <a href="sys_olog_update.html?id=${item.id}"class="btn btn-default btn-sm" title="编辑"><i class="fa fa-edit"> 编辑</i></a>
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