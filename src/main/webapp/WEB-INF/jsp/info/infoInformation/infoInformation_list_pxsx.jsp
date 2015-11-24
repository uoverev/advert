<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script>
require(['ConfirmWindow','bootstrapQ'], function($){
	//##推荐方法，通用
	var recommendUrl = '/module/info/placeSys/placeSys_addObj.html';
	// 推荐到创业宝典主推荐位
	var recommendListValuablePrimaryCode = "list_valuable_primary";
	$('#recommendListValuablePrimary').click(function(){
		var checkbox = $("input[name='blankCheckbox']:checked");
		if(checkbox.length == 0){
			// 不做任何操作
			return false;
		}
		if($(checkbox[0]).attr("category") != 5){
			bootstrapQ.alert('只有创业宝典能够被推荐');
			return false;
		}
		if(checkbox.length > 1){
			bootstrapQ.alert('一次只能推荐一条数据');
			return false;
		}
		if($(checkbox[0]).attr("status") != 3){
			bootstrapQ.alert('只有已上线的资讯才能被推荐');
			return false;
		}
		var refId = $(checkbox[0]).attr("id");
		var refName = $(checkbox[0]).attr("title");
		$.ajax({
			url : recommendUrl,
			type : 'post',
			cache : false,
			data : {"placeCode":recommendListValuablePrimaryCode,"refId":refId,"refName":refName},			
			dataType : "json",
			success : function(result) {
				bootstrapQ.alert(result.message);
			}
		})
	})
	// 推荐到创业宝典次推荐位
	var recommendListValuableSecondaryCode = "list_valuable_secondary";
	$('#recommendListValuableSecondary').click(function(){
		var checkbox = $("input[name='blankCheckbox']:checked");
		if(checkbox.length == 0){
			// 不做任何操作
			return false;
		}
		if($(checkbox[0]).attr("category") != 5){
			bootstrapQ.alert('只有创业宝典能够被推荐');
			return false;
		}
		if(checkbox.length > 1){
			bootstrapQ.alert('一次只能推荐一条数据');
			return false;
		}
		if($(checkbox[0]).attr("status") != 3){
			bootstrapQ.alert('只有已上线的资讯才能被推荐');
			return false;
		}
		var refId = $(checkbox[0]).attr("id");
		var refName = $(checkbox[0]).attr("title");
		$.ajax({
			url : recommendUrl,
			type : 'post',
			cache : false,
			data : {"placeCode":recommendListValuableSecondaryCode,"refId":refId,"refName":refName},			
			dataType : "json",
			success : function(result) {
				bootstrapQ.alert(result.message);
			}
		})
	})
	// 推荐到创业课堂
	var recommendListClassCode = "list_class";
	$('#recommendListClass').click(function(){
		var checkbox = $("input[name='blankCheckbox']:checked");
		if(checkbox.length == 0){
			// 不做任何操作
			return false;
		}
		if($(checkbox[0]).attr("category") != 6){
			bootstrapQ.alert('只有创业课堂能够被推荐');
			return false;
		}
		if(checkbox.length > 1){
			bootstrapQ.alert('一次只能推荐一条数据');
			return false;
		}
		if($(checkbox[0]).attr("status") != 3){
			bootstrapQ.alert('只有已上线的资讯才能被推荐');
			return false;
		}
		var refId = $(checkbox[0]).attr("id");
		var refName = $(checkbox[0]).attr("title");
		$.ajax({
			url : recommendUrl,
			type : 'post',
			cache : false,
			data : {"placeCode":recommendListClassCode,"refId":refId,"refName":refName},			
			dataType : "json",
			success : function(result) {
				bootstrapQ.alert(result.message);
			}
		})
	})
	// 推荐到创业计划书
	var recommendListPlanCode = "list_plan";
	$('#recommendListPlan').click(function(){
		var checkbox = $("input[name='blankCheckbox']:checked");
		if(checkbox.length == 0){
			// 不做任何操作
			return false;
		}
		var category = $(checkbox[0]).attr("category");
		if(category != 71 && category != 72 && category != 73 && category != 74){
			bootstrapQ.alert('只有创业计划书能够被推荐');
			return false;
		}
		if(checkbox.length > 1){
			bootstrapQ.alert('一次只能推荐一条数据');
			return false;
		}
		if($(checkbox[0]).attr("status") != 3){
			bootstrapQ.alert('只有已上线的资讯才能被推荐');
			return false;
		}
		var refId = $(checkbox[0]).attr("id");
		var refName = $(checkbox[0]).attr("title");
		$.ajax({
			url : recommendUrl,
			type : 'post',
			cache : false,
			data : {"placeCode":recommendListPlanCode,"refId":refId,"refName":refName},			
			dataType : "json",
			success : function(result) {
				bootstrapQ.alert(result.message);
			}
		})
	})
	//推荐到实训基地
	var recommendListBaseCode = "list_base";
	$('#recommendListBase').click(function(){
		var checkbox = $("input[name='blankCheckbox']:checked");
		if(checkbox.length == 0){
			// 不做任何操作
			return false;
		}
		if($(checkbox[0]).attr("category") != 8){
			bootstrapQ.alert('只有实训基地能够被推荐');
			return false;
		}
		if(checkbox.length > 1){
			bootstrapQ.alert('一次只能推荐一条数据');
			return false;
		}
		if($(checkbox[0]).attr("status") != 3){
			bootstrapQ.alert('只有已上线的资讯才能被推荐');
			return false;
		}
		var refId = $(checkbox[0]).attr("id");
		var refName = $(checkbox[0]).attr("title");
		$.ajax({
			url : recommendUrl,
			type : 'post',
			cache : false,
			data : {"placeCode":recommendListBaseCode,"refId":refId,"refName":refName},			
			dataType : "json",
			success : function(result) {
				bootstrapQ.alert(result.message);
			}
		})
	})
	//推荐到首页的实训基地
	var recommendIndexBaseCode = "index_base";
	$('#recommendIndexBase').click(function(){
		var checkbox = $("input[name='blankCheckbox']:checked");
		if(checkbox.length == 0){
			// 不做任何操作
			return false;
		}
		if($(checkbox[0]).attr("category") != 8){
			bootstrapQ.alert('只有实训基地能够被推荐');
			return false;
		}
		if(checkbox.length > 1){
			bootstrapQ.alert('一次只能推荐一条数据');
			return false;
		}
		if($(checkbox[0]).attr("status") != 3){
			bootstrapQ.alert('只有已上线的资讯才能被推荐');
			return false;
		}
		var refId = $(checkbox[0]).attr("id");
		var refName = $(checkbox[0]).attr("title");
		$.ajax({
			url : recommendUrl,
			type : 'post',
			cache : false,
			data : {"placeCode":recommendIndexBaseCode,"refId":refId,"refName":refName},			
			dataType : "json",
			success : function(result) {
				bootstrapQ.alert(result.message);
			}
		})
	})
});

