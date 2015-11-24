<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12 col-sm-9 col-lg-10">
    <%-- 导航 --%>
    <ul class="nav nav-tabs">
        <li class="active"><a href="sys_role_list.html">系统角色表列表</a></li>
        <feinno-security2:hasPermission name="/module/security/sys_role_add.html">
        <li><a href="sys_role_add.html"><i class="fa fa-plus"></i> 增加系统角色表</a></li>
        </feinno-security2:hasPermission>
    </ul>

    <%-- 查询条件 --%>
    <div class="clearfix">
        <div class="panel panel-info">
            <div class="panel-heading">筛选</div>
            <div class="panel-body">
                <form action="" method="get" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">主键ID</label>

                        <div class="col-sm-8">
                            <input class="form-control" name="id" id="id" type="text" value="${param.id}">
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
                        <th>主键ID</th>
                        <th>角色名称</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.roleName}</td>
                        <td>
                            <feinno-security2:hasPermission name="/module/security/sys_role_view.html">
                            <a href="sys_role_view.html?id=${item.id}"class="btn btn-default btn-sm" title="查看">查看</a>
                            </feinno-security2:hasPermission>
                            <feinno-security2:hasPermission name="/module/security/sys_role_update.html">
                            <a href="sys_role_update.html?id=${item.id}"class="btn btn-default btn-sm" title="编辑"><i class="fa fa-edit"> 编辑</i></a>
                            </feinno-security2:hasPermission>
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