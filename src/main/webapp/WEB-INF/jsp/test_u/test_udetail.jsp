<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script>

    require(['validator.bootstrap'], function($){
        $(function(){
            $("#form1").validate({
                rules: {
                        name : {
                        required: true
                    },
                        province : {
                        required: true
                    },
                        city : {
                        required: true
                    },
                        distinct : {
                        required: true
                    },
                        detail : {
                        required: true
                    },
                        identity : {
                        required: true
                    },
                        sex : {
                        required: true
                    },
                        carB : {
                        required: true
                    },
                        buyT : {
                        required: true,
                        date:true
                    }
                },
                messages: {
                    name : {
                        required: ' '
                    },
                    province : {
                        required: ' '
                    },
                    city : {
                        required: ' '
                    },
                    distinct : {
                        required: ' '
                    },
                    detail : {
                        required: ' '
                    },
                    identity : {
                        required: ' '
                    },
                    sex : {
                        required: ' '
                    },
                    carB : {
                        required: ' '
                    },
                    buyT : {
                        required: ' ',
                        date:' '
                    }
                }
            });
        });
    });
</script>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <%-- 导航 --%>
        <li><a href="test_ulist.html">test_user_info列表</a></li>
        <li<c:if test="${empty item}"> class="active"</c:if>><a href="test_uadd.html"><i class="fa fa-plus"></i> 增加test_user_info</a></li>
        <c:if test="${not empty item}"><li class="active"><a href="javascript:"><i class="fa fa-edit"></i> 编辑test_user_info</a></li></c:if>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal form" method="post" id="form1">
            <c:if test="${not empty item}">
            <input type="hidden" name="id" value="${item.id}" />
            </c:if>
            <div class="panel panel-default">
                <div class="panel-heading">
                    test_user_info
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">姓名</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名"  value="${item.name}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">所在省</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="province" name="province" placeholder="请输入所在省"  value="${item.province}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">所在市</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="city" name="city" placeholder="请输入所在市"  value="${item.city}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">所在区</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="distinct" name="distinct" placeholder="请输入所在区"  value="${item.distinct}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">详细地址</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="detail" name="detail" placeholder="请输入详细地址"  value="${item.detail}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">身份证号</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="identity" name="identity" placeholder="请输入身份证号"  value="${item.identity}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">性别</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="sex" name="sex" placeholder="请输入性别"  value="${item.sex}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">车辆品牌</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="carB" name="carB" placeholder="请输入车辆品牌"  value="${item.carB}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">购车时间</label>
                        <div class="col-sm-9">
                            <script type="text/javascript">
                                require(["daterangepicker"], function($){
                                    $(function(){
                                        var elm = $("#buyT");
                                        $(elm).daterangepicker({
                                            format: "YYYY-MM-DD",
                                            showDropdowns: true,
                                            singleDatePicker:true
                                        });
                                    });
                                });
                            </script>
                            <div class="input-prepend input-group">
                                <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
                                <input class="form-control" type="text" name="buyT" id="buyT" readonly="readonly" value="<fmt:formatDate value="${item.buyT}" pattern="yyyy-MM-dd" />">
                            </div>

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