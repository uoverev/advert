<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script src="${pageContext.request.contextPath }/resource/js/require.js"></script>
<script src="${pageContext.request.contextPath }/resource/js/app/config.js"></script>
<script src="${pageContext.request.contextPath }/resource/js/lib/jquery-1.11.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            var page=1;
            var nScrollHight = 0; //滚动距离总长(注意不是滚动条的长度)
            var nScrollTop = 0;   //滚动到的当前位置
            var nDivHight = $("#image-container").height();
            var bindScroll=true;
            $("#image-container").scroll(function(){
                if(bindScroll==false){
                    return ;
                }
                nScrollHight = $(this)[0].scrollHeight;
                nScrollTop = $(this)[0].scrollTop;
                //console.log("topDivHi"+(nScrollTop + nDivHight)+", scrollHi:"+nScrollHight);
                if(nScrollTop + nDivHight >= nScrollHight-230) {
                    page++;
                    $.ajax({
                        url:'/global/upload/ajax/list.html',
                        type:'POST',
                        data:{callback:'${callback}',options:'${url}', pageNo:page},
                        dataType:'html',
                        sync:false,
                        success:function(data){
                            if(data==null || $.trim(data)==""){
                                bindScroll=false;
                            }
                            $("#waterfall").append(data);
                        }
                    });
                }
            });
        });
    </script>

<style type="text/css">
    .modal-dialog .file-browser .breadcrumb i{ font-size:1em;}
</style>

<div id="waterfall" class="clearfix file-browser">
    <c:forEach items="${list}" var="item">
        <div class="thumbnail${item.attachUrl eq url ? ' active' : ''}">
                <em><img src="${item.attachUrl}" height="100" onclick="${callback}.select({filename: '${item.attachUrl}', url:'${item.attachUrl}'});" class="img-responsive" /></em>
                <span class="text-center"></span>
                <em class="close" title="删除这张图片" onclick="${callback}.delete(this, '${item.attachUrl}');return false;">×</em>
        </div>
    </c:forEach>
</div>

