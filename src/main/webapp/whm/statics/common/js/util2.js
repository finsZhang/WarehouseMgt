/*
 *  crm util.js by fangll
 *  depend jquery-1.1.js+
 * 
*/
/*常用工具类*/

var WEB = {};
WEB.WEBROOT = window.GLOBAL.WEBROOT||"/partybuild";
WEB.$d = function (id) {
	var obj = document.getElementById(id);
	if (obj != null){
		return obj;
	}else{
		try{
			obj = top.window.frames['frm_top'].document.getElementById(id);	
		}catch(e){
			obj = null;
		}
		if (obj != null){
			return obj;
		}
	}
	return null;
};
	 
WEB.checkNum = function(obj) {
	if(event.keyCode == 13 ||event.keyCode == 8){
	   return;	
	}
	if ((event.keyCode < 44 || event.keyCode > 58)) {
		alert("\u8bf7\u8f93\u5165\u6570\u5b57");
		obj.focus();
		event.returnValue = false;
	}
}; 

WEB.pad = function (str,length,flag,pos){//格式化字符串为length长度,不足用flag补,pos{"r":右补,其它:左补}
	str = str + "";
	var len = length - (str.length);
	var p = "";
	for (var i = 1; i <= len; i++) {
		p += flag;
	}
	if(pos=="r"){
		str = str + "" + p;
	}else{
		str = p + "" + str;
	}
	return str;
};
	
WEB.lpad = function(str,len){//左补零
	var t = "0000000000000000"+str;
	return t.substring(t.length-len,t.length);
};

WEB.rpad = function(str,len){//右补零
	var t = str+"0000000000000000";
	return t.substring(0,len);
};

WEB.replaceAll = function(str,f,t){
	return  str.replace(new RegExp(f,"gm"),t);
};

WEB.trim = function (strValue){
   return strValue.replace(/^\s*|\s*$/g,"");
};	

WEB.placeholder =function(){	     
  var ary = [];			   
  for(i = 1 ; i < arguments.length ; i++){	  
   ary.push(arguments[i]);
  }	 
  return arguments[0].replace(/\{(\d+)\}/g,function(m ,i){
    return ary[i];
  });
};

WEB.htmlEscape = function(str){
	return str.replace(/<[^>].*?>/g,"");
};	
	
//math
WEB.round=function (number,X) {//4舍5入
    //rounds number to X decimal places, defaults to 2
	X = (!X ? 2 : X);
	return Math.round(number*Math.pow(10,X))/Math.pow(10,X);
};

WEB.random=function(first,last){//产生 first~last范围内的随时数
  var max = last-first+1;
  return Math.floor(Math.random()*max+first);
};
	
//checkbox event
WEB.isSelected=function (str, substr) {
	var strArr = str.split(",");
	for (var i = 0; i < strArr.length; i++) {
		if (strArr[i] == substr) {
			return true;
		}
	}
	return false;
};	

WEB.isChecked=function(obj){
   var d=document.getElementsByName(obj);
   for(var i=0;i<d.length;i++){
      if(d[i].checked) return true;
   }
   return false;
};	

WEB.checkRev=function (cname) {
	var obj = document.getElementsByName(cname);
	var len = obj.length;
	for (var i = 0; i < len; i++) {
		if (obj[i].checked) {
			obj[i].checked = false;
		} else {
			obj[i].checked = true;
		}
	}
};

WEB.checkNone=function (cname) {
	var obj = document.getElementsByName(cname);
	var len = obj.length;
	for (var i = 0; i < len; i++) {
		obj[i].checked = false;
	}
};

WEB.checkAll=function (cname) {
	var obj = document.getElementsByName(cname);
	var len = obj.length;
	for (var i = 0; i < len; i++) {
		obj[i].checked = true;
	}
};

WEB.checkToggle=function (cname) {
	var obj = document.getElementsByName(cname);
	var len = obj.length;
	var o = event.srcElement;
	for (var i = 0; i < len; i++) {
		obj[i].checked = o.checked;
	}
};

//String
WEB.NVL=function(str){
	return (str == null) ? "" : str;
};
	 
WEB.getValue2=function (str, de) {
    str = WEB.NVL(str);
	return (str == "") ? de : str;
}; 
WEB.getValue=function (str) {
	return WEB.NVL(str);
}; 

	
WEB.popParam=function(options){
  var p="";
  if(options.param){//window parameter	      
	  if(options.param.w!=undefined){p+="dialogWidth:"+options.param.w+"px;";}else{p+="dialogWidth:360px;";}
	  if(options.param.h!=undefined){p+="dialogHeight:"+options.param.h+"px;";}else{p+="dialogHeight:300px;";}
	  if(options.param.s!=undefined){p+="status:"+options.param.s+";";}else{p+="status:no;";}
	  if(options.param.m!=undefined){p+="menubar:"+options.param.m+";";}else{p+="menubar:no;";}
	  if(options.param.r!=undefined){p+="resizable:"+options.param.r+";";}else{p+="resizable:yes;";}
	  if(options.param.p!=undefined){p+="help:"+options.param.p+";";}else{p+="help:no;";}
         var x = e=window.event?(window.event.screenX):0;	
         var y = e=window.event?(window.event.screenY+13):0;
	  if(options.param.y!=undefined){p += "dialogTop:"+(y+options.param.y)+"px;";}
	  if(options.param.x!=undefined){p += "dialogLeft:"+(x+options.param.x)+"px;";}
	  p+="maximize:yes";
	  //minimize:yes;
  }else{
      p+="dialogWidth:360px;";
      p+="dialogHeight:300px;";
      p+="status:no;";
      p+="resizable:yes;";
      p+="menubar:no;";
      p+="help:no;";	  
  }		  
  return p;

};
	
	//window
