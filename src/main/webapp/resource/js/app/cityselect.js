define(['jquery'],function($){
	var init = function(){
		var prov = $('.prov');
		var city = $('.city');
		var dist = $('.dist');
		
		prov.bind(('change'),function(){
            var val = $(this).children('option:selected').val();
			cityStart(val);
		});
		
		city.bind('change',function(){
            var val = $(this).children('option:selected').val();
			distStart(val);
		});
		
		function cityStart(pcode){
            if(pcode!=undefined && pcode.length==0){
                city.empty();
                city.html('<option value="">全部</option>')
                return ;
            }
			$.ajax({
				type : "GET",
				url : "nextct.html",
				dataType : "json", 
				data : {
					_pcode :pcode
				},
				error : function(request) {
					alert("Connection error");
				},
				success : function(data) {
					if(data.code == 0)
					{
						var html = '<option value="">全部</option>';
						for(var i=0,obj=data.obj;i<obj.length;i++){
							html+='<option value='+obj[i].code+'>'+obj[i].name+'</option>'
						}
                        city.html(html);
                        var val = $(city).children('option:selected').val();
                        distStart(val);
					}
				}
			});
		}
		
		function distStart(pcode){
			if(pcode!=undefined && pcode.length==0){
                dist.empty();
                dist.html('<option value="">全部</option>')
                return ;
            }
			$.ajax({
				type : "GET",
				url : "nextct.html",
				dataType : "json", 
				data : {
					_pcode :pcode
				},
				error : function(request) {
					alert("Connection error");
				},
				success : function(data) {
					if(data.code == 0)
					{
						var html = '<option value="">全部</option>';
						for(var i=0,obj=data.obj;i<obj.length;i++){
							html+='<option value='+obj[i].code+'>'+obj[i].name+'</option>'
						}
                        dist.html(html);
					}
				}
			});
		}
	};
	
	return {
		init : init
	};
});