<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>  
<%  
response.setStatus(HttpServletResponse.SC_OK);  
Exception ex = (Exception) request.getAttribute("javax.servlet.error.exception");
%>
<html lang="zh-cn" slick-uniqueid="3">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="./web/resource/favicon.png">
    <title>ERROR: 500</title>
    <link href="/resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resource/css/common.css" rel="stylesheet">
    <script src="/resource/js/require.js"></script>
    <script src="/resource/js/app/config.js"></script>
    <script src="/resource/js/lib/jquery-1.11.1.min.js"></script>
</head>
<body>

<div class="container" style="margin-top:70px;">
    <div class="row">
            <div class="jumbotron">
                <h1>服务器出现内部错误</h1>
                <p><%=ex.getMessage()%></p>
                <p><a class="btn btn-primary btn-lg" href="javascript:window.history.go(-1);" role="button">返回</a></p>
            </div>
    </div>
</div>

</body>
</html>        