WEB.popDlg = function (url, w, h,t,l) {   
    var x = e=window.event?(window.event.screenX):0;	
    var y = e=window.event?(window.event.screenY+13):0;	      
	var p = "";
	p += "help:no";
	p += ";status:no";
	p += ";dialogWidth:" + w + "px";
	p += ";dialogHeight:" + h + "px";
	if(t!=undefined){p += ";dialogTop:"+(y+t)+"px";}
	if(l!=undefined){p += ";dialogLeft:"+(x+l)+"px";}
	p += ";menubar:no";
	p += ";resizable:yes";
	p += ";maximize:yes";
	//;minimize:yes
	return window.showModalDialog(url, self, p);
};
	
WEB.pop = function (url, w, h,t,l) { 
	if(!w) w = 500;
	if(!h) h = 300;
    var u = WEB.WEBROOT+"/common/pop.jsp?url="+url;
	return WEB.popDlg(u,w,h,t,l);
};	
	
WEB.popWin = function(options){
  return WEB.popObj(options);
};
	
WEB.popObj = function(options){//格式为:{url:url,data:[],param:{},where:{}}
	if(!(options instanceof Object)){
		alert("options参数错误,必须为json格式!");
		return;
	}
	
	if(!options.url){ 
	  alert("url参数错误(不能为空)");
	  return;
	}	 
	if(!options.data){
	  alert("data参数错误(不能为空):"+options.data);
	  return;
	}	   
	var p = WEB.popParam(options);  
	//prompt("",p);
	//return window.showModelessDialog(options.url, options.data, p);
	var u = options.url;
	
	//alert(options.where)
	if(options.where!=undefined){//追加where参数
		var flag = (u.indexOf("?")>=0)?"&":"?";
		u += WEB.json2Param(options.where,flag);
	}
	
	if(u.indexOf("/common/pop.jsp")<0){
		u = WEB.WEBROOT+"/common/pop.jsp?url="+u;
	}
	
	return window.showModalDialog(u, options.data, p);
};
	
	
WEB.getElementWidth = function(o) {
	//x = document.getElementById(objectId);
	return x.offsetWidth;
},
	
WEB.getAbsoluteLeft = function(o) {
	// Get an object left position from the upper left viewport corner
	//o = document.getElementById(objectId)
	oLeft = o.offsetLeft            // Get left position from the parent object
	while(o.offsetParent!=null) {   // Parse the parent hierarchy up to the document element
		oParent = o.offsetParent    // Get parent object reference
		oLeft += oParent.offsetLeft // Add parent left position
		o = oParent
	}
	return oLeft
};
	
WEB.getAbsoluteTop = function(o) {
		// Get an object top position from the upper left viewport corner
		//o = document.getElementById(objectId)
		oTop = o.offsetTop            // Get top position from the parent object
		while(o.offsetParent!=null) { // Parse the parent hierarchy up to the document element
			oParent = o.offsetParent  // Get parent object reference
			oTop += oParent.offsetTop // Add parent top position
			o = oParent
		}
		return oTop
	};	
	
	//cookie
WEB.getCookie = function(l){
  var i="",I=l+"=";
  if(document.cookie.length>0){
     offset=document.cookie.indexOf(I);
     if(offset!=-1){
        offset+=I.length;
        end=document.cookie.indexOf(";",offset);
        if(end==-1)end=document.cookie.length;
        i=unescape(document.cookie.substring(offset,end))
      }
   };return i
};

WEB.setCookie = function(O,o,l,I){
	   var i="",c="";
	   if(l!=null){
	     i=new Date((new Date).getTime()+l*3600000);
	     i="; expires="+i.toGMTString();
	   }
	   if(I!=null){
	     c=";domain="+I;
	   }
	   document.cookie=O+"="+escape(o)+i+c
	 };
	 
WEB.request = function(strName){
 	var strHref = window.document.location.href; 
 	var intPos = strHref.indexOf("?"); 
 	var strRight = strHref.substr(intPos + 1); 
 	var arrTmp = strRight.split("&");
 	for(var i = 0; i < arrTmp.length; i++) { 
 		var arrTemp = arrTmp[i].split("="); 
 		if(arrTemp[0].toUpperCase() == strName.toUpperCase()) 
 		return arrTemp[1]; 
 	}
 	return "";
};

WEB.addLog = function(s){
	WEB.$d("log").innerHTML +=(s+"<br/>");
	var h = WEB.$d("log").offsetHeight;
	WEB.$d("wlog").scrollTop = h;
};

WEB.clearLog = function(){
	WEB.$d("log").innerHTML ="";
};

//date
WEB.today = function(){
  var d = new Date();
  var y = d.getYear();
  var m = d.getMonth() + 1;
      m = (m>=10)?m:"0"+m;
  var dy = d.getDate();
      dy= (dy>=10)?dy:"0"+dy;
  var s = y+"-"+m+"-"+dy;
  return s;	
};

WEB.now = function(){
  var d = new Date();	
  var t = WEB.today()
  var h = d.getHours();
  h = (h>=10)?h:"0"+h;
  var m= d.getMinutes();
  m = (m>=10)?m:"0"+m;
  var s = d.getSeconds();
  s = (s>=10)?s:"0"+s;
  return t+" "+h+":"+m+":"+s;	
};


	
//file
WEB.fileName=function(dir){
  return dir.substring(dir.lastIndexOf("\\")+1,dir.length);	
};
	
