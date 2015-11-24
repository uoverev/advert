<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <%-- 导航 --%>
        <li><a href="test_ulist.html">test_user_info列表</a></li>
        <li><a href="test_uadd.html"><i class="fa fa-plus"></i> 增加test_user_info</a></li>
        <li class="active"><a href="javascript:"> 查看test_user_info</a></li>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal" role="form">
            <div class="panel panel-default">
                <div class="panel-heading">
                    test_user_info
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">姓名</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.name}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">所在省</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.province}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">所在市</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.city}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">所在区</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.distinct}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">详细地址</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.detail}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">身份证号</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.identity}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">性别</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.sex}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">车辆品牌</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.carB}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">购车时间</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                <fmt:formatDate value="${item.buyT}" pattern="yyyy-MM-dd" />
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