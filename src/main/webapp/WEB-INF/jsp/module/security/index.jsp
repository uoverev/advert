<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <li class="active"><a href="javascript:">账号概况</a></li>
    </ul>

    <div class="clearfix welcome-container">
        <div class="page-header">
            <h4><i class="fa fa-comments"></i> 帐号基础信息</h4>
        </div>
        <div class="account">
            <div class="panel panel-default row">
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
                                ${item.descn}&nbsp;
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
                                <fmt:formatDate value="${item.expiredDate}" pattern="yyyy-MM-dd" /> &nbsp;
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">解锁时间</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                <fmt:formatDate value="${item.unlockDate}" pattern="yyyy-MM-dd" /> &nbsp;
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            require(['jquery', 'util'], function($, u){
                $('.account p a').each(function(){
                    u.clip(this, $(this).text());
                });
            });
        </script>
    </div>
</div>