WEB.fileExt=function(dir){
  return dir.substring(dir.lastIndexOf(".")+1,dir.length);
};

WEB.fileShortName=function(dir){
  return dir.substring(dir.lastIndexOf("\\")+1,dir.lastIndexOf("."));
};
	
/*设置当前位置*/
WEB.setPath = function(url){		
    if(top && top.frm_top){
       top.frm_top.showPath();
	   top.frm_top.setPath(url);
	   return;
	}else if(parent && parent.GLOBALPATH){
	   parent.GLOBALPATH.showPath();
	   parent.GLOBALPATH.setPath(url);
	   return;
	}else if(parent.parent && parent.parent.GLOBALPATH){
	   parent.parent.GLOBALPATH.showPath();
	   parent.parent.GLOBALPATH.setPath(url);
	   return;
	}else{
	   return;		
	}
};
	
WEB.hidePath=function(){
    if(top && top.frm_top){
        top.frm_top.hidePath();
        return;
	}else if(parent && parent.GLOBALPATH){
	   parent.GLOBALPATH.hidePath();
	   return;
	}else if(parent.parent && parent.parent.GLOBALPATH){
	   parent.parent.GLOBALPATH.hidePath();
	   return;
	}else{
	   return;		
	}
};

WEB.showPath=function(){
   if(top && top.frm_top){
        top.frm_top.showPath();
        return;
	}else if(parent && parent.GLOBALPATH){
	   parent.GLOBALPATH.showPath();
	   return;
	}else if(parent.parent && parent.parent.GLOBALPATH){
	   parent.parent.GLOBALPATH.showPath();
	   return;
	}else{
	   return;		
	}
}; 	


/**================
     页签全局API
   ================**/

/*新增页签
 *option.title:页签名称,必须
 *option.url:路径(包含${ctx}的全路径),必须
 *refresh:如果页面已存在,是否刷新页面{true:刷新,false:不刷新},可选,默认不刷新
*/
WEB.addTab = function(option){
	if(window.TAB){
		window.TAB.add(option);
		return;
	}else if(window.parent && window.parent.TAB){
		window.parent.TAB.add(option);
	}else if(window.parent.parent && window.parent.parent.TAB){
		window.parent.parent.TAB.add(option);
	}else if(window.parent.parent.parent && window.parent.parent.parent.TAB){
		window.parent.parent.parent.TAB.add(option);
	}else if(window.parent.parent.parent.parent && window.parent.parent.parent.parent.TAB){
		window.parent.parent.parent.parent.TAB.add(option);
	}else{
		if(option && option.url){
			//alert(WEB.WEBROOT+option.url);
			window.open(WEB.WEBROOT+"/"+option.url,"_blank","width=800,height=500;");
		}else{
			alert("url参数不能为空");
		}
	}
};

/*
 * 显示主页
 * refresh:是否刷新页面{true:刷新,false:不刷新},可选,默认不刷新 
 */
WEB.homeTab = function(refresh){
	if(window.TAB){
		window.TAB.home(refresh);
		return;
	}else if(window.parent && window.parent.TAB){
		window.parent.TAB.home(refresh);
	}else if(window.parent.parent && window.parent.parent.TAB){
		window.parent.parent.TAB.home(refresh);
	}else if(window.parent.parent.parent && window.parent.parent.parent.TAB){
		window.parent.parent.parent.TAB.home(refresh);
	}else if(window.parent.parent.parent.parent && window.parent.parent.parent.parent.TAB){
		window.parent.parent.parent.parent.TAB.home(refresh);
	}else{
		return;	
	}
};

/**
* 根据title选择页签
* refresh:是否刷新页面{true:刷新,false:不刷新},可选,默认不刷新
*/
WEB.selectTab = function(title,refresh){
	if(window.TAB){
		window.TAB.select(title,refresh)
		return;
	}else if(window.parent && window.parent.TAB){
		window.parent.TAB.select(title,refresh)
	}else if(window.parent.parent && window.parent.parent.TAB){
		window.parent.parent.TAB.select(title,refresh)
	}else if(window.parent.parent.parent && window.parent.parent.parent.TAB){
		window.parent.parent.parent.TAB.select(title,refresh)
	}else if(window.parent.parent.parent.parent && window.parent.parent.parent.parent.TAB){
		window.parent.parent.parent.parent.TAB.select(title,refresh)
	}else{
		return;	
	}
};

/**
* 根据title删除页签
*/
WEB.removeTab = function(title){	
	if(window.TAB){
		window.TAB.close(title);
		return;
	}else if(window.parent && window.parent.TAB){
		window.parent.TAB.close(title);
	}else if(window.parent.parent && window.parent.parent.TAB){
		window.parent.parent.TAB.close(title);
	}else if(window.parent.parent.parent && window.parent.parent.parent.TAB){
		window.parent.parent.parent.TAB.close(title);
	}else if(window.parent.parent.parent.parent && window.parent.parent.parent.parent.TAB){
		window.parent.parent.parent.parent.TAB.close(title);
	}else{
		return;	
	}

};

/**
* 根据title刷新页签
*/
WEB.refreshTab = function(title){	
	if(window.TAB){
		window.TAB.refresh(title);
		return;
	}else if(window.parent && window.parent.TAB){
		window.parent.TAB.refresh(title);
	}else if(window.parent.parent && window.parent.parent.TAB){
		window.parent.parent.TAB.refresh(title);
	}else if(window.parent.parent.parent && window.parent.parent.parent.TAB){
		window.parent.parent.parent.TAB.refresh(title);
	}else if(window.parent.parent.parent.parent && window.parent.parent.parent.parent.TAB){
		window.parent.parent.parent.parent.TAB.refresh(title);
	}else{
		return;	
	}
}

