/**
 * 定义JS命名空间
 */
if (typeof com == "undefined") {
    var com = {};
}

com.ai = com.ai || {}
com.ai.bdx = com.ai.bdx || {};

com.ai.bdx.util = com.ai.bdx.util || (function () {
	/**
	 * 所有查询界面公共js
	 * 重置查询条件
	 * @memberOf {TypeName} 
	 */
	function reset(args){
		$("#"+args).find("input,textarea").each(function(){
		 	this.value ="";
		});
		//查询界面select2
		$("#"+args).find("select").each(function(){
			 $("#s2id_"+this.id).find("span:first").text($("#"+this.id+" option:first").text());
			 $("#"+this.id).val(-1);
		});
		
	}

    /**
     * href的get提交转post提交
     * url为固定参数，必须有
     * 其余为不定参数，必须为数组类型，其中第一个元素是name，第二个元素为value eg:["id",1]
     * eg: hrefPost('test.html',['id',1],['name','zhangsan']...)
     * @param url
     */
    function hrefPost(url) {

        var html = "<form action=" + url + " method='post' name='hrefForm' style='display:none'>"

        for (var i = 1; i < arguments.length; i++) {
            html += "<input type='hidden' name='" + arguments[i][0] + "' value='" + arguments[i][1] + "'>"
        }
        html += "</form>";

        document.write(html);
        document.hrefForm.submit();

    }
    
    function hrefPostMult(url,params) {

        var html = "<form action=" + url + " method='post' name='hrefForm' style='display:none'>"

        if(isNotEmpty(params)){
        	var items = params.params;
        	for(var i = 0 ;i < items.length; i ++){
        		html += "<input type='hidden' name='" + items[i][0] + "' value='" + items[i][1] + "'>"
        	}
        }
        
//        for (var i = 1; i < arguments.length; i++) {
//            html += "<input type='hidden' name='" + arguments[i][0] + "' value='" + arguments[i][1] + "'>"
//        }
        html += "</form>";

        document.write(html);
        document.hrefForm.submit();

    }

    /**
     * 校验对象是否为空
     * @param obj
     * @returns {boolean}
     */
    function isEmpty(obj) {
        if (obj == undefined || obj == null || obj == "") {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 校验对象是否为空
     * @param obj
     * @returns {boolean}
     */
    function isNotEmpty(obj) {
        if (obj == undefined || obj == null || obj == "") {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 校验对象是否为空 ,如果为undefined返回空字符串
     * @param obj
     * @returns {boolean}
     */
    function emptyVal(obj) {
        if (obj == undefined || obj == null || obj == "") {
            return "";
        } else {
            return obj;
        }
    }

    /**
     * IP地址合法性校验
     * @param ipAddr
     * @returns {boolean}
     */
    function ipValidate(ipAddr) {
        var patten = /^([1-9]|[1-9]\d{1}|1\d\d|2[0-4]\d|25[0-5])\.(([0-9]|[1-9]\d{1}|1\d\d|2[0-4]\d|25[0-5])\.){2}([1-9]|[1-9]\d{1}|1\d\d|2[0-4]\d|25[0-5])$/;
        if (patten.test(ipAddr)) {
            return true;
        }
        return false;
    }

    /**
     * 域名校验
     * @param domain
     * @returns {boolean}
     */
    function checkDomain(domain) {
        var patrn = /^[0-9a-zA-Z]+[0-9a-zA-Z\.-]*\.[a-zA-Z]{2,4}$/;
        if (patrn.test(domain)) return true;
        return false;
    }
    /**
     * 分页图标显示
     * @param {Object} table
     * @memberOf {TypeName} 
     */
    function updatePagerIcons(table) {
	    var replacement =
	    {
	        'ui-icon-seek-first': 'icon-double-angle-left bigger-140',
	        'ui-icon-seek-prev': 'icon-angle-left bigger-140',
	        'ui-icon-seek-next': 'icon-angle-right bigger-140',
	        'ui-icon-seek-end': 'icon-double-angle-right bigger-140'
	    };
	    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
	        var icon = $(this);
	        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
	
	        if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
	    })
	}


    /**
     * 树形下拉控件 支持单选及多选
     * @param inputSelector  页面组件input组件的ID
     * @param multi   是否是多选，如果true为多选，false单选
     * @param option  配置参数
     * @param callFunc 回调函数
     */
    function ztreeComp(inputSelector,multi,option,callFunc){
        var obj = $("#"+inputSelector);
        var treeId = inputSelector+"_tree";
        var divId = inputSelector+"_div";
        var t = $("<div id=\""+divId+"\"  style=\"display:none; position: absolute;z-index:9999;\"><ul id=\""+treeId+"\" class=\"ztree\" style=\"margin-top:0; width:"+(obj.innerWidth()+2)+"px; height: 150px;\"></ul></div>");
        $('body').after(t);
        var settingForMulti = {
            beforeClick: beforeClick,
            onCheck: onCheck,
            onClick: onClickForMulti
        };
        var settingForSingle = {
            onClick: onClickForSingle
        };
        var checkForMulti = {
            enable: true,
            chkboxType:{"Y":"s", "N":"s"}
        }
        var checkForSingle = {
            enable: false,
            radioType: "all"
        }
        var setting = {
            check: multi?checkForMulti:checkForSingle,
            async: {
                enable: true,
                url:option.data.url,
                autoParam:option.data.param,
                cache:false
            },
            view: {
                view: {
                    showLine: false,
                    selectedMulti: multi
                }
            },
            data: {
                simpleData: {
                    enable:true,
                    idKey: option.dataStructure.idKey,
                    pIdKey: option.dataStructure.pIdKey,
                    rootPId: option.dataStructure.rootPId
                },
                key:{
                    name:option.dataStructure.name
                }
            },
            callback: multi?settingForMulti:settingForSingle
        };

        //单选下回调函数，比如要回显我们指定的字段填充到其他字段中。
        function onClickForSingle(event, treeId, treeNode){
            if (!treeNode.isParent) {
                callFunc(treeNode);
                hideMenu();
            }
        }
        function onClickForMulti(treeNode) {
            zTree.expandNode(treeNode, true, true, true);
        }
        //解决多选下选中后再次选择取消选中
        function beforeClick(treeNode) {
            zTree.checkNode(treeNode, !treeNode.checked, null, true);
            return false;
        }
        //多选下回调函数，比如要回显我们指定的字段填充到其他字段中。
        function onCheck() {
            callFunc(zTree);
        }

        initTree(treeId,setting);
        var zTree = $.fn.zTree.getZTreeObj(treeId);
        $("#"+inputSelector).click(function(){
            showMenu();
        })
        function showMenu() {
            var obj = $("#"+inputSelector);
            var objOffset = $("#"+inputSelector).offset();
            $("#"+divId).css({left:objOffset.left + "px", top:(objOffset.top+1) + obj.outerHeight() + "px"}).slideDown("fast");
            $("body").bind("mousedown", onBodyDown);
        }
        function hideMenu() {
            $("#"+divId).fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }
        function onBodyDown(event) {
            if (!(event.target.id == inputSelector || event.target.id == divId || $(event.target).parents("#"+divId).length>0)) {
                hideMenu();
            }
        }

        function initTree(){
            $.fn.zTree.init($("#"+treeId), setting,option.rootNode);
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            var nodes = treeObj.getNodes();
            if (nodes.length>0) {
                treeObj.reAsyncChildNodes(nodes[0], "refresh");
            }
        };
    }
    return{
        hrefPost: hrefPost,
        hrefPostMult:hrefPostMult,
        isEmpty: isEmpty,
        isNotEmpty: isNotEmpty,
        ipValidate: ipValidate,
        checkDomain: checkDomain,
        emptyVal: emptyVal,
        ztreeComp:ztreeComp,
        updatePagerIcons:updatePagerIcons,
        reset : reset
    }
}())

//电话验证
function isPhone(str){
	return new RegExp("^0?(130|131|132|133|134|135|136|137|138|139|145|147|150|151|152|153|155|156|157|158|159|170|177|180|181|182|183|185|186|187|188|189)[0-9]{8}$").test(str);
}
//序列化对象
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

Date.prototype.format = function(format)
{
    var o =
    {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(), //day
        "h+" : this.getHours(), //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3), //quarter
        "S" : this.getMilliseconds() //millisecond
    }

    if(/(y+)/.test(format))
    {
        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }

    for(var k in o)
    {
        if(new RegExp("("+ k +")").test(format))
        {
            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
        }
    }
    return format;
}

//yyyy-MM-dd hh:mm:ss
function timestampToDate(time,format){
    return new Date(time).format(format)
}

function dateFormat(dateString,format) {
    if(!dateString)return "";
    var time = new Date(dateString.replace(/-/g,'/').replace(/T|Z/g,' '));
    var o = {
        "M+": time.getMonth() + 1, //月份
        "d+": time.getDate(), //日
        "h+": time.getHours(), //小时
        "m+": time.getMinutes(), //分
        "s+": time.getSeconds(), //秒
        "q+": Math.floor((time.getMonth() + 3) / 3), //季度
        "S": time.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (time.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format)) format = format.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return format;
}

function fmoney(s, n, step)
{
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    if(!step)
       return s;
    var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1];
    var t = "";
    for(var i = 0; i < l.length; i ++ )
    {
        t += l[i] + ((i + 1) % step == 0 && (i + 1) != l.length ? "," : "");
    }
    return t.split("").reverse().join("") + "." + r;
}

function fmoney2float(money){
    if(!money){
        return 0;
    }
    return parseFloat(money.replace(/,/g,''));
}

//弹出框;  
function alterInfo(title,msg){
    layer.alert(msg,{icon: 0,title:title});
}   
//错误弹出框;  
function errorInfo(title,msg){   
	jQuery.messager.alert(title,msg,'error');   
}   
//信息弹出框;
function info(title,msg){
    layer.alert(msg,{icon: 0,title:title});
}

//信息弹出框,瞬间关闭;
function infoClose(title,msg,fn){
    layer.alert(msg,{icon: 0,title:title,closeBtn:0},fn);
}

//警告弹出框;  
function warning(title,msg){   
	jQuery.messager.alert(title,msg,'warning');   
}
//信息弹出框,保存成功是调用;
function infoCall(title,msg,fn){
    layer.alert(msg,{icon: 1,title:title,closeBtn:0},fn);
}

//信息弹出框;
function layerConfirm(msg,fn){
    layer.confirm(msg, {icon: 3, title:'提示'},fn);
}
//信息弹出框;
function infoSuccess(title,msg){
    layer.alert(msg,{icon:1,title:title});
}

//右下角弹出框;  
function slide(title,msg){   
	jQuery.messager.show({   
	title:title,   
	msg:msg,   
	timeout:5000,   
	showType:'slide'  
});  
	
}


function callFuncForMultiQuery(zTree) {
    var nodes = zTree.getCheckedNodes(true);
    var v = "";
    var n = "";
    for (var i = 0, l = nodes.length; i < l; i++) {
        v += nodes[i].orgCode + ",";
        n += nodes[i].orgName + ",";
    }
    if (v.length > 0) v = v.substring(0, v.length - 1);
    if (n.length > 0) n = n.substring(0, n.length - 1);
    $('#orgCodes').val(v);
    $('#orgNames').val(n);
}

function displayExecuteLayer(){
    var left = ($(window).width()/2 - 70)+"px";
    var top = ($(window).height()/2 - 28)+"px";
    $('.executing').css({'left':left,'top':top});
    $(".executing").show();
    $(".overlay").show();
}

function hideExecuteLayer(){
    $(".executing").hide();
    $(".overlay").hide();
}

function isMoney(value){
    var exp = /^([1-9][\d]{0,4}|0)(\.[\d]{1,2})?$/;
    if(exp.test(value)){
        return parseFloat(value);
    }else{
        return false;
    }
}

function validateCashbox(operatorCode,message){
    var flag = false;
    $.ajax({
        type: "POST",
        async: false,
        timeout:5000,
        url: GLOBAL.WEBROOT + "/cashbox/checkCashboxOwner",
        cache:false,
        data: "ownerCode=" + operatorCode,
        success: function (data) {
            if (data) {
                info('温馨提示',message);
                flag = true;
            }
        },
        error:function(){
            info('温馨提示','获取钱箱失败！');
            flag = true;
        }
    });
    return flag;
}

function getDiffDays(strDateStart,strDateEnd){
    var strSeparator = "-"; //日期分隔符
    var oDate1;
    var oDate2;
    var iDays;
    oDate1= strDateStart.split(strSeparator);
    oDate2= strDateEnd.split(strSeparator);
    var strDateS = new Date(oDate1[0], oDate1[1]-1, oDate1[2]);
    var strDateE = new Date(oDate2[0], oDate2[1]-1, oDate2[2]);
    iDays = parseInt((strDateE - strDateS) / 1000 / 60 / 60 /24)//把相差的毫秒数转换为天数
    return iDays ;
}

function validateOneCard(cardNoParam){
    var msg = readOCX.readPublicInfo('{"requestInfo": {"departmentCode": "KSDM","departmentName": "KSMC","operatorCode": "CZRDM","operatorName": "CZRXM"}}');
    var obj = $.parseJSON(msg);
    var result = obj.responseInfo;
    if (result.isSuccess != "0") {
        info('温馨提示', result.errMsg);
        return false;
    }
    var cardInfo = obj.businessInfo;
    var cardNo = cardInfo.cardNo;
    if(cardNo!=cardNoParam) {
       return  false;
    }else{
        return true;
    }
}

function readCardNo(){
    var msg = readOCX.readPublicInfo('{"requestInfo": {"departmentCode": "KSDM","departmentName": "KSMC","operatorCode": "CZRDM","operatorName": "CZRXM"}}');
    var obj = $.parseJSON(msg);
    var result = obj.responseInfo;
    if(result.isSuccess!="0"){
        info('温馨提示',result.errMsg);
        return null;
    }
    var cardInfo = obj.businessInfo;
    var cardNo = cardInfo.cardNo;
    return cardInfo.cardNo;
}

var cardMaxAmount = 1000;

function getPageResource(pagePath,callback) {
    $.ajax({
        type: "POST",
        async:false,
        url: GLOBAL.WEBROOT + "/sysmanage/getPageResource.ajax?pagePath=" + pagePath,
        cache: false,
        success:callback
    });
}

Array.prototype.containRes = function(item){
    return RegExp(item).test(this);
};


function printTicket(url,width,height){
    var top=Math.round((window.screen.height-height)/2);
    var left=Math.round((window.screen.width-width)/2);
    window.open (url, 'newwindow', 'height='+height+'px, width='+width+'px, top='+top+',left='+left+', toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
}