<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="col-xs-12 col-sm-9 col-lg-10">
	<ul class="nav nav-tabs">
		<%-- 导航 --%>
		<c:if test="${queryType=='zczx' }">
			<li><a href="infoInformation_list_zczx.html">资讯基本信息列表</a></li>
			<li <c:if test="${empty item}"> class="active"</c:if>><a
				href="infoInformation_add.html?queryType=zczx"><i
					class="fa fa-plus"></i> 增加资讯基本信息</a></li>
		</c:if>
		<c:if test="${queryType=='pxsx' }">
			<li><a href="infoInformation_list_pxsx.html">资讯基本信息列表</a></li>
			<li <c:if test="${empty item}"> class="active"</c:if>><a
				href="infoInformation_add.html?queryType=pxsx"><i
					class="fa fa-plus"></i> 增加资讯基本信息</a></li>
		</c:if>
		<li class="active"><a href="javascript:"> 查看资讯基本信息</a></li>
	</ul>
	<%-- 详细 --%>
	<div class="clearfix">
		<form class="form-horizontal" role="form">
			<div class="panel panel-default">
				<div class="panel-heading">资讯基本信息</div>
				<div class="panel-body">
					<div class="form-group">
						<label class="col-xs-12 col-sm-3 col-md-2 control-label">资讯分类</label>
						<div class="col-sm-9">
							<p class="form-control-static">
								<c:forEach var="e" items="${allCategorys }">
									<c:if test="${item.category == e.key }">
										<span class="label label-info">${e.value }</span>
									</c:if>
								</c:forEach>
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-3 col-md-2 control-label">资讯标题</label>
						<div class="col-sm-9">
							<p class="form-control-static">${item.title}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-3 col-md-2 control-label">是否置顶</label>
						<div class="col-sm-9">
							<p class="form-control-static">${item.stick > 0 ? '已置顶' : '未置顶'}
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-3 col-md-2 control-label">上线时间</label>
						<div class="col-sm-9">
							<p class="form-control-static">
								<fmt:formatDate value="${item.lineTime}" pattern="yyyy-MM-dd" />
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-3 col-md-2 control-label">资讯简介</label>
						<div class="col-sm-9">
							<p class="form-control-static">${item.introduction}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-3 col-md-2 control-label">咨询图片</label>
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
						<label class="col-xs-12 col-sm-3 col-md-2 control-label">推荐位图片</label>
						<div class="col-sm-9">
							<p class="form-control-static">
								<c:if test="${!empty item.recommendationImageUrl1}">
									<img src="${headerUrl}${item.recommendationImageUrl1}"
										class="img-thumbnail" style="width: 140px; height: 140px;">
								</c:if>
							</p>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-xs-12 col-sm-3 col-md-2 control-label">推荐位2图片</label>
						<div class="col-sm-9">
							<p class="form-control-static">
								<c:if test="${!empty item.recommendationImageUrl2}">
									<img src="${headerUrl}${item.recommendationImageUrl2}"
										class="img-thumbnail" style="width: 140px; height: 140px;">
								</c:if>
							</p>
						</div>
					</div> --%>
					<div class="form-group">
						<label class="col-xs-12 col-sm-3 col-md-2 control-label">资讯内容</label>
						<div class="col-sm-9">
							<p class="form-control-static">${item.content}</p>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-12 col-sm-3 col-md-2 control-label"></label>
				<div class="col-sm-10">
					<p class="form-control-static">
						<a href="javascript:history.go(-1);" class="btn btn-primary">返回</a>
					</p>
				</div>
			</div>
		</form>
	</div>
</div>