/**
* 取当前标签iframe对象
*/
WEB.getTabSelectFrame = function(){
	if(window.TAB){
		return window.TAB.getSelectFrame();
	}else if(window.parent && window.parent.TAB){
		return window.parent.TAB.getSelectFrame();
	}else if(window.parent.parent && window.parent.parent.TAB){
		return window.parent.parent.TAB.getSelectFrame();
	}else if(window.parent.parent.parent && window.parent.parent.parent.TAB){
		return window.parent.parent.parent.TAB.getSelectFrame();
	}else if(window.parent.parent.parent.parent && window.parent.parent.parent.parent.TAB){
		return window.parent.parent.parent.parent.TAB.getSelectFrame();
	}else{
		return {};
	}
}

/**=========
  全局窗口操作
=============**/

//获取窗口对象
WEB.getWINObj=function(){
	if(window.WIN){
		return window;
	}else if(window.parent && window.parent.WIN){ 
		return window.parent;
	}else if(window.parent.parent && window.parent.parent.WIN){ 
		return window.parent.parent;
	}else if(window.parent.parent.parent && window.parent.parent.parent.WIN){ 
		return window.parent.parent.parent;
	}else if(window.parent.parent.parent.parent && window.parent.parent.parent.parent.WIN){ 
		return window.parent.parent.parent.parent;
	}else{
		return null;
	}
}

//获取窗体可见最大宽度
WEB.getMaxWindowWidth=function(){
	var winobj = WEB.getWINObj();
	if(winobj == null){
		winobj =  window;
	}
	
	var clientWidth=winobj.document.body.clientWidth;//其它浏览器默认值
	if(navigator.userAgent.indexOf("MSIE 6.0")!=-1) {
		clientWidth=winobj.document.body.clientWidth;
	}
	else if(navigator.userAgent.indexOf("MSIE")!=-1) {
		//IE7 IE8
		clientWidth=winobj.document.documentElement.offsetWidth;
	}
	
	if(navigator.userAgent.indexOf("Chrome")!=-1) {
		clientWidth=winobj.document.body.scrollWidth;
	}
	
	if(navigator.userAgent.indexOf("Firefox")!=-1) {
		clientWidth=winobj.document.documentElement.scrollWidth;
	}
	
	return clientWidth ;
}



//获取窗体可见的最大高度
WEB.getMaxWindowHeight=function(){
	var winobj = WEB.getWINObj();
	if(winobj == null){
		winobj =  window;
	}
	
	var clientHeight=winobj.document.body.clientHeight;//其它浏览器默认值
	if(navigator.userAgent.indexOf("MSIE 6.0")!=-1) {
		clientHeight=winobj.document.body.clientHeight;
	}
	else if(navigator.userAgent.indexOf("MSIE")!=-1) {
		//IE7 IE8
		clientHeight=winobj.document.documentElement.offsetHeight
	}
	
	if(navigator.userAgent.indexOf("Chrome")!=-1) {
		clientHeight=winobj.document.body.scrollHeight;
	}
	
	if(navigator.userAgent.indexOf("Firefox")!=-1) {
		clientHeight=winobj.document.documentElement.scrollHeight;
	}
	
	return clientHeight ;
}



WEB.openWin = function(option){	
	//获取窗口对象
	var winobj = WEB.getWINObj();
	
	if(winobj != null){
		var maxHeight=WEB.getMaxWindowHeight();
		var maxWidth= WEB.getMaxWindowWidth();
		
		//处理高度，如果高度为full，或者指定的高度超过最大高度
		if(typeof(option.height)!="undefined" && 
		   (
		      (option.height+"").toLowerCase() =="full" || option.height>=maxHeight
		    )){
			//如果有设置top,则不需要设置为0  
			if(!option.top && option.top <= 0){
				option.top=0;
			}
			option.height=maxHeight;
		} 
		
		//处理宽度
		if(typeof(option.width)!="undefined"  && 
		   (
		      (option.width+"").toLowerCase() =="full" || option.width>=maxWidth
		    )){
			option.left=0;
			option.width=maxWidth;
		} 
		
		//处理左右居中
		if(typeof(option.left)=="undefined" || option.left==''){ 
			option.left =( maxWidth-option.width)/2;
		}
		
		//处理上下居中
		if(typeof(option.top)=="undefined" || option.top==''){ 
			option.top =( maxHeight-option.height)/2;
		}
	
		
	
		winobj.WIN.open(option);
	}
	
	//用普通窗口打开
	else{
		if(option && option.url){
			//alert(WEB.WEBROOT+option.url);
			var width = option.width||800;
			var height = option.height||500;
			window.open(WEB.WEBROOT+"/"+option.url,"_blank","width="+width+",height="+height+";resizable=y");
		}else{
			alert("url参数不能为空");
		}
	}
};

//关闭全局窗口
WEB.closeWin = function(id, param){
	if(window.WIN){
		window.WIN.close(id, param);
		return;
	}else if(window.parent && window.parent.WIN){
		window.parent.WIN.close(id, param);
	}else if(window.parent.parent && window.parent.parent.WIN){
		window.parent.parent.WIN.close(id, param);
	}else if(window.parent.parent.parent && window.parent.parent.parent.WIN){
		window.parent.parent.parent.WIN.close(id, param);
	}else if(window.parent.parent.parent.parent && window.parent.parent.parent.parent.WIN){
		window.parent.parent.parent.parent.WIN.close(id, param);
	}else{
		return;	
	}
};


