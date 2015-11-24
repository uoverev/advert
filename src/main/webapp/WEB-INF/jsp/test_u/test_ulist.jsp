<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12 col-sm-9 col-lg-10">
    <%-- 导航 --%>
    <ul class="nav nav-tabs">
        <li class="active"><a href="test_ulist.html">test_user_info列表</a></li>
        <li><a href="test_uadd.html"><i class="fa fa-plus"></i> 增加test_user_info</a></li>
    </ul>

    <%-- 查询条件 --%>
    <div class="clearfix">
        <div class="panel panel-info">
            <div class="panel-heading">筛选</div>
            <div class="panel-body">
                <form action="" method="get" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">姓名</label>

                        <div class="col-sm-8">
                            <input class="form-control" name="name" id="name" type="text" value="${param.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">所在省</label>

                        <div class="col-sm-8">
                        	<select class="form-control prov">
                        		<option value="">全部</option>
                        		<c:forEach items="${_regions }" var="item">
                        			<option value="${item.code }">${item.name }</option>
                        		</c:forEach>
                        	</select>
                        	<%--
                            <input class="form-control" name="province" id="province" type="text" value="${param.province}">
                             --%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">所在市</label>

                        <div class="col-sm-8">
                        	<select class="form-control city">
                        		<option value="">全部</option>
                        	</select>
                        	<%--
                            <input class="form-control" name="city" id="city" type="text" value="${param.city}">
                             --%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">所在区</label>

                        <div class="col-sm-8">
                        	<select class="form-control dist">
                        		<option value="">全部</option>
                        	</select>
                            <%--<input class="form-control" name="distinct" id="distinct" type="text" value="${param.distinct}">
                             --%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">性别</label>

                        <div class="col-sm-8">
                            <input class="form-control" name="sex" id="sex" type="text" value="${param.sex}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-2 col-md-2 col-lg-1 control-label">车辆品牌</label>

                        <div class="col-sm-8">
                            <input class="form-control" name="carB" id="carB" type="text" value="${param.carB}">
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
                        <th>姓名</th>
                        <th>所在省</th>
                        <th>所在市</th>
                        <th>所在区</th>
                        <th>性别</th>
                        <th>车辆品牌</th>
                        <th>购车时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.province}</td>
                        <td>${item.city}</td>
                        <td>${item.distinct}</td>
                        <td>${item.sex}</td>
                        <td>${item.carB}</td>
                        <td><fmt:formatDate value="${item.buyT}" pattern="yyyy-MM-dd" /></td>
                        <td>
                            <a href="test_uview.html?id=${item.id}"class="btn btn-default btn-sm" title="查看">查看</a>
                            <a href="test_uupdate.html?id=${item.id}"class="btn btn-default btn-sm" title="编辑"><i class="fa fa-edit"> 编辑</i></a>
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
<script>
	require(['cityselect'],function(cityselect){
		cityselect.init();
	})
</script>