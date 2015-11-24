$.validator.setDefaults({
    //errorClass:"help-block",
    onfocusout:false,
    onkeyup:false,
    onclick:false,
    errorElement:"small",
    error : function(){alert("error");},
    errorPlacement : function(error, element) {
        element.removeClass("help-block");
        /*<i class="form-control-feedback glyphicon glyphicon-remove"></i>*/
        var tip = element.next();
        if(tip!=null && tip.prop("tagName") == "I"){
            tip.removeClass("glyphicon-ok");
            tip.addClass("glyphicon-remove");
        }else{
            element.after("<i class=\"form-control-feedback glyphicon glyphicon-remove\"></i>");
        }

        element.parent().parent("div").removeClass("has-success");
        element.parent().parent("div").addClass("has-feedback has-error");
        error.addClass("help-block");
        error.appendTo( element.parent());
    },
    success : function(error){
        /*<i class="form-control-feedback glyphicon glyphicon-ok"></i>*/
        var tip = error.parent().find("I");
        if(tip!=null && tip.prop("tagName") == "I"){
            tip.removeClass("glyphicon-remove");
            tip.addClass("glyphicon-ok");
        }
        error.parent().parent("div").removeClass("has-error");
        error.parent().parent("div").addClass("has-feedback has-success");
        error.remove();
    }
});

$.extend($.validator.messages, {
    required: "必选字段",
    remote: "请修正该字段",
    email: "请输入正确格式的电子邮件",
    url: "请输入合法的网址",
    date: "请输入合法的日期",
    dateISO: "请输入合法的日期 (ISO).",
    number: "请输入合法的数字",
    digits: "只能输入整数",
    creditcard: "请输入合法的信用卡号",
    equalTo: "请再次输入相同的值",
    accept: "请输入拥有合法后缀名的字符串",
    maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
    minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
    rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
    range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
    max: jQuery.validator.format("请输入一个最大为{0} 的值"),
    min: jQuery.validator.format("请输入一个最小为{0} 的值")
});

$.validator.addMethod("notEqualTo", function(value, element, param) {
  return this.optional(element) || value != $(param).val();
}, "Please specify a different (non-default) value");

//手机号码
$.validator.addMethod("phone", function(value, element, param) {
    var isValid = (/^((00|\+)?(86(?:-| )))?((\d{11})|(\d{3}[- ]{1}\d{4}[- ]{1}\d{4})|((\d{2,4}[- ]){1}(\d{7,8}|(\d{3,4}[- ]{1}\d{4}))([- ]{1}\d{1,4})?))$/).test(value);
    return this.optional(element) || isValid;
}, "请输入有效的电话号码");

//价格
$.validator.addMethod("money", function(value, element, param) {
    return this.optional(element) || /^([1-9]\d*|0)(\.\d{1,2})?$/.test(value);
}, "请输入有效的价格");

//最大日期
$.validator.addMethod("maxDate", function(value, element, param) {
	var endDate = $("input[name='" + param[0] + "']").val();
    return this.optional(element) || (endDate && typeof endDate === 'string') ? new Date(endDate) >= new Date(value) : true;
});

//最小日期
$.validator.addMethod("minDate", function(value, element, param) {
	var startDate = $("input[name='" + param[0] + "']").val();
    return this.optional(element) || (startDate && typeof startDate === 'string') ? new Date(startDate) <= new Date(value) : true;
});

//字数统计
$.validator.addMethod("rangeByteLength", function(value, element, param) {
	var isValid = true, blen = 0;
	for (var i = 0; value && i < value.length; i++) {
		if ((value.charCodeAt(i) & 65280) != 0) {
			blen++;
		}
		blen++;
	}
	if (blen > param[1] || blen < param[0]) {
		isValid = false;
	}
    return this.optional(element) || isValid;
});

//兼容火狐（时间格式为yyyy-MM-dd HH:mm:ss）
$.validator.addMethod("dateTime", function(value, element, param) {
	if(value.length==19){
		console.log(value);
		var year = value.substring(0,4);
		var month = value.substring(5,7);
		var day = value.substring(8,10);
		var hour = value.substring(11,13);
		var minute = value.substring(14,16);
		var secend = value.substring(17,19);
		return this.optional( element ) || !/Invalid|NaN/.test( new Date(year,month,day,hour,minute,secend).toString() );
	}
	return false;
});

//验证富文本框内容
$.validator.addMethod("specialInput", function(value, element, param) {
	var ptext = $(".note-editable").find('p').text();
	var phtml = $(".note-editable").find('p').html();
	if(ptext!=null&&ptext!=""){
		return true;
	}
	if(phtml!=""&&phtml!="<br>"){
		return true;
	}
	return false;
});

//验证标签长度
$.validator.addMethod("tagMaxLength", function(value, element, param) {
	var i = value.indexOf(",");
	if(i<0){
		return true;
	}
	var number  = value.split(",");
	if(number.length<4){
		return true;
	}
	return false;
});