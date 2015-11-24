<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <%-- 导航 --%>
        <li><a href="place_list.html">前台位置数据表列表</a></li>
        <li class="active"><a href="javascript:"> 查看前台位置数据表</a></li>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal" role="form">
            <div class="panel panel-default">
                <div class="panel-heading">
                    前台位置数据表
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">位置名</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.name}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">状态</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                <c:choose>
                                        <c:when test="${item.status == 0}">
                                            <span class="label label-info">失效</span>
                                        </c:when>
                                        <c:when test="${item.status == 1}">
                                            <span class="label label-info">生效</span>
                                        </c:when>
                                </c:choose>
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">更新时间</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                <fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd" />
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