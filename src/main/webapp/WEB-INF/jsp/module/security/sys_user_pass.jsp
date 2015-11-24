<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <li><a href="sys_user_list.html">系统用户列表</a></li>
        <feinno-security2:hasPermission name="/module/security/sys_user_add.html">
        <li><a href="sys_user_add.html"><i class="fa fa-plus"></i> 增加系统用户</a></li>
        </feinno-security2:hasPermission>
        <li class="active"><a href="javascript:"> 初始化用户登录密码</a></li>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal" role="form" method="post" id="form1">
            <div class="panel panel-default">
                <div class="panel-heading">
                    初始化用户登录密码
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">登录用户名</label>
                        <div class="col-sm-9"> <span class="help-block">${item.username}</span></div>
                        <input type="hidden" name="id" value="${item.id}">
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">输入新登录密码</label>
                        <div class="col-sm-9">
                           <input type="password" name="newpassword" id="newpassword" value="" class="form-control" autocomplete="off" >
                           <span class="help-block">指定帐户的登录密码。</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">再次输入新登录密码</label>
                        <div class="col-sm-9">
                           <input type="password" name="newpassword2" value="" class="form-control" autocomplete="off" >
                           <span class="help-block">再次输入指定帐户的登录密码。</span>
                        </div>
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label"></label>
                        <div class="col-sm-9">
                            <input name="submit" type="submit" value="保存" class="btn btn-primary col-lg-1" />
                        </div>
                    </div>

                </div>
            </div>
        </form>
    </div>
</div>
<script>
require(['util', 'validator.bootstrap'], function(util, $){
	$(function(){
		$('#form1').validate({
			onfocusout:false,
			onkeyup:false,
			onclick:false,
			focusInvalid:false,
			rules: {
				newpassword: {
					required: true,
					maxlength: 30
				},
				newpassword2: {
					required: true,
					equalTo: '#newpassword'
				}
			},
			messages: {
				newpassword: {
					required: ' ',
					maxlength: '最多只能输入{0}个字符'
				},
				newpassword2: {
					required: ' ',
					equalTo: '两次输入的密码必须要相同哟！'
				}
			}
		});
	});
});

</script>