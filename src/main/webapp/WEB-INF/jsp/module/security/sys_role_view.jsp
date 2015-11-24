<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <%-- 导航 --%>
        <li><a href="sys_role_list.html">系统角色表列表</a></li>
        <feinno-security2:hasPermission name="/module/security/sys_role_add.html">
        <li><a href="sys_role_add.html"><i class="fa fa-plus"></i> 增加系统角色表</a></li>
        </feinno-security2:hasPermission>
        <li class="active"><a href="javascript:"> 查看系统角色表</a></li>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal" role="form">
            <div class="panel panel-default">
                <div class="panel-heading">
                    系统角色表
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">角色名称</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.roleName}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">描述信息</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.descn}
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