WEB.openMsn = function(option){	
	if(window.MSN){
		window.MSN.open(option);
		return;
	}else if(window.parent && window.parent.MSN){
		window.parent.MSN.open(option);
	}else if(window.parent.parent && window.parent.parent.MSN){
		window.parent.parent.MSN.open(option);
	}else if(window.parent.parent.parent && window.parent.parent.parent.MSN){
		window.parent.parent.parent.MSN.open(option);
	}else if(window.parent.parent.parent.parent && window.parent.parent.parent.parent.MSN){
		window.parent.parent.parent.parent.MSN.open(option);
	}else{
		//alert(option.msn);
	}
};

//关闭全局消息窗口
WEB.closeMsn = function(){	
	if(window.MSN){
		window.MSN.close();
		return;
	}else if(window.parent && window.parent.MSN){
		window.parent.MSN.close();
	}else if(window.parent.parent && window.parent.parent.MSN){
		window.parent.parent.MSN.close();
	}else if(window.parent.parent.parent && window.parent.parent.parent.MSN){
		window.parent.parent.parent.MSN.close();
	}else if(window.parent.parent.parent.parent && window.parent.parent.parent.parent.MSNMSN){
		window.parent.parent.parent.parent.MSN.close();
	}else{
		return;	
	}
};

WEB.checkIsImage=function(id){
	var fileName = document.getElementById(id).value;
	if(fileName != '' && fileName != null){
		var lastIndex = fileName.substr(fileName.lastIndexOf(".")+1 , fileName.length);
		lastIndex = lastIndex.toLowerCase();
		if(lastIndex != 'jpg' && lastIndex != 'gif' && lastIndex != 'bmp' && lastIndex != 'jpeg' && lastIndex != 'png'){
			alert("请选择一张图片！");
			return false;
		}
	}
	return true;
}
WEB.checkIsXls=function(id){
	var fileName = document.getElementById(id).value;
	if(fileName != '' && fileName != null){
		var lastIndex = fileName.substr(fileName.lastIndexOf(".")+1 , fileName.length);
		lastIndex = lastIndex.toLowerCase();
		if(lastIndex != 'xls' && lastIndex != 'xlsx'){
			alert("请选择Excel文件上传！");
			return false;
		}
	}
	return true;
}

/*showmodel方式添加附件*/
WEB.getAttach = function(){
	var url = WEB.WEBROOT+"/tools/upload/upload.jsp?ref=1";
	var u = WEB.WEBROOT+"/common/pop.jsp?url="+url;	
	var options = {url:u,data:[],param:{w:620,h:390,r:"no"}};
	return WEB.popObj(options);
};
	
//设置附件列表
WEB.addAttach = function(attachId){
	var divId = "_div_"+attachId;
	var ulId =  "_ul_"+attachId;
	var docId = attachId+"_docIds";
	var list = WEB.getAttach();
	var li = "";
	var docIds=$("#"+docId).val();		
	var docArr=docIds.split(",");
	if(list!=undefined){
		li = ""; //<ul>			
		for(var i=0;i<list.length;i++){
			var did = list[i].docId;
			var isExist = WEB.attachIsExist(did,docArr);				
			if(!isExist){
				li += "<li id='doc_"+docId+did+"'>";
				li += list[i].docTitle;
				li += "<img src='"+WEB.WEBROOT+"/images/icon/del.gif' title='删除附件' onclick=\"WEB.delAttach('"+attachId+"','"+did+"')\">;";
				li += "</li>";
				docIds +=","+did;
			}
		}
		li +=""; //</ul>
		if(docIds.substring(0,1)==","){
			docIds = docIds.substring(1,docIds.length);
		}
		$("#"+ulId).append(li);
		$("#"+docId).val(docIds);
		//alert($("#"+docId).val());
	}	
};
	
//附件是否已关联,存在:true
WEB.attachIsExist = function(did,docArr){		
	//var flag = false;
	for(var j=0;j<docArr.length;j++){			
		if(docArr[j]==did){
			return true; 
		}
	}
	return false;
};
	
//删除附件
WEB.delAttach = function (attachId,did){
	if(!confirm("确定删除吗?")){
		return;
	}
	var divId = "_div_"+attachId;
	//var docId = "_"+attachId;
	var docId = attachId+"_docIds";
	//删除li列表
	$("#doc_"+docId+did).remove();
	
	//更新docId
	var docIds = $("#"+docId).val();
	var arr = docIds.split(",");
	var newArr = $.grep( arr, function(n,i){
	    return (n != did);
	});		
	$("#"+docId).val(newArr.toString());		
	//alert(arrH.toString()+"\n"+arr.toString()); 		
	return ;
};
	
//查看附件
WEB.viewAttach = function(did,target){	    
    if(document.getElementById("attachFrm")==null){	
        var ifm = document.createElement("<iframe name='attachFrm' id='attachFrm' width='0' height='0' src='"+WEB.WEBROOT+"/common/blank.jsp'>");
	    /*		    
	    ifm.setAttribute("name",target);		    
	    ifm.name = target; 	//不支持name设置	    
	    ifm.id=target;
	    ifm.width=10;
	    ifm.height=10;		    
	    //ifm.style.display="none";
	    */
	    document.body.appendChild(ifm);			    
	}else{		
		var obj = document.frames["attachFrm"].document.body;
		if(obj!=null){
		   obj.innerText = "";
		}			
	}

	var win = window.open(WEB.WEBROOT+"/download.action?docId="+did,"attachFrm");
    return;	
};

