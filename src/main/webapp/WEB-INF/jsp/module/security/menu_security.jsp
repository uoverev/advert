<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12 col-sm-3 col-lg-2">
    <c:forEach items="${authorisedMenus}" var="menu">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title"><a data-toggle="collapse" href="#frame-${menu.id}">${menu.text}</a>
            </h4>
        </div>

        <ul class="list-group${menu.attributes.showMode eq '1' ? ' in' : ' collapse'}" id="frame-${menu.id}">
            <c:forEach items="${menu.children}" var="resc">
            <a class="list-group-item${lm==resc.attributes.firstSpeel ? ' active' : ''}" href="${pageContext.request.contextPath }${resc.attributes.url}?lm=${resc.attributes.firstSpeel}"><i class="${resc.iconCls}"></i>  ${resc.text}</a>
            </c:forEach>
        </ul>

    </div>
    </c:forEach>

</div>