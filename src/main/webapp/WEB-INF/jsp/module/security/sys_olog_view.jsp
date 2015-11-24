<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <%-- 导航 --%>
        <li><a href="sys_olog_list.html">系统日志列表</a></li>
        <li class="active"><a href="javascript:"> 查看系统日志</a></li>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal" role="form">
            <div class="panel panel-default">
                <div class="panel-heading">
                    系统日志
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">模块</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.module}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">模块名称</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.moduleName}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">操作</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.action}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">操作名称</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.actionName}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">执行时间</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.executeMilliseconds}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">操作时间</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                <fmt:formatDate value="${item.operateTime}" pattern="yyyy-MM-dd" />
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">操作员</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.operateUser}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">操作员ID</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.operateUserId}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">请求参数</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.requestParameters}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">操作结果</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                <c:choose>
                                        <c:when test="${item.operateResult == 1}">
                                            <span class="label label-info">成功</span>
                                        </c:when>
                                        <c:when test="${item.operateResult == 2}">
                                            <span class="label label-info">失败</span>
                                        </c:when>
                                </c:choose>
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">消息</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.operateMessage}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">客户端信息</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.clientInformations}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">备注</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.descn}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">平台标识</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.tag}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">会话ID</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.sessionId}
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