//追加附件 opt = {refId:"357",refMoudle:"bus_chg_info",docType:"attach",newDocType:"attach2"}
WEB.appendAttach = function(opt){
	var refId = opt.refId||"";
	var refModule= opt.refModule||"";
	var docType = opt.docType||"";
	var newDocType = opt.newDocType||"attach";
	if(refId=="" || refModule==""){
		//alert("refId,refMoudle参数不能为空");
		return;
	}
	var p = {"sqlName":"common.attachQuery"
			,"page.autoCount":"true"
			,"page.pageNo":"1"
			,"page.pageSize":"100"
			,"params.refId":refId
			,"params.refModule":refModule
			,"params.docType":docType			
			}
	var url = WEB.WEBROOT+"/common/ibatisQuery.action";
	$.getJSON(url, p,
		function(data){
		   var str='';
		   var li="";
		   var docIds="";
		   var result = data.result;
		   for(var i=0;i<result.length;i++){
			  li +=("<li id='doc_"+newDocType+"_docIds"+result[i].DOC_ID+"'>");
			  li +=("<a href=\"javascript:WEB.viewAttach('"+result[i].DOC_ID+"','attachFrm')\">");
			  li +=(result[i].DOC_TITLE+"."+result[i].DOC_KIND);
			  li +=("</a>");
			  li +=("<img src='images/icon/del.gif' title='删除附件' onclick=\"WEB.delAttach('"+newDocType+"','"+result[i].DOC_ID+"')\">");
			  li +=("</li>");
			  docIds += ","+result[i].DOC_ID;  
		      //$("#attach").html();
		   }
		   if(docIds!=""){docIds = docIds.substring(1);}
		   $("#_ul_"+newDocType).html(li);
		   $("#"+newDocType+"_docIds").val(docIds);
		   //alert($("#"+newDocType+"_docIds").val());
		   //alert(str);
	})

}

//清空引用
WEB.resetRef = function(id){
	$("#"+id).val("");
	$("#"+id+"_text").val("");	
}

//提示信息
WEB.message = function(data){
		var url = WEB.WEBROOT+"/common/message.jsp";
		return window.showModalDialog(url, data, "dialogWidth:320px;dialogHeight:220px;");
};

/*
 *提示信息
 *显示提示信息,n秒后隐藏
 */
WEB.alt = function(id,str,n){
	n = n||5000;
	$('#'+id).fadeIn(500,function(){
		$(this).html(str);
		$(this).fadeOut(n,function(){$(this).html("");});
	}); 
}

/**
 * 把json解析为url参数
 * o:jsonObject
 * pre:之前参数
 */
WEB.json2Param=function(o, pre){
	var value, buf = [], key, e = encodeURIComponent; 
	for(key in o){ 
		value = (o[key]==undefined)?"":e(o[key]);
		buf.push("&", e(key), "=",value);
	} 
	if(!pre){ 
		buf.shift(); 
		pre = ""; 
	} 
	return pre + buf.join(''); 
}; 

/**
 * 把url参数解析为json
 * string: url
 * overwrite:
 */
WEB.param2Json=function(string, overwrite){
	var obj = {}, pairs = string.split('&'),d = decodeURIComponent,name,value; 
	$.each(pairs, function(i,pair) { 
		pair = pair.split('='); 
		name = d(pair[0]); 
		value = d(pair[1]); 
		obj[name] = overwrite || !obj[name] ? value : [].concat(obj[name]).concat(value); 
	}); 
	return obj; 
}; 


/*
 * 报表导出函数, 注意列表中如果有操作列，操作列的名称必须是act。参数说明如下：
 * url：指请求Action，后面不带任何参数，有需要跟参数的，请将这些参数设置在condition中
 * thNames:  列表的表头数组, jqgrid自身的列表表头(colNames属性)即可
 * colModel: 列表的属性列数组(必须与thNames数组的长度一致),jqgrid自身的列表属性列数组(colModel)即可
 * condition: 这是一个JSON对象, 数据查询的过滤条件以及一些其他的参数设置
 * isExpHidCols: boolean类型,指"是否导出隐藏列"标志, 默认不导出隐藏列,true表示导出隐藏列,false表示不导出隐藏列
 */
WEB.exportXls = function(url, colNames, colModel, condition, isExpHidCols) {
	var thNames = new Array();
	thNames = thNames.concat(colNames);
	var colNames = new Array();
	var j = 0;
	if(isExpHidCols == true) {		
		for(var i=0; i < colModel.length; i++){
			if(colModel[i].name=='act' || colModel[i].name == 'rn' ||  colModel[i].name == 'cb'){ 				
				thNames = thNames.remove(j);
				continue;
			}			
			j++;
			colNames.push(colModel[i].name);
		}
	} else {	    
	    for(var i=0; i < colModel.length; i++){
			if(colModel[i].hidden || colModel[i].name=='act' || colModel[i].name == 'rn'||  colModel[i].name == 'cb'){				
				thNames = thNames.remove(j);
				continue;
			}			
			j++;
			colNames.push(colModel[i].name);
		}
	}
	j=0;
	var params = "thNames="+encodeURIComponent(thNames.toString())+"&colNames="+encodeURIComponent(colNames.toString());
	/*
	for(var p in condition) {
		params += "&"+p+"="+condition[p];
	}
	*/	
	params=WEB.json2Param(condition,params);
	var ifm = document.getElementById("exportXlsFrm");
    if(ifm == null){	
        ifm = document.createElement("iframe");
		ifm.id = "exportXlsFrm";
		ifm.style.position = "absolute";
		ifm.style.zIndex = "1";
		ifm.style.visibility = "hidden";
		ifm.style.height="0px";
		ifm.style.width="0px";
		ifm.style.top="0px";
		ifm.style.left="0px";
		document.body.appendChild(ifm);
    }    
    ifm.src = url + "?" + params;	
}

