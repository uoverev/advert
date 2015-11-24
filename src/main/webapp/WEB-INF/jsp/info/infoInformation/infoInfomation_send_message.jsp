<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<style>
 .modal-open {
	overflow-y: hidden !important;
} 
</style>
<script>
	require([ 'validator.bootstrap' ], function($) {
		$(function() {
			$("#sendMessageForm").validate({
				rules : {
					sendTime : {
						 required: true,
						 dateTime:true
					},
					content : {
						required : true
					}
				},
				messages : {
					sendTime : {
						 required: ' ',
						 dateTime:' '
					},
					content:{
						 required: ' '
					}
				}
			});
		});
	});

	function getContentValue(message,category) {
		$("#categoryForm").val(category);
		var content = "【创业网】";
		content+=message;
		content+="\r更多创业新资讯，请登录平台查询。";
		$("#contentForm").val(content);
	}

	
</script>


<div class="modal fade" id="sendModal"  role="dialog"  aria-labelledby="sendModalLabel" aria-hidden="true">
	<form action="sendMessage.html" method="post" id="sendMessageForm">
	    <input type="hidden" name="category" id="categoryForm">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="panel-body">
	                        <label class="col-xs-12 col-sm-12  control-label" style="color: blue;">（提示：短信内容为资讯简介内容）</label>
	                        <hr size="1" color="#999999">
	                         <label class="col-xs-12 col-sm-2  control-label">发送时间</label>
	                         <div class="form-group">
	                        <div class="col-sm-10" style="padding-bottom: 20px">
	                             <script type="text/javascript">
	                                require(["daterangepicker"], function($){
	                                    $(function(){
	                                        var elm = $("#sendTime");
	                                        $(elm).daterangepicker({
	                                            format: "YYYY-MM-DD HH:mm:ss",
	                                            timePicker:true,
	                                            timePickerIncrement:10,
	                                            timePicker12Hour : false,
	                                            minDate:new Date(),
	                                            showDropdowns: true,
	                                            singleDatePicker:true
	                                        });
	                                    });
	                                });
	                              </script>
		                          <div class="input-prepend input-group">
		                              <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
		                              <input class="form-control" type="text" name="sendTime" id="sendTime" readonly="readonly" value="">
		                          </div>
	                        </div>
	                        </div>
	                        <label class="col-xs-12 col-sm-2 control-label">短信内容</label>
	                        <div class="col-sm-10" >
			                    <textarea rows="10" cols="80" class="form-control" id="contentForm" name="content" placeholder="请输入短信内容"></textarea>
	                        </div>
	                       
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="submit" class="btn btn-primary">确定</button>
				</div>
				<!-- /.modal-content -->
			</div>
		</div>
	</form>
</div>