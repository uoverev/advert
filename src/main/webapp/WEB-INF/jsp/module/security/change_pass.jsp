<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal" role="form" method="post" action="change_pass.html" id="form1">
            <div class="panel panel-default">
                <div class="panel-heading">
                    帐户登录密码修改
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">登录用户名</label>
                        <div class="col-sm-9"> <span class="help-block"><feinno-security2:principal /></span></div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">输入用户当前登录密码</label>
                        <div class="col-sm-9">
                           <input type="password" id="oldpassword" name="oldpassword" value="" class="form-control" autocomplete="off" >
                           <span class="help-block">指定用户的登录密码。</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">输入用户新登录密码</label>
                        <div class="col-sm-9">
                           <input type="password" name="newpassword" id="newpassword" value="" class="form-control" autocomplete="off" >
                           <span class="help-block">指定用户的登录密码。</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">再次输入用户新登录密码</label>
                        <div class="col-sm-9">
                           <input type="password" name="newpassword2" value="" class="form-control" autocomplete="off" >
                           <span class="help-block">再次输入指定用户的登录密码。</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label"></label>
                        <div class="col-sm-9">
                            <input name="submit" type="submit" value="保存密码" class="btn btn-primary" />
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
			rules: {
				oldpassword: {
					required: true,
					maxlength: 30
				},
				newpassword: {
					required: true,
					maxlength: 30,
					notEqualTo: '#oldpassword'
				},
				newpassword2: {
					required: true,
					maxlength: 30,
					equalTo: '#newpassword'
				}
			},
			messages: {
				oldpassword: {
					required: ' ',
					maxlength: '最多只能输入{0}个字符'
				},
				newpassword: {
					required: ' ',
					maxlength: '最多只能输入{0}个字符',
					notEqualTo: '新密码和旧密码不能相同！'
				},
				newpassword2: {
					required: ' ',
					maxlength: '最多只能输入{0}个字符',
					equalTo: '两次输入的密码必须要相同哟！'
				}
			}
		});
	});
});
</script>