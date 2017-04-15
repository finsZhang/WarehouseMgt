(function(window, undefined) {
    var document = window.document;
    var $ = window.$;
    var _push_common = window.push_common;

    var Push_common = function() {

    };
    
    Push_common.prototype = {
    
    	//总计时
    	totalTime : 120, 
    
    	/**
         * 点击发送验证码按钮
         * @param obj : 点击发送按钮对象
         * @param phoneNo : 电话号码
         * @param businessType: 业务类型
         * @param codeId : 验证码返回input隐藏框ID(自己定义，默认是pushMsgRetCode)
         */
        pushMsg : function(obj, phoneNo, businessType, codeId,msgCode) {
    	 
            //开始倒计时
            $(obj).attr("class","timer");
   		    $(obj).val("验证码获取中...");
   		    $(obj).attr("disabled", true); 
   		    var code = this.getPushCode(phoneNo, businessType);
   		    if(code == '-999'){
   		    	 $(obj).attr("class","register_btndtm");
   		    	 $(obj).val("发送动态码");
   		    	 $(obj).attr("disabled", false); 
   		    }else if(code == '-777'){
   		    	$(obj).val("请等待90秒重新操作");
   		    //验证码发送成功事件
   		    }else{
   		    	if(codeId == null || codeId == ''){
   		    		codeId = "pushMsgRetCode";
   		    	}
//   		    	//验证码回填
//   		    	var objs = $(obj).parent().children();
//   		    	if(objs.length > 0){
//   		    		$(objs[0]).val(code);
//   		    	}
//   		        //预约短信验证码回填
//   		    	if(businessType == "IF_BOOKING"){
//   		    		$("#"+msgCode).val(code);
//   		    		$("#msgCode").change();
//   		    	}   		    	
   		    	
   		    	var html = "<input id='"+codeId+"' type='hidden' value='"+code+"'>";
   		    	$(obj).after(html);
   		    	//倒计时开始
   		    	this.startTime(obj);
   		    }
        },
        
        /**
         * 获取验证码
         * @param phoneNo: 发送验证码电话号码
         * @param businessType: 业务类型
         * return code ： -999 异常请求或链接超时   code : -888 连续请求
         */
        getPushCode : function(phoneNo, businessType){
        	 
        	var code = '-999';
        	$.ajax({
                "type" : "POST",
                "async" : false, 
                "url" : GLOBAL.WEBROOT+"/platform/common/pushCode/getPushCode.ajax",
                "data" : {
                	"phoneNo" : phoneNo,
                	"businessType":businessType
                },
                "success" : function(data) {
                	if(data.success){
                		code = data._msg;
                	}
                },
                "error" : function(){
                	
                }
            });
        	return code;
        },
        
        /**
         * 校验短信验证码是否正确
         * @param businessType: 业务类型
         * @return boolean：true 验证通过  false 验证失败
         */
        checkPushCode : function(code, businessType){
        	var flag = false;
        	$.ajax({
                "type" : "POST",
                "async" : false, 
                "url" : GLOBAL.WEBROOT+"/platform/common/pushCode/checkPushCode.ajax",
 
                "data" : {
                	"businessType":businessType,
                	"code" : code
                },
                "success" : function(data) {
                	if(data.success){
                		flag = true;
                	}
                },
                "error" : function(){
                	
                }
            });
        	return flag;
        },
        
        startTime : function(obj){
        	 var totalTime = this.totalTime;
        	 var iTimeId = window.setInterval(function(){
                if(totalTime == -1){
                    $(obj).attr("class","register_btndtm");
	   		    	$(obj).val("发送动态码");
	   		    	$(obj).attr("disabled", false); 
	   		    	window.clearInterval(iTimeId);
                }else{
                	$(obj).attr("class","timer");
		   		    $(obj).val(totalTime+"秒后重新获取");
                }
                totalTime = totalTime -1;
             },1000);
        }
        
    };

    window.pushCommon = new Push_common();

})(window, undefined);