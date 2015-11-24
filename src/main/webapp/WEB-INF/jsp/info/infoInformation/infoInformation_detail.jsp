<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/lib/jquery-browser.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/lib/ajaxfileupload.js"></script>
<script>
    require(['validator.bootstrap','summernote','bootstrapQ'], function($){
        $(function(){
        	$("#content").summernote({
        		height: 455,
				onImageUpload: function(files, editor, $editable) {
					sendFile(files[0], editor, $editable);
				}
        	});
        	
        	function sendFile(file, editor, $editable) {
				data = new FormData();
				data.append("file", file);
				$.ajax({
					data : data,
					type : "POST",
					url : 'ajaxUploadInfoImage.html',
					cache : false,
					contentType : false,
					processData : false,
					success : function(result, status) { //服务器成功响应处理函数
						var data = result;
						if (data.success) {
							$('#content').summernote('editor.insertImage', '${headerUrl}'+ data.entity)
						} else {
							bootstrapQ.alert(data.message);
						}
					}
				});
			}
            $("#form1").validate({
            	ignore: "hidden",
                rules: {
                        title : {
                        required: true,
                        maxlength:128
                    },
                        stick : {
                        required: true,
                        digits:true
                    },
                        lineTime : {
                        required: true,
                        dateTime:true
                    },
                        introduction : {
                        required: true,
                        maxlength:1024
                    },
                        contentHidden : {
                        specialInput: true
                    },
                        category : {
                        required: true,
                        digits:true
                    }
                },
                messages: {
                	 title : {
                         required: '请输入资讯标题',
                         maxlength: '输入最大长度不超过{0}'
                     },
                         stick : {
                         required: '请选择是否置顶',
                         digits:'请选择是否置顶'
                     },
                         lineTime : {
                       	 required: '请选择上线时间',
                         dateTime:'请选择上线时间'
                     },
                         introduction : {
                         required: '请输入资讯简介',
                         maxlength: '输入最大长度不超过{0}'
                     },
                         contentHidden : {
                         specialInput: '请输入资讯内容'
                     },
                         category : {
                         required: '请选择资讯分类',
                         digits:'请选择资讯分类'
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
        <c:if test="${queryType=='zczx' }"> 
        <li><a href="infoInformation_list_zczx.html">资讯基本信息列表</a></li>
        <li<c:if test="${empty item}"> class="active"</c:if>><a href="infoInformation_add.html?queryType=zczx"><i class="fa fa-plus"></i> 增加资讯基本信息</a></li>
        </c:if>
         <c:if test="${queryType=='pxsx' }"> 
         <li><a href="infoInformation_list_pxsx.html">资讯基本信息列表</a></li>
        <li<c:if test="${empty item}"> class="active"</c:if>><a href="infoInformation_add.html?queryType=pxsx"><i class="fa fa-plus"></i> 增加资讯基本信息</a></li>
        </c:if>
        <c:if test="${not empty item}"><li class="active"><a href="javascript:"><i class="fa fa-edit"></i> 编辑资讯基本信息</a></li></c:if>
    </ul>


    <%-- 详细 --%>
    <div class="clearfix">
        <form class="form-horizontal form" method="post" id="form1">
            <c:if test="${not empty item}">
            <input type="hidden" name="id" value="${item.id}" />
            </c:if>
            <div class="panel panel-default">
                <div class="panel-heading">
                    	资讯基本信息
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">资讯标题</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="title" name="title" placeholder="请输入资讯标题"  value="${item.title}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">资讯分类</label>
                        <div class="col-sm-9">
                            <!-- <label class="btn btn-default">
                                <input type="radio" name="category" value="1" id="category1"> 创业动态
                            </label>
                            <label class="btn btn-default">
                                <input type="radio" name="category" value="2" id="category2"> 创业典型
                            </label>
                            <label class="btn btn-default">
                                <input type="radio" name="category" value="3" id="category3"> 专题
                            </label>
                            <label class="btn btn-default">
                                <input type="radio" name="category" value="4" id="category4"> 创业宝典
                            </label>
                            <label class="btn btn-default">
                                <input type="radio" name="category" value="5" id="category5"> 创业课堂
                            </label>
                            <label class="btn btn-default">
                                <input type="radio" name="category" value="6" id="category6"> 创业计划书
                            </label> -->
                            <c:forEach items="${allCategorys}" var="e">
                            <c:if test="${queryType=='zczx' }">
                            	
                            	
                            	   <c:if test="${e.key<5 }">
                            	   <label class="btn btn-default">
	                                <input type="radio" name="category" value="${e.key }" id="category${e.key }"> ${e.value }
	                                </label>
	                                </c:if>
	                            </c:if>
	                           <c:if test="${queryType=='pxsx' }">
                            	   <c:if test="${e.key>4 }">
                            	      <label class="btn btn-default">
	                                <input type="radio" name="category" value="${e.key }" id="category${e.key }"> ${e.value }
	                                </label>
	                                </c:if>
	                            </c:if>
	                           
                            </c:forEach>
                            <script type="text/javascript">
                            	var queryType = '${queryType}';
                            	if(queryType == 'zczx'){
                            		document.getElementById("category${empty item ? 1 : item.category}").checked=true;	
                            	}else{
                            		document.getElementById("category${empty item ? 5 : item.category}").checked=true;
                            	}
                            </script>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">是否置顶</label>
                        <div class="col-sm-9">
                        	<!--  
                            <input class="form-control" name="stick" id="stick" type="text" value="${item.stick}">-->
                            <!-- <label class="btn btn-default">
                                <input type="radio" name="stick" value="0" id="stick0"> 不置顶
                            </label>
                            <label class="btn btn-default">
                                <input type="radio" name="stick" value="1" id="stick1"> 置顶
                            </label> -->
                            <c:forEach items="${allSticks }" var="e">
                            	<label class="btn btn-default">
                                	<input type="radio" name="stick" value="${e.key }" id="stick${e.key }"> ${e.value }
                            	</label>		
                            </c:forEach>
                            <script type="text/javascript">
                                document.getElementById("stick${(empty item || item.stick == 0 ) ? 0 : 1}").checked=true;
                            </script>
	
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">上线时间</label>
                        <div class="col-sm-9">
                            <script type="text/javascript">
                                require(["daterangepicker"], function($){
                                    $(function(){
                                        var elm = $("#lineTime");
                                        $(elm).daterangepicker({
                                            format: "YYYY-MM-DD HH:mm:ss",
                                            timePicker:true,
                                            timePickerIncrement:10,
                                            timePicker12Hour : false,
                                            showDropdowns: true,
                                            singleDatePicker:true
                                        });
                                    });
                                });
                            </script>
                            <div class="input-prepend input-group">
                                <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
                                <input class="form-control" type="text" name="lineTime" id="lineTime" readonly="readonly" value="<fmt:formatDate value="${item.lineTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
                            </div>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">资讯简介</label>
                        <div class="col-sm-9">
                        	<!-- 
                            <input type="text" class="form-control" id="introduction" name="introduction" placeholder="请输入资讯简介" value="${item.introduction}"> -->
                            <textarea class="form-control" rows="3" id="introduction" name="introduction" placeholder="请输入资讯简介" style="resize : none">${item.introduction}</textarea>

                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">资讯图片</label>
                        <div class="col-sm-9">
                        	<input type="hidden" id="imgUrl" name="imgUrl" value="${item.imgUrl}">
                        	<img src="${headerUrl}${empty item.imgUrl ? 'cms_no_picture.jpg' : item.imgUrl}" class="img-thumbnail" style="width: 140px; height: 140px;" id="showImgUrl">
                        	<input type="file" name="inputFile1" id="inputFile1" onchange="javascript:uploadImage(this,'showImgUrl', 'imgUrl', 'inputFile1');">
                        	<!-- 
                            <input type="text" class="form-control" id="imgUrl" name="imgUrl" placeholder="请输入咨询图片"  value="${item.imgUrl}"> -->

                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">推荐位图片</label>
                        <div class="col-sm-9">
                        	<input type="hidden" id="recommendationImageUrl1" name="recommendationImageUrl1" value="${item.recommendationImageUrl1}">
                        	<img src="${headerUrl}${empty item.recommendationImageUrl1 ? 'cms_no_picture.jpg' : item.recommendationImageUrl1}" class="img-thumbnail" style="width: 140px; height: 140px;" id="showRecommendationImageUrl1">
                        	<input type="file" name="inputFile2" id="inputFile2" onchange="javascript:uploadImage(this,'showRecommendationImageUrl1', 'recommendationImageUrl1', 'inputFile2');">
                        	<!-- 
                            <input type="text" class="form-control" id="recommendationImageUrl1" name="recommendationImageUrl1" placeholder="请输入推荐位1图片"  value="${item.recommendationImageUrl1}"> -->

                        </div>
                    </div>
                    <%-- <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">推荐位2图片</label>
                        <div class="col-sm-9">
                        	<input type="hidden" id="recommendationImageUrl2" name="recommendationImageUrl2" value="${item.recommendationImageUrl2}">
                        	<img src="${headerUrl}${empty item.recommendationImageUrl2 ? 'cms_no_picture.jpg' : item.recommendationImageUrl2}" class="img-thumbnail" style="width: 140px; height: 140px;" id="showRecommendationImageUrl2">
                        	<input type="file" name="inputFile3" id="inputFile3" onchange="javascript:uploadImage(this,'showRecommendationImageUrl2', 'recommendationImageUrl2', 'inputFile3');">
                        	<!-- 
                            <input type="text" class="form-control" id="recommendationImageUrl2" name="recommendationImageUrl2" placeholder="请输入推荐位2图片"  value="${item.recommendationImageUrl2}"> -->

                        </div>
                    </div> --%>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">资讯内容</label>
                        <div class="col-sm-9">
                            <input type="hidden" name="contentHidden" id="contentHidden">
                        	<textarea rows="5" cols="40" name="content" id="content" class="input-block-level">${item.content}</textarea>
                        	<!-- 
                            <input type="text" class="form-control" id="content" name="content" placeholder="请输入content"  value="${item.content}"> -->

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