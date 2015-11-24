<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script>

    require(['validator.bootstrap'], function($){
        $(function(){
            $("#form1").validate({
                rules: {
                        refName : {
                        required: true,
                        maxlength:512
                    },
                        sortNo : {
                        required: true,
                        digits:true,
                        maxlength:11
                    },
                        status : {
                        required: true,
                        digits:true
                    }
                },
                messages: {
                    refName : {
                        required: '请输入关联数据名称 ',
                        maxlength: '输入最大长度不超过{0}'
                    },
                    sortNo : {
                        required: '请输入排序号 ',
                        digits:'请输入整数',
                        maxlength: '输入最大长度不超过{0}'
                    },
                    status : {
                        required: '请选择发布状态',
                        digits:'请选择发布状态'
                    }
                }
            });
        });
    });
</script>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <%-- 导航 --%>
        <li><a href="placeSys_list.html">推荐位关系数据表列表</a></li>
        <c:if test="${not empty item}"><li class="active"><a href="javascript:"><i class="fa fa-edit"></i> 编辑推荐位关系数据表</a></li></c:if>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal form" method="post" id="form1">
            <c:if test="${not empty item}">
            <input type="hidden" name="id" value="${item.id}" />
            </c:if>
            <div class="panel panel-default">
                <div class="panel-heading">
                    推荐位关系数据表
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">关联数据名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="refName" name="refName" placeholder="请输入关联数据名称"  value="${item.refName}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">排序号</label>
                        <div class="col-sm-9">
                            <input class="form-control" name="sortNo" id="sortNo" type="text" value="${item.sortNo}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">发布状态</label>
                        <div class="col-sm-9">
                            <label class="btn btn-default">
                                <input type="radio" name="status" value="0" id="status0"> 未发布
                            </label>
                            <label class="btn btn-default">
                                <input type="radio" name="status" value="1" id="status1"> 已发布
                            </label>
                            <script type="text/javascript">
                                document.getElementById("status${empty item ? 0 : item.status}").checked=true;
                            </script>

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