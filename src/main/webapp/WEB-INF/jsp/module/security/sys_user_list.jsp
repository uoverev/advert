<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12 col-sm-9 col-lg-10">
    <%-- 导航 --%>
    <ul class="nav nav-tabs">
        <li class="active"><a href="sys_user_list.html">系统用户列表</a></li>
        <feinno-security2:hasPermission name="/module/security/sys_user_add.html">
        <li><a href="sys_user_add.html"><i class="fa fa-plus"></i> 增加系统用户</a></li>
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
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">登录用户名</label>

                        <div class="col-sm-8">
                            <input class="form-control" name="username" id="username" type="text" value="${param.username}">
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
                        <th width="60">主键ID</th>
                        <th width="110">登录用户名</th>
                        <th width="100">用户角色组</th>
                        <th width="60">状态</th>
                        <th width="110">创建时间</th>
                        <th width="110">最后登录时间</th>
                        <th width="110">过期时间</th>
                        <th width="110">解锁时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.username}</td>
                        <td>${item.role.roleName}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.status == 1}">
                                    <span class="label label-info">启用</span>
                                </c:when>
                                <c:when test="${item.status == 0}">
                                    <span class="label label-info">禁用</span>
                                </c:when>
                                <c:when test="${item.status == 2}">
                                    <span class="label label-info">锁定</span>
                                </c:when>
                            </c:choose>
                        </td>
                        <td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd" /></td>
                        <td><fmt:formatDate value="${item.lastLoginDate}" pattern="yyyy-MM-dd" /></td>
                        <td><fmt:formatDate value="${item.expiredDate}" pattern="yyyy-MM-dd" /></td>
                        <td><fmt:formatDate value="${item.unlockDate}" pattern="yyyy-MM-dd" /></td>
                        <td>
                            <feinno-security2:hasPermission name="/module/security/sys_user_view.html">
                            <a href="sys_user_view.html?id=${item.id}"class="btn btn-default btn-sm" title="查看">查看</a>
                            </feinno-security2:hasPermission>
                            <feinno-security2:hasPermission name="/module/security/sys_user_update.html">
                            <a href="sys_user_update.html?id=${item.id}"class="btn btn-default btn-sm" title="编辑"><i class="fa fa-edit"> 编辑</i></a>
                            </feinno-security2:hasPermission>
                            <feinno-security2:hasPermission name="/module/security/sys_user_pass.html">
                            <a href="sys_user_pass.html?id=${item.id}"class="btn btn-default btn-sm" title="密码重置"><i class="fa fa-key"> 密码重置</i></a>
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