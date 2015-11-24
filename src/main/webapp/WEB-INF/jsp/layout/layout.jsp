<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="./web/resource/favicon.png">
    <title><tiles:getAsString name="title"/></title>
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

</head>
<body>
<tiles:insertAttribute name="header" />

<div class="container-fluid" style="margin-top:70px;">
    <div class="row">
        <tiles:insertAttribute name="menu" />
        <tiles:insertAttribute name="body" />
    </div>
</div>
<tiles:insertAttribute name="footer" />

</body>
</html>