<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <%-- 导航 --%>
        <li><a href="placeData_list.html">广告位列表</a></li>
        <li><a href="placeData_add.html"><i class="fa fa-plus"></i> 增加广告位</a></li>
        <li class="active"><a href="javascript:"> 查看广告位</a></li>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal" role="form">
            <div class="panel panel-default">
                <div class="panel-heading">
                    广告位
                </div>
                <div class="panel-body">
                	<div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">位置名称</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.codeName}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">显示内容</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.content}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">提示文字</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.alt}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">跳转地址</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
                                ${item.jumpUrl}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">图片</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
								<c:if test="${!empty item.imgUrl}">
									<img src="${headerUrl}${item.imgUrl}" class="img-thumbnail"
										style="width: 140px; height: 140px;">
								</c:if>
							</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">开始时间</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
								<fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">排序号</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">
								${item.sortNo }
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