/*
 * 报表导出函数。自定义导出报表表头名称和报表导出的属性列。参数说明如下：
 * url：指请求Action，后面不带任何参数，有需要跟参数的，请将这些参数设置在condition中
 * thNames:  导出报表的表头数组
 * colModel: 导出报表的属性列数组(必须与thNames数组的长度一致)
 * condition: JSON对象, 数据查询的过滤条件以及一些其他的参数设置
 */
WEB.cusExportXls = WEB.myExportXls = function(url, thNames, colNames, condition){ 
	var params = "thNames="+encodeURIComponent(thNames.toString())+"&colNames="+encodeURIComponent(colNames.toString());
	/*
	for(var p in condition) {
		params += "&"+p+"="+condition[p];
	}
	*/
	params=WEB.json2Param(condition,params);	
    var ifm = document.getElementById("exportXlsFrm");
    if(ifm == null){	
        ifm = document.createElement("iframe");
		ifm.id = "exportXlsFrm";
		ifm.style.position = "absolute";
		ifm.style.zIndex = "1";
		ifm.style.visibility = "hidden";
		ifm.style.height="0px";
		ifm.style.width="0px";
		ifm.style.top="0px";
		ifm.style.left="0px";
		document.body.appendChild(ifm);
    }
    ifm.src = url + "?" + params;
}

/**
 * @param: 权限字符串，以“，”分隔
 * @return:jsonArray
 */
WEB.getPrivilage = function(param){
	var url = WEB.WEBROOT+"/sys/checkPrivilage!getPersonalPrivilage.action";
	var result = null;
	$.ajax({  
        type : "post",  
        url : url,  
        async : false,
        
        data : {
        	"privilages": param
        },
        dataType: 'json',  
        success : function(data){  
          result = data.result;
        }  
    }); 
    return result;
}

/**
  维系附件CRUD
**/

var ATTACH_ID=1;
ATTACH={};
ATTACH.add=function(flag){
	ATTACH_ID++;
	var ATTACH_MODEL = flag;
    var str = '<li id="'+ATTACH_MODEL+'Attach_'+ATTACH_ID+'">';
    str +='附件:<input type="text" class="w100 r" name="'+ATTACH_MODEL+'FileName" readonly="true"/><input type="file" name="'+ATTACH_MODEL+'File" value="" id="'+ATTACH_MODEL+'File'+ATTACH_ID+'" class="w20" onchange="ATTACH.change(this)"/>';
    str +="&nbsp;<img class='icon-attach' align='middle' src='"+WEB.WEBROOT+"/images/icons/delete.png' onclick=\"ATTACH.remove('"+ATTACH_MODEL+"Attach_"+ATTACH_ID+"')\">";
	str +='</li>';
	
	var attachSize = $("#"+ATTACH_MODEL+"AttachList").children().size();
	if(attachSize>=10){
		alert("批量添加附件不能超过10个");
		return;
	}else{
		$("#"+ATTACH_MODEL+"AttachList").append(str);
	}	
}

ATTACH.remove = function(id){
    $("#"+id).remove();
}


ATTACH.Exits=function (o){
  var cfile = $(o).val();
  var oname = $(o).attr("name");
  var oid =  $(o).attr("id");
  var fileList = document.getElementsByName(oname);
  for(i=0;i<fileList.length;i++){
  	if(fileList[i].value==cfile && oid!=fileList[i].id){
  		alert(cfile+"您选择的附件已经存在,请不要重复选择附件.");
  		$(o).val("");
  		$(o).prev().val("");
  		return true;
  	}
  }
  return false;
}

//删除附件
ATTACH.del=function(f,id){
	if(confirm("确定要删除附件吗?")){
		ATTACH.remove(f+"_"+id+"_attach");
		var url = WEB.WEBROOT+"/allservice/addinfo!del.so";
		var p = {"p":f,"id":id};
		$.post(url,p,function(result){
			alert(result);
		});
	}	
}

ATTACH.change=function(o){
  //$(t).val(WEB.fileShortName(o.value));
  if(!ATTACH.Exits(o)){
 	 $(o).prev().val(WEB.fileName($(o).val()));
  }
}


WEB.doAreaChange = function (po,cid,query){
	var p = $(po).val();
	var url = WEB.WEBROOT+"/common/cache.action";
	var param = {"type":"county","areaId":p,"countySource":$("#"+cid).attr("source")};
	var addstr = "";
	if(query){
		addstr = '<option value="">全部</option>';
	}else{
		addstr = '<option value="">请选择</option>';
	}
	$("#"+cid).empty();
	$.get(url,param,function(result){
		$("#"+cid).append(addstr+result);
	})
}	




/**删除数组对象中某个元素,jquery有现成的*/
Array.prototype.remove = function(n) {  
	//n表示第几项，从0开始算起。
	//prototype为对象原型，注意这里为对象增加自定义方法的方法。
	var len = this.length;
	if(n < 0 || n > len){  //如果n<0或者n>数组长度时，则不进行任何操作。
		return this;
	} else {
		if( n == 0){ this.shift(); return this;} //删除第一个元素
		if( n == len - 1){ this.pop(); return this;} //删除最后一个元素
		return this.slice(0,n).concat(this.slice(n+1,this.length));
   	}
}


