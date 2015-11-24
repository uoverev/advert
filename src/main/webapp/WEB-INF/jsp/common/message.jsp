<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<div class="container-fluid">
		<div class="jumbotron clearfix alert alert-${ not empty alertinfo ? alertinfo : 'info' }">
			<div class="row">
				<div class="col-xs-12 col-sm-3 col-lg-2 text-right">
                    <i class="fa fa-5x ${ alertinfo eq 'success' || alertinfo eq 'info' ? 'fa-check-circle' : 'fa-times-circle' }"></i>
				</div>
				<div class="col-xs-12 col-sm-8 col-md-9 col-lg-10">

					<p>${msg}</p>
					<p><a href="javascript:" class="alert-link" onclick="sendHref();">如果你的浏览器没有自动跳转，请点击此链接</a></p>
					<script type="text/javascript">
                        function sendHref(){
                            <c:choose>
                                <c:when test="${empty location}">
                            window.history.go(-1);
                                </c:when>
                                <c:otherwise>
                            location.href = "${location}";
                                </c:otherwise>
                            </c:choose>
                        }
						setTimeout(sendHref, 3000);
					</script>
				</div>
			</div>
		</div>
	</div>