//删除资讯
function del(id, status){
	$.ajax({
		url : "/module/info/placeSys/placeSys_getNum.html",
		type : 'post',
		cache : false,
		data : {"refId":id},			
		dataType : "json",
		success : function(result) {
			if(result.success){
				if(result.entity == "0"){
					bootstrapQ.confirm("是否删除？",function(){
						window.location.href = "infoInformation_online.html?id="+id+"&status=" + status;
					});
				}else{
					bootstrapQ.alert("该资讯已被推荐，删除对应推荐位信息后才能删除该信息");	
				}
			}else{
				bootstrapQ.alert(result.message);	
			}
		}
	})
}
</script>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <%-- 导航 --%>
    <ul class="nav nav-tabs">
        <li class="active"><a href="infoInformation_list_pxsx.html">资讯基本信息列表</a></li>
        <li><a href="infoInformation_add.html?&queryType=pxsx"><i class="fa fa-plus"></i> 增加资讯基本信息</a></li>
    </ul>

    <%-- 查询条件 --%>
    <div class="clearfix">
        <div class="panel panel-info">
            <div class="panel-heading">筛选</div>
            <div class="panel-body">
                <form action="" method="get" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">资讯标题</label>

                        <div class="col-sm-8">
                            <input class="form-control" name="title" id="title" type="text" value="${param.title}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">创建者</label>

                        <div class="col-sm-8">
                            <input class="form-control" name="createUserName" id="createUserName" type="text" value="${param.createUserName}">
                        </div>
                    </div>
             <%--        <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">是否置顶</label>

                        <div class="col-sm-8">
                            <select class="form-control" name="stick" id="stick">
	                            <option value="">所有</option>
	                            <c:forEach var="e" items="${allSticks}">
	                            	<option value="${e.key}" ${param.stick == e.key?'selected':''}>${e.value}</option>
	                            </c:forEach>
							</select>
                        </div>
                    </div> --%>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">上线时间</label>

                        <div class="col-sm-8">
                            <script type="text/javascript">
                                require(["daterangepicker"], function($){
                                    $(function(){
                                        var elm = $("#lineTime");
                                        $(elm).daterangepicker({
                                            startDate: $(elm).prev().prev().val(),
                                            endDate: $(elm).prev().val(),
                                            format: "YYYY-MM-DD",
                                            showDropdowns: true
                                        }, function(start, end){
                                            $(elm).find(".date-title").html(start.toDateStr() + " 至 " + end.toDateStr());
                                            $(elm).prev().prev().val(start.toDateStr());
                                            $(elm).prev().val(end.toDateStr());
                                        });
                                    });
                                });
                            </script>
                            <input name="beginLineTime" type="hidden" value="<fmt:formatDate value="${beginLineTime}" pattern="yyyy-MM-dd" />">
                            <input name="endLineTime" type="hidden" value="<fmt:formatDate value="${endLineTime}" pattern="yyyy-MM-dd" />">
                            <button class="btn btn-default daterange" id="lineTime" type="button" data-original-title="" title=""><span class="date-title"><fmt:formatDate value="${beginLineTime}" pattern="yyyy-MM-dd" /> 至 <fmt:formatDate value="${endLineTime}" pattern="yyyy-MM-dd" /></span> <i class="fa fa-calendar"></i></button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">资讯分类</label>

                        <div class="col-sm-8">
                            <select name="category" id="category" class="form-control">
                                <option value="">所有</option>
                                <c:forEach var="e" items="${allCategorys}">
                                    <c:if test="${e.key>4 }">
                                	      <option value="${e.key}" ${param.category == e.key?'selected':''}>${e.value}</option>
                                	</c:if>	
                                </c:forEach>
                            </select>
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
    	<%--推荐位按钮--%>
    	<a href="javascript:void(0)" class="btn btn-default btn-sm" title="创业宝典主推荐位" id="recommendListValuablePrimary"><i class="fa fa-chevron-up">创业宝典列表主推荐位</i></a>
    	<a href="javascript:void(0)" class="btn btn-default btn-sm" title="创业宝典次推荐位" id="recommendListValuableSecondary"><i class="fa fa-chevron-up">创业宝典列表次推荐位</i></a>
    	<a href="javascript:void(0)" class="btn btn-default btn-sm" title="创业课堂列表推荐位" id="recommendListClass"><i class="fa fa-chevron-up">创业课堂列表推荐位</i></a>
    	<a href="javascript:void(0)" class="btn btn-default btn-sm" title="创业计划书列表推荐位" id="recommendListPlan"><i class="fa fa-chevron-up">创业计划书列表推荐位</i></a>
    	<a href="javascript:void(0)" class="btn btn-default btn-sm" title="首页实训基地推荐位" id="recommendIndexBase"><i class="fa fa-chevron-up">首页实训基地推荐位</i></a>
    	<a href="javascript:void(0)" class="btn btn-default btn-sm" title="实训基地列表推荐位" id="recommendListBase"><i class="fa fa-chevron-up">实训基地列表推荐位</i></a>
        <div class="table-responsive panel-body">
            <table class="table table-hover">
                <thead>
                    <tr>
                    	<th width="30px;"></th>
                        <th>资讯标题</th>
                    <!--     <th>是否置顶</th> -->
                        <th>上线时间</th>
                        <th width="100px;">使用状态</th>
                        <th width="100px;">创建者</th>
                        <th width="100px;">资讯分类</th>
                        <th width="330px;">操作</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td><input type="checkbox" name="blankCheckbox" status="${item.status }" category="${item.category }" id="${item.id }" title="${item.title }"></td>
                        <td title="${item.title}">${item.title}</td>
                  <%--       <td>${item.stick > 0 ? '已置顶' : '--'}</td> --%>
                        <td title="<fmt:formatDate value="${item.lineTime}" pattern="yyyy-MM-dd HH:mm:ss" />"><fmt:formatDate value="${item.lineTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <td>
                        	<c:forEach var="e" items="${allStatuss }">
                        		<c:if test="${item.status == e.key }"><span class="label label-info">${e.value }</span></c:if>
                        	</c:forEach>
                        </td>
                        <td>${item.createUserName}</td>
                        <td>
                        	<c:forEach var="e" items="${allCategorys }">
                        		<c:if test="${item.category == e.key }"><span class="label label-info">${e.value }</span></c:if>	
                        	</c:forEach>
                        </td>
                        <td>
                        	<c:if test="${item.status != 99 }">
                        		<a href="infoInformation_view.html?id=${item.id}&queryType=pxsx" class="btn btn-default btn-sm" title="查看">查看</a>
                            	<a href="infoInformation_update.html?id=${item.id}&queryType=pxsx" class="btn btn-default btn-sm" title="编辑"><i class="fa fa-edit">编辑</i></a>
                            	<%-- <a href="#sendModal" role="button" data-toggle="modal" onclick="getContentValue('${item.introduction}',${item.category })"
										class="btn btn-default btn-sm" title="短信提示" 
                            	>短信提示</a> --%>
                            	<c:choose>
                            		<c:when test="${item.status == 1 }">
                            			<a href="javascript:void(0)" class="btn btn-default btn-sm confirm" title="上线" url="infoInformation_online.html?id=${item.id }&status=3"><i class="fa fa-chevron-up">上线</i></a>
                            		</c:when>
                            		<c:otherwise>
                            			<a href="javascript:void(0)" class="btn btn-default btn-sm confirm" title="下线" url="infoInformation_online.html?id=${item.id }&status=1"><i class="fa fa-chevron-down">下线</i></a>
                            		</c:otherwise>
                            	</c:choose>
                            	<a href="javascript:void(0)" class="btn btn-default btn-sm" title="删除" onclick="javascript:del(${item.id }, 99);"><i class="fa fa-remove">删除</i></a>
                        	</c:if>
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
<jsp:include page="infoInfomation_send_message.jsp"></jsp:include>