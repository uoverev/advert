<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/lib/ajaxfileupload.js"></script>
<script>

    require(['validator.bootstrap'], function($){
        $(function(){
            $("#form1").validate({
                rules: {
                        content : {
                        required: false
                    },
                        alt : {
                        required: false
                    },
                        jumpUrl : {
                        required: false
                    },
                        imgUrl : {
                        required: true
                    }
                },
                messages: {
                    content : {
                        required: ' '
                    },
                    alt : {
                        required: ' '
                    },
                    jumpUrl : {
                        required: ' '
                    },
                    imgUrl : {
                        required: ' '
                    }
                }
            });
        });
    });
    
 	// 文件大小验证
    function checkFileSize(maxsize,fileElementId){
		maxsize = maxsize*1024*1024;
		var  browserCfg = {};
		var ua = window.navigator.userAgent;
		if (/msie/i.test(navigator.userAgent) && !window.opera){
			browserCfg.ie = true;
		}else if(ua.indexOf("Firefox")>=1){
			browserCfg.firefox = true;
		}else if(ua.indexOf("Chrome")>=1){
			browserCfg.chrome = true;
		}else if(ua.indexOf("Safari")>=1){
			browserCfg.safari = true;
		}else if(ua.indexOf("Opera")>=1){
			browserCfg.opera = true;
		}
		try{
		 	var obj_file = document.getElementById(fileElementId);
		 	if(obj_file.value==""){
		 		return false;
		 	}
		 	var filesize = 0;
		 	if(browserCfg.firefox || browserCfg.chrome || browserCfg.safari || browserCfg.opera){
		 		filesize = obj_file.files[0].size;
		 	}else if(browserCfg.ie){
		 		var tempimg = document.getElementById('tempimg');
		 		if(!tempimg){
		 			$('body').append('<img id="tempimg"/>');
		 			tempimg = document.getElementById('tempimg');
		 		}
		 		tempimg.dynsrc=obj_file.value;
		 		filesize = tempimg.fileSize;
		 	}else{
		 		return true;
		 	}
		 	if(filesize==-1){
		 		return true;
		 	}else if(filesize>maxsize){
		 		return false;
			}else{
		 		return true;
			}
		}catch(e){
			return true;
		}
	};
    
    // ajax文件上传
    function uploadImage(obj, showPath, path, fileId){
    	// 判断当前传入的图片是否为图片格式
    	var fileName = obj.value;
    	var extStart = fileName.lastIndexOf(".");
    	var ext = fileName.substring(extStart,fileName.length).toUpperCase();
    	if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"){
    		bootstrapQ.alert("文件上传格式不正确：图片限于bmp,png,gif,jpg格式");
    		// 将input file设置为空
    		clear(fileId);
    		return false;
    	}
    	if(checkFileSize(2, fileId)){
    		// 将图片上传到头像临时目录，并展示到页面显示
        	$.ajaxFileUpload({
                url:'ajaxUploadInfoImage.html',
                secureuri : false,					//一般设置为false
                fileElementId : fileId,				//file控件的id
                dataType : 'text',
                success: function (result, status){	//服务器成功响应处理函数
                	var data = JSON.parse(result);
                	if(data.success) {
            			$("#" + path).val(data.entity);
            			$("#" + showPath).attr("src" , ('${headerUrl}' + data.entity));
              		} else {
              			bootstrapQ.alert(data.message);
              		}
                },
                error: function (result, status, e){		//服务器响应失败处理函数
                	bootstrapQ.alert("图片上传失败");
                }
            });	
    	}else{
    		bootstrapQ.alert("上传图片大小不能超过2M");
    		clear(fileId);
    	}
    }
    
    // 清空input file
    function clear(inputId){
    	var file = document.getElementById(inputId);
    	// for IE, Opera, Safari, Chrome
        if (file.outerHTML) {
            file.outerHTML = file.outerHTML;
        } else { // FF(包括3.5)
            file.value = "";
        }
    }
</script>
<div class="col-xs-12 col-sm-9 col-lg-10">
    <ul class="nav nav-tabs">
        <%-- 导航 --%>
        <li><a href="placeData_list.html">广告位列表</a></li>
        <li<c:if test="${empty item}"> class="active"</c:if>><a href="placeData_add.html"><i class="fa fa-plus"></i> 增加广告位</a></li>
        <c:if test="${not empty item}"><li class="active"><a href="javascript:"><i class="fa fa-edit"></i> 编辑广告位</a></li></c:if>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal form" method="post" id="form1">
            <c:if test="${not empty item}">
            <input type="hidden" name="id" value="${item.id}" />
            </c:if>
            <div class="panel panel-default">
                <div class="panel-heading">
                    广告位
                </div>
                <div class="panel-body">
                	<div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">位置名称</label>
                        <div class="col-sm-9">
                            <c:forEach items="${allCodes }" var="e">
                            	<label class="btn btn-default">
                                	<input type="radio" name="code" value="${e.key }" id="code${e.key }"> ${e.value }
                            	</label>		
                            </c:forEach>
                            <script type="text/javascript">
                                document.getElementById("code${empty item ? 'index_place_data' : item.code }").checked=true;
                            </script>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">显示内容</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="content" name="content" placeholder="请输入显示内容"  value="${item.content}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">提示文字</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="alt" name="alt" placeholder="请输入用于图片不能显示提示文字"  value="${item.alt}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">跳转地址</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="jumpUrl" name="jumpUrl" placeholder="请输入跳转地址"  value="${item.jumpUrl}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">显示图片</label>
                        <div class="col-sm-9">
                        	<input type="hidden" id="imgUrl" name="imgUrl" value="${item.imgUrl}">
                        	<img src="${headerUrl}${empty item.imgUrl ? 'cms_no_picture.jpg' : item.imgUrl}" class="img-thumbnail" style="width: 140px; height: 140px;" id="showImgUrl">
                        	<input type="file" name="inputFile" id="inputFile" onchange="javascript:uploadImage(this,'showImgUrl', 'imgUrl', 'inputFile');">
                        	
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <button type="submit" class="btn btn-primary col-lg-1" name="submit" value="提交">提交</button>
                </div>
            </div>
        </form>
    </div>
</div>