<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>登录系统</title>
<link href="${pageContext.request.contextPath }/resource/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/resource/css/font-awesome.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/resource/css/common.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/resource/js/require.js"></script>
<script src="${pageContext.request.contextPath }/resource/js/app/config.js"></script>
<script src="${pageContext.request.contextPath }/resource/js/lib/jquery-1.11.1.min.js"></script>
<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath }/resource/js/lib/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/resource/js/html5shiv.min.js"></script>
<script src="${pageContext.request.contextPath }/resource/js/respond.min.js"></script>
<![endif]-->
<style type="text/css">
.error {
	color:#a94442
}
.help-block{
	display: block;
    margin-top: 5px;
    margin-bottom: 10px;
}
</style>
<script>

    require(['validator.bootstrap'], function($){
        $(function(){
            $("#fo").validate({
                rules: {
                	username : {
                        required: true
                    },
                    password : {
                        required: true
                    }
                },
                messages: {
                	username : {
                        required: '请输入用户名'
                    },
                    password : {
                        required: '请输入密码'
                    }
                },errorPlacement:function(error,element){
                	error.appendTo($(element).parent().parent());
                }
            });
        });
    });
</script>
</head>
<body>
<div class="container-fluid" style="margin-top:140px;">
    <div class="row">
        <div class="clearfix" style="margin-bottom:5em;">
            <div class="panel panel-default container">
                <div class="panel-body">
                    <c:if test="${not empty loginError}">
                    <div class="alert alert-danger" role="alert"><strong>登陆失败：</strong>${loginError}</div>
                    </c:if>

                    <form id="fo" action="/login.html" method="post" role="form" onsubmit="return formcheck();">
                        <div>
	                        <div class="form-group input-group">
	                            <div class="input-group-addon"><i class="fa fa-user"></i></div>
	                            <input name="username" type="text" class="form-control input-lg" value="<feinno-security2:principal />" placeholder="请输入用户名登录" autocomplete="off" >
	                        </div>
                        </div>
                        <div>
	                        <div class="form-group input-group">
	                            <div class="input-group-addon"><i class="fa fa-unlock-alt"></i></div>
	                            <input name="password" type="password" class="form-control input-lg" placeholder="请输入登录密码" autocomplete="off" >
	                        </div>
                        </div>
                        <div class="form-group">
                            <div class="h3">${loginMessage}</div>
                            <label class="checkbox-inline input-lg">
                                <input id="rememberMe" type="checkbox" value="true" name="rememberMe"> 记住用户名
                            </label>
                            <div class="pull-right">
                                <%-- <a href="" class="btn btn-link btn-lg">注册</a> --%>
                                <input type="submit" name="submit" value="登录" class="btn btn-primary btn-lg">
                                <input name="token" value="7180e133" type="hidden">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<footer class="footer">
    <div class="container text-center">
        <%--<a href="#">关于新媒</a>
        <a href="#">点餐帮助</a>--%>
        <p class="copyright">Powered by Feinno.com</p>
    </div>
</footer>
<script>
$(function(){
	//debugger;
	var strCookie=document.cookie;
	if(strCookie){
		var arrCookie=strCookie.split(";");
		for(var i=0;i<arrCookie.length;i++){
			var arr=arrCookie[i].split("="); 
			if("username"==$.trim(arr[0])){
				if(arr[1]){
					$(':text[name="username"]').val(arr[1]);
					$('#rememberMe').prop("checked", true);
				}
				break; 
			}
		}
	}
});
function formcheck() {
// 	if($('#remember:checked').length == 1) {
// 		if ( typeof(cookie) != "undefined" ) {
// 			cookie.set('remember-username', $(':text[name="username"]').val());
// 		}
// 	} else {
// 		if ( typeof(cookie) != "undefined" ) {
// 			cookie.del('remember-username');
// 		}
// 	}
//debugger;
	if($('#rememberMe').prop("checked")){
		var username = $(':text[name="username"]').val();
		if(username){
		    var exp = new Date();  
		    exp.setTime(exp.getTime() + 60 * 60000 * 24);//过期时间1天   
			document.cookie="username="+username + ";expires=" + exp.toGMTString();
		}
	}else{
		document.cookie="username="+'';
	}
	return true;
}
	require(['jquery', 'util'], function($, az){});
</script>
</body>
</html>