qiao.on('.confirm', 'click', function(){
	var url = $(this).attr("url");
	var title = $(this).attr("title");
	bootstrapQ.confirm('是否' + title + "?",function(){
		window.location.href=url;
	});
});


qiao.on('.confirmAudit', 'click', function(){
	var url = $(this).attr("url");
	var title = $(this).attr("title");
	bootstrapQ.confirm("是否确认审核通过?",function(){
		window.location.href=url;
	});
});

function alertWindow (msg){
	bootstrapQ.alert(msg);
}
