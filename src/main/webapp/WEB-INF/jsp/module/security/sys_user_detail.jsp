<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script>

    require(['validator.bootstrap'], function($){
        $(function(){
            $("#form1").validate({
                rules: {
                        username : {
                        required: true
                    },
					<c:if test="${empty item}">
                        password : {
                        required: true
                    },
                        password2 : {
                        required: true,
						equalTo: '#password'
                    },
					</c:if>
                        email : {
                        required: true
                    },
                        status : {
                        required: true,
                        number:true
                    }
                },
                messages: {
                    username : {
                        required: ' '
                    },
					<c:if test="${empty item}">
                        password : {
                        required: ' '
                    },
                        password2 : {
                        required: '请重新输入新密码！',
						equalTo: '两次输入的密码必须要相同哟！'
                    },
					</c:if>
                    email : {
                        required: ' '
                    },
                    status : {
                        required: ' ',
                        number:' '
                    }
                }
            });
        });
    });
</script>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <%-- 导航 --%>
        <li><a href="sys_user_list.html">系统用户列表</a></li>
        <feinno-security2:hasPermission name="/module/security/sys_user_add.html">
        <li<c:if test="${empty item}"> class="active"</c:if>><a href="sys_user_add.html"><i class="fa fa-plus"></i> 增加系统用户</a></li>
        </feinno-security2:hasPermission>
        <c:if test="${not empty item}"><li class="active"><a href="javascript:"><i class="fa fa-edit"></i> 编辑系统用户</a></li></c:if>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal form" method="post" id="form1">
            <c:if test="${not empty item}">
            <input type="hidden" name="id" value="${item.id}" />
            </c:if>
            <div class="panel panel-default">
                <div class="panel-heading">
                    系统用户
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">登录用户名</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="username" name="username" placeholder="请输入登录用户名"  value="${item.username}" onblur="javascript:checkDup();" ${empty item ? ' ' : 'readonly disabled'}>
                            <span class="help-block" >
                                <strong id="nameSpan">请指定登录的用户名，注意名称不可重复</strong>
                            </span>
                        </div>
                    </div>
                    <c:if test="${empty item}">
                        <div class="form-group">
                            <label class="col-xs-12 col-sm-3 col-md-2 control-label">登录密码</label>
                            <div class="col-sm-9">
                                <input name="password" type="password" class="form-control" id="password" placeholder="请输入登录用户密码" maxlength="30">
    
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-12 col-sm-3 col-md-2 control-label">再次输入登录密码</label>
                            <div class="col-sm-9">
                                <input name="password2" type="password" class="form-control" id="password2" placeholder="请输入登录用户密码" maxlength="30">
    
                            </div>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">所属角色</label>
                        <div class="col-sm-9">
                            <select name="roleId" class="form-control" value="${item.role.id}" style="width: 160px">
                                    <c:forEach items="${roles }" var="role">
                                    <option value="${role.id }" ${role.id eq item.role.id ? 'selected':''}>${role.roleName }</option>
                                    </c:forEach>
                            </select>
                            <span class="help-block">请选择所属的角色</span>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">电子邮件</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="email" name="email" placeholder="请输入电子邮件"  value="${item.email}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">过期时间</label>
                        <div class="col-sm-9">
                            <script type="text/javascript">
                                require(["daterangepicker"], function($){
                                    $(function(){
                                        var elm = $("#expiredDate");
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
                                <input class="form-control" type="text" name="expiredDate" id="expiredDate" readonly="readonly" value="<fmt:formatDate value="${item.expiredDate}" pattern="yyyy-MM-dd" />">
                            </div>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">解锁时间</label>
                        <div class="col-sm-9">
                            <script type="text/javascript">
                                require(["daterangepicker"], function($){
                                    $(function(){
                                        var elm = $("#unlockDate");
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
                                <input class="form-control" type="text" name="unlockDate" id="unlockDate" readonly="readonly" value="<fmt:formatDate value="${item.unlockDate}" pattern="yyyy-MM-dd" />">
                            </div>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">描述</label>
                        <div class="col-sm-9">
                          <textarea name="descn" rows="5" class="form-control" id="descn" placeholder="请输入描述信息">${item.descn}</textarea>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">状态</label>
                        <div class="col-sm-9">
                            <label class="btn btn-default">
                                <input type="radio" name="status" value="0" id="status0"> 禁用
                            </label>
                            <label class="btn btn-default">
                                <input type="radio" name="status" value="1" id="status1"> 启用
                            </label>
                            <label class="btn btn-default">
                                <input type="radio" name="status" value="2" id="status0"> 锁定
                            </label>
                            <script type="text/javascript">
                                document.getElementById("status${empty item ? 1 : item.status}").checked=true;
                            </script>

                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <button type="submit" id="sub_form1" class="btn btn-primary col-lg-1" name="submit" value="提交">提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script language="javascript">
function checkDup()
{
	$.ajax({
		type : "POST",
		url : "check_name.html",
		data : $('#form1').serialize(),
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			if(data.code == 0)
			{
				$('#nameSpan').css('color','red');
				$('#nameSpan').html(data.message);
				document.getElementById("sub_form1").disabled = true;
				$(this).addClass('red');
			}else{
				$('#nameSpan').css('color','green');
				$('#nameSpan').html(data.message);
				document.getElementById("sub_form1").disabled = false;
			}
		}
	});
}
</script>