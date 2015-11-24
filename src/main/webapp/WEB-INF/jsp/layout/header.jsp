<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
    	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
          <div class="container-fluid">
            <div class="navbar-header">
				<a class="navbar-brand" href="#">大学生创业网CMS</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i><feinno-security2:principal /> <b class="caret"></b></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/module/security/change_pass.html"><i class="fa fa-cloud-download fa-fw"></i> 修改密码</a></li>
                            <li class="divider"></li>
                            <li><a href="/logout.html"><i class="fa fa-sign-out fa-fw"></i> 退出系统</a></li>
                        </ul>
                    </li>
				</ul>
            </div>
          </div>
        </nav>
