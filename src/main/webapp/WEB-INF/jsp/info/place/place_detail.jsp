<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script>

    require(['validator.bootstrap'], function($){
        $(function(){
            $("#form1").validate({
                rules: {
                        name : {
                        required: true,
                        maxlength: 50
                    },
                        status : {
                        required: true,
                        digits:true
                    }
                },
                messages: {
                    name : {
                        required: '请输入位置名',
                        maxlength: '输入最大长度不超过{0}'
                    },
                    status : {
                        required: '请选择状态 ',
                        digits:' '
                    }
                }
            });
        });
    });
</script>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <%-- 导航 --%>
        <li><a href="place_list.html">前台位置数据表列表</a></li>
        <c:if test="${not empty item}"><li class="active"><a href="javascript:"><i class="fa fa-edit"></i> 编辑前台位置数据表</a></li></c:if>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal form" method="post" id="form1">
            <c:if test="${not empty item}">
            <input type="hidden" name="id" value="${item.id}" />
            </c:if>
            <div class="panel panel-default">
                <div class="panel-heading">
                    前台位置数据表
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">位置名</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="请输入位置名"  value="${item.name}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">状态</label>
                        <div class="col-sm-9">
                            <label class="btn btn-default">
                                <input type="radio" name="status" value="0" id="status0"> 失效
                            </label>
                            <label class="btn btn-default">
                                <input type="radio" name="status" value="1" id="status1"> 生效
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