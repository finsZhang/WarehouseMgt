$(function () {
	 if(window.top!=window){
		 window.top.location.href=window.location.href;
	 }
	 
    /*登陆*/
    $("#loginSubmit").click(function () {
        login($(this).attr("name"));
    });

   
    loginBack();

    //解决ie下不支持placeholder（占位符）问题
	placeholder();
	//图片验证码切换
	$("#imageCheckCode").click(function(){
  		var url = $(this).attr("src");
  		if(url.indexOf("?") != -1){
  			$(this).attr("src",$(this).attr("src") + "&nocache=" + new Date().getTime());
  		}else{
      		$(this).attr("src",$(this).attr("src") + "?nocache=" + new Date().getTime());
  		}
	});
});
//解决ie下不支持placeholder（占位符）问题
function placeholder(){
	var doc=document,inputs=doc.getElementsByTagName('input'),supportPlaceholder='placeholder'in doc.createElement('input'),placeholder=function(input){var text=input.getAttribute('placeholder'),defaultValue=input.defaultValue;
    if(defaultValue==''){
        input.value=text}
        input.onfocus=function(){
            if(input.value===text){this.value=''}};
            input.onblur=function(){if(input.value===''){this.value=text}}};
            if(!supportPlaceholder){
                for(var i=0,len=inputs.length;i<len;i++){var input=inputs[i],text=input.getAttribute('placeholder');
                if(input.type==='text'&&text){placeholder(input)}}}
}


//用户登陆验证
function login(tab) {
    var accountName = $("div#" + tab + " input[name='accountName']").val();//用户名
    var password = $("div#" + tab + " input[name='passwd']").val();//密码

    //用户名密码验证
    if (accountName == "" || accountName=="用户名/邮箱/手机号") {
        $("#alt_" + tab).html("<i></i>请输入用户名!");
        $("div#" + tab + " input[name='accountName']").focus();
        return false;
    }
    if (password == "") {
        $("#alt_" + tab).html("<i></i>请输入密码!");
        $("div#" + tab + " input[name='passwd']").focus()
        return false;
    }

    //WEB.setCookie("user", accountName, 100);
    //WEB.setCookie("pwd", password, 100);  --密码不能缓存吧
   //WEB.setCookie("savePass", "1", 100);

    //记住帐号密码
//    if($("#chkUserPsw").attr("checked")=='checked'){
//        var userCode = $("#normal").find("input[name=accountName]").val();//用户名
//        var password = $("#normal").find("input[name=passwd]").val();//密码
//        //存取cookie
//        setCookie("user",userCode,100);
//        setCookie("pwd",password,100);
//
//    }else{
//        setCookie("user","",100);
//        setCookie("pwd","",100);
//    }

    var checkCode = $("div#" + tab + " input[name='imageCode']").val();//验证码

    if( checkCode==""){
        $("#alt_" + tab).html("<i></i>请输入验证码!");
        $("div#" + tab + " input[name='imageCode']").focus();
        return false;
    }else if(!verifyCode(tab)){
        $("#alt_" + tab).html("<i></i>验证码不正确!");
        return false;
    }
        
    var url =  GLOBAL.WEBROOT+"/whm/statics/common/images/loading.gif";
    $("#alt_" + tab).html(" <img src='"+url+"' style='width:16px;height:16px'>校验中,请稍候..");
    $("#loginForm").submit();
}

//验证图片验证码
function verifyCode(tab){
	var checkCodeFlag = false;
	var checkCode = $("div#" + tab + " input[name='imageCode']").val();//验证码
    if(checkCode == undefined || checkCode.length != 4){
    	$("#alt_" + tab).html("<i>验证码不正确!</i>");
        return checkCodeFlag;
    }
    var page = "login_user";
    $.ajax({
        type: "POST",
        async: false,
        cache:false,
        url:GLOBAL.WEBROOT +  '/whm/common/checkCode/validate.ajax',
        data: {checkCode:checkCode,page:page},
        dataType:'json',
        success:function(data){
            if(data.success){
                checkCodeFlag = true;
            }else{
            	$("#alt_" + tab).html("<i>验证码不正确!</i>");
            }
        }
    });
    return checkCodeFlag;
}
//操作成功后返回值的操作
function loginBack() {
    var errorCode = $("#errorCode").val();
   
    if(errorCode == "0" || errorCode == ""){
        return;
    }
    var tab = "normal";
    switch (errorCode) {
        case "-1" :
        {
            $("#alt_" + tab).html("<i></i>验证码错误!")
            break;
            $("#login").attr("disabled", false);
        }
        case "0" :
        {
            window.top.location = "/whm/test/test.html";  //测试
            break;
            $("#login").attr("disabled", true);
        }
        case "1" :
        {
            $("#alt_" + tab).html("<i></i>该用户不存在!");
            $("#login").attr("disabled", false);
            break;
        }
        case "2" :
        {
            $("#alt_" + tab).html("<i></i>用户名或密码错误!");
            $("#login").attr("disabled", false);
            break;
        }
        case "9" :
        {
            $("#alt_" + tab).html("<i></i>用户状态异常,请联系管理员!");
            $("#login").attr("disabled", false);
            break;
        }
        default  :
        {
            $("#alt_" + tab).html("<i></i>系统忙,请稍候再试!错误代码:" + errorCode);
            $("#login").attr("disabled", false);
        }
    }
}

//回车键盘监听事件
document.onkeydown = keyListener;
function keyListener(e) {
    e = e ? e : event;
    if (e.keyCode == 13) {
        login("normal");
    }
}


//重填
function cleanAll() {
    $("#normalRegistName").val("");
    $("#normalPass").val("");
//    $("#savePass").attr("checked", false);
}

//设置cookie
function setCookie(O,o,l,I){
   var i="",c="";
   if(l!=null){
     i=new Date((new Date).getTime()+l*3600000);
     i="; expires="+i.toGMTString();
   }
   if(I!=null){
     c=";domain="+I;
   }
   document.cookie=O+"="+escape(o)+i+c;
 }
 
 //获得cookie
function getCookie(l){
  var i="",I=l+"=";
  if(document.cookie.length>0){
     offset=document.cookie.indexOf(I);
     if(offset!=-1){
        offset+=I.length;
        end=document.cookie.indexOf(";",offset);
        if(end==-1)end=document.cookie.length;
        i=unescape(document.cookie.substring(offset,end));
      }
   };
   return i;
}
 
//从cookie中取值回填
$(function(){ 
	if(getCookie("user") !="" && getCookie("pwd") !=""){
		$("#chkUserPsw").attr("checked",true); 
	    $("#normal").find("input[name=accountName]").val(getCookie("user"));
	    $("#normal").find("input[name=passwd]").val(getCookie("pwd"));
	}else{
		$("#chkUserPsw").attr("checked",false); 
	}	
  
});
