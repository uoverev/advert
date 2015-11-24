<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <%-- 导航 --%>
        <li><a href="sys_user_list.html">系统用户表列表</a></li>
        <feinno-security2:hasPermission name="/module/security/sys_user_add.html">
        <li><a href="sys_user_add.html"><i class="fa fa-plus"></i> 增加系统用户表</a></li>
        </feinno-security2:hasPermission>
        <li class="active"><a href="javascript:"> 查看系统用户表</a></li>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal" role="form">
            <div class="panel panel-default">
                <div class="panel-heading">
                    系统用户表
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">登录用户名</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.username}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">用户所属角色</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.role.roleName}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">电子邮件</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.email}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">状态</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                <c:choose>
                                        <c:when test="${item.status == 1}">
                                            <span class="label label-info">有效</span>
                                        </c:when>
                                        <c:when test="${item.status == 0}">
                                            <span class="label label-info">无效</span>
                                        </c:when>
                                        <c:when test="${item.status == 2}">
                                            <span class="label label-info">锁定</span>
                                        </c:when>
                                </c:choose>
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">描述</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.descn}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">创建时间</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                <fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">最后登录时间</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                <fmt:formatDate value="${item.lastLoginDate}" pattern="yyyy-MM-dd" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">最后登录IP</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.lastLoginIp}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">过期时间</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                <fmt:formatDate value="${item.expiredDate}" pattern="yyyy-MM-dd" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">解锁时间</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                <fmt:formatDate value="${item.unlockDate}" pattern="yyyy-MM-dd" />
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 col-md-2 control-label"></label>
                <div class="col-sm-10">
                    <p class="form-control-static"><a href="javascript:history.go(-1);" class="btn btn-primary">返回</a></p>
                </div>
            </div>
        </form>
    </div>
</div>