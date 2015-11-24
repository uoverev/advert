<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script>

    require(['validator.bootstrap'], function($){
        $(function(){
            $("#form1").validate({
                rules: {
                      title : {
                        required: true,
                        maxlength: 80
                    },
                      countNum : {
                        required: true,
                        digits:true,
                        maxlength: 11
                    }
                },
                messages: {
                    title : {
                        required: '请输入模板标题',
                        maxlength: '输入最大长度不超过{0}'
                    },
                    countNum : {
                        required: '请输入展示数据数量 ',
                        digits : '请输入整数 ',
						maxlength: '输入最大长度不超过{0}'
                    }
                }
            });
        });
    });
</script>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <%-- 导航 --%>
        <li><a href="template_list.html">模版列表</a></li>
        <c:if test="${not empty item}"><li class="active"><a href="javascript:"><i class="fa fa-edit"></i> 编辑模版</a></li></c:if>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal form" method="post" id="form1">
            <c:if test="${not empty item}">
            <input type="hidden" name="id" value="${item.id}" />
            </c:if>
            <div class="panel panel-default">
                <div class="panel-heading">
                    模版
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">模板标题</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="title" name="title" placeholder="请输入模板标题"  value="${item.title}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">展示数据数量</label>
                        <div class="col-sm-9">
                            <input class="form-control" name="countNum" id="countNum" type="text" value="${item.countNum}">

                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <button type="submit" class="btn btn-primary col-lg-1" name="submit" value="提交">提交</button>
                </div>
            </div>
        </form>
    </div>
</div>