//.caption自动添加开关功能
$(document).ready(function(){
	//.caption添加开关
	$(".caption").prepend('<span class="icon-switch icon-plus"></span>').end()
	$(".caption .icon-switch").click(function() {	
		$(this).toggleClass("icon-minu");
		var oid = $(this).attr("id");
		if(oid==""){
			$(this).parents(".caption:first").next().toggle();
			//$(this).parents(".caption:first").next().slideToggle(300);
		}else{
			$("[id^='"+oid+"_']").toggle();
			//$("[id^='"+oid+"_']").slideToggle(300);
		}		
	});								
});

//legend.fswitch自动添加开关功能
$(document).ready(function(){
	$("legend.fswitch").css({"cursor":"pointer"});
	$("legend.fswitch").prepend('<span class="icon-switch icon-plus">&nbsp;&nbsp;&nbsp;&nbsp;</span>').end();
	$("legend.fswitch").click(function() {	
		$(this).next().toggle();
		$(this).children("span").toggleClass("icon-minu");
	});	
});

//.enlarge 单击放大
$(document).ready(function(){
	
	//add by hanlq 防止点击鼠标左键页面拖动
	/**
	$('div').live('selectstart',function(){
		return false;
	});
	
	$('input').live('selectstart',function(){
		return true;
	});
	*/
	$('body').live('dragstart',function(){
		return false;
	});
	
	$("input.enlarge").click(function() {
		var srcValue = $(this).val();
		var url= WEB.WEBROOT+"/common/enlarge.jsp";
		var data = [{"src":srcValue,ref:"result"}];
		var param = {w:300,h:200};
		var result = WEB.popObj({url:url,data:data,param:param});	
		if(result){
		   $(this).val(result);
		}
	});	
});
/*
*名称:图片上传本地预览插件 v1.1
*参数说明: Img:图片ID;Width:预览宽度;Height:预览高度;ImgType:支持文件类型;Callback:选择文件显示图片后回调方法;
*使用方法: 
<div>
<img id="ImgPr" width="120" height="120" /></div>
<input type="file" id="up" />
把需要进行预览的IMG标签外 套一个DIV 然后给上传控件ID给予uploadPreview事件
$("#up").uploadPreview({ Img: "ImgPr", Width: 120, Height: 120, ImgType: ["gif", "jpeg", "jpg", "bmp", "png"], Callback: function () { }});
*/
jQuery.fn.extend({
    uploadPreview: function (opts) {
        var _self = this,
            _this = $(this);
        opts = jQuery.extend({
            Img: "ImgPr",
            Width: 100,
            Height: 100,
            ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
            Callback: function () {}
        }, opts || {});
        _self.getObjectURL = function (file) {
            var url = null;
            if (window.createObjectURL != undefined) {
                url = window.createObjectURL(file)
            } else if (window.URL != undefined) {
                url = window.URL.createObjectURL(file)
            } else if (window.webkitURL != undefined) {
                url = window.webkitURL.createObjectURL(file)
            }
            return url
        };
        _this.change(function () {
            if (this.value) {
                if (!RegExp("\.(" + opts.ImgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {
                    alert("选择文件错误,图片类型必须是" + opts.ImgType.join("，") + "中的一种");
                    this.value = "";
                    return false
                }
                if ($.browser.msie) {
                    try {
                        $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
                    } catch (e) {
                        var src = "";
                        var obj = $("#" + opts.Img);
                        var div = obj.parent("div")[0];
                        _self.select();
                        if (top != self) {
                            window.parent.document.body.focus()
                        } else {
                            _self.blur()
                        }
                        src = document.selection.createRange().text;
                        document.selection.empty();
                        obj.hide();
                        obj.parent("div").css({
                            'filter': 'progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)',
                            'width': opts.Width + 'px',
                            'height': opts.Height + 'px'
                        });
                        div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src
                    }
                } else {
                    $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
                }
                opts.Callback()
            }
        })
    }
});

//导出
function exportFlexigridExcel(grid){
	   var form = $("#flexiGridDownForm_" + $(grid.selector).attr("id"));
	   if(form.length==0){
		   html="<div style='display:none'><form id='flexiGridDownForm_queryTable' method='post'><input type='hidden' name='flexiGirdColModel'></form><div></div></div>";
		   $($(grid.selector)).after(html);
	   }
	   var p = grid[0].p;
	   	rp = p.maxRp; 
		page = 1;
		var param = [{
			name : p.searchModel
					? p.searchModel + '.page'
					: 'searchModel.page',
			value : page
		}, {
			name : p.searchModel
					? p.searchModel + '.rp'
					: 'searchModel.rp',
			value : rp
		}, {
			name : p.searchModel
					? p.searchModel + '.sortname'
					: 'searchModel.sortname',
			value : p.sortname
		}, {
			name : p.searchModel
					? p.searchModel + '.sortorder'
					: 'searchModel.sortorder',
			value : p.sortorder
		}, {
			name : 'flexiGridType',
			value : 'FlexiGridExcelDown'
		}];
		if (p.params) {
			for (var pi = 0; pi < p.params.length; pi++) {
				param[param.length] = p.params[pi];
			}
		}
		var url = "";
		$.each(param, function(i, n) {
					if (i > 0) {
						url = url + "&";
					}
					url = url + n.name + "=" + encodeURI(n.value);
				})
		// 设置action，其实就是查询的时候，所需要的URL ;
		$("#flexiGridDownForm_" + $(grid.selector).attr("id")).attr('action',
				p.url + "?" + url);
		// 列选择；//序列化
		if (p.colModel) {
			$(
					"#"+form.attr("id")
							+ " :hidden[name='flexiGirdColModel']").val(JSON.stringify(p.colModel));
		}
		form.submit();
     	
     }