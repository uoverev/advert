<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:forEach items="${list}" var="item">
    <div class="thumbnail${item.attachUrl eq url ? ' active' : ''}">
        <em><img src="${item.attachUrl}" onclick="${callback}.select({filename: '${item.attachUrl}', url:'${item.attachUrl}'});" class="img-responsive"></em>
        <span class="text-center"><%--${item.attachUrl}--%></span>
        <em class="close" title="删除这张图片" onclick="${callback}.delete(this, '${item.attachUrl}');return false;">×</em>
    </div>
</c:forEach>
