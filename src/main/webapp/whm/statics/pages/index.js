var menus ;
var rootOrgId;
var rootOrgName;
var rootOrgCode;

$(function () {
	//$("#userinfo").html('<small>Welcome,</small>'+ user.operatorName);
    $("#userinfo").html('<small>Welcome,</small>'+ '小张');
    var divMenuHeader = $("#menuHeader");
    divMenuHeader.append('<div class="nav-item dropdown"><ul><li><div class="nav01" id="01"><a href="javascript:void(0)" onclick="queryMenuByParent(\'01\',\'01\')">出货记录管理</a></div></li></ul></div>');
    queryMenuByParent('01','01');
    divMenuHeader.append('<div class="nav-item dropdown"><ul><li><div class="nav01" id="02"><a href="javascript:void(0)" onclick="queryMenuByParent(\'02\',\'02\')">出货报表管理</a></div></li></ul></div>');
    queryMenuByParent('02','02');
    divMenuHeader.append('<div class="nav-item dropdown"><ul><li><div class="nav01" id="03"><a href="javascript:void(0)" onclick="queryMenuByParent(\'03\',\'03\')">用户管理</a></div></li></ul></div>');
    queryMenuByParent('03','03');
    divMenuHeader.append('<div class="nav-item dropdown"><ul><li><div class="nav01" id="04"><a href="javascript:void(0)" onclick="queryMenuByParent(\'04\',\'04\')">基础管理</a></div></li></ul></div>');


    /*$.ajax({
        type: "POST",
        async: false,
        cache:false,
        url:GLOBAL.WEBROOT +  '/whm/getMenus.ajax',
        dataType:'json',
        success:function(data){
                menus = eval(data.menus);
                if (menus) {
                var parentId;
                var topId;
                //find header topId;
                for (var i = 0; i < menus.length; i++) {
                    parentId = menus[i].parentMenuId;
                    if (parentId == '0') {
                        topId = menus[i].menuId;
                        break;
                    }
                }
                var divMenuHeader = $("#menuHeader");
                var j = 0;
                for (var i = 0; i < menus.length; i++) {
                    parentId = menus[i].parentMenuId;
                    if (parentId == topId) {
                        divMenuHeader.append('<div class="nav-item dropdown"><ul><li><div class="nav01" id="pmenuId_'+menus[i].menuId+'"><a href="javascript:void(0)" onclick="queryMenuByParent('+parentId+','+menus[i].menuId+')">' + menus[i].menuName + '</a></div></li></ul></div>');
                        if (j == 0) {
                            loadMenuTree(menus[i].menuId);
                            $("#pmenuId_"+menus[i].menuId).attr("class","nav02");
                            j++;
                        }
                    }
                }
            }
            var target = $("#menuTree").find("a")[0].target;
            if(target=="view_frame"){
                $("#menuTree").find("a")[0].click();
            }else{
                $("#menuTree").find("a")[1].click();
            }
        }
    });

    $.ajax({
        type: "POST",
        async: false,
        cache:false,
        url:GLOBAL.WEBROOT +  '/whm/getRootOrg.ajax',
        dataType:'json',
        success:function(data){
            initParam(data);
        }
    });*/
});

function initParam(data){
    rootOrgId = data.orgId;
    rootOrgName = data.orgName;
    rootOrgCode = data.orgCode;
    optionForMulti = {
        dataStructure: {
            idKey: "orgId",
            pIdKey: "parentOrgId",
            rootPId: rootOrgId,
            name: "orgName"
        },
        data: {
            url: GLOBAL.WEBROOT + "/common/getOrgs.ajax",    //获取数据的URL
            param: ["orgId"]  //获取数据的参数
        },
        rootNode: [{orgId: rootOrgId, orgName: rootOrgName,orgCode: rootOrgCode, open: true, isParent: true, nocheck: false}]
    };
    optionForSingle = {
        dataStructure: {
            idKey: "orgId",
            pIdKey: "parentOrgId",
            rootPId: rootOrgId,
            name: "orgName"
        },
        data: {
            url: GLOBAL.WEBROOT + "/common/getOrgs.ajax",    //获取数据的URL
            param: ["orgId"]  //获取数据的参数
        },
        rootNode: [{orgId: rootOrgId, orgName: rootOrgName,orgCode: rootOrgCode, open: true, isParent: true, nocheck: true}]
    };
}

function loadMenuTree(topId){
    var menuTree = $("#menuTree");
    menuTree.html('');
    if(topId=='01')
    	menuTree.append("<dl><h5><a href='shipmentMgt/page/list.html' target='view_frame'><i class='fM-l-icon2'>出货记录管理</i></a></h5><dl>");
    else if(topId=='02'){
        menuTree.append("<dl><h5><a href='shipmentMgt/page/list.html' target='view_frame'><i class='fM-l-icon2'>出货报表管理管理</i></a></h5><dl>");
	}else if(topId=='03'){
        menuTree.append("<dl><h5><a href='userMgt/list.html' target='view_frame'><i class='fM-l-icon2'>用户管理</i></a></h5><dl>");
    }else if(topId=='04'){
        menuTree.append("<dl><h5><a href='dictMgt/list.html' target='view_frame'><i class='fM-l-icon2'>字典管理</i></a></h5><dl>");
	}
   /* for(var i=0;i<menus.length;i++){
        //直接子节点
        if(topId == menus[i].parentMenuId) {
            if (menus[i].menuType == 1) {
				var menuUrl = menus[i].menuUrl;//menus[i].menuName-------------------
                menuTree.append("<dl><h5><a href='"+menuUrl+"' target='view_frame'><i class='fM-l-icon2'>" + menus[i].menuName + "</i></a></h5><dl>");
            } else {
                menuTree.append("<dl id='dl" + menus[i].menuId + "'><h5><a href='javascript:void(0);' onclick='changeMenu("+menus[i].menuId+")'><i class='fM-l-icon2'>" + menus[i].menuName + "</i></a></h5><dl>");
            }
        }
    }
    
    var dl
    for(var i=0;i<menus.length;i++){
        var menuId = menus[i].parentMenuId;
        dl = $("#dl"+menuId);
        if(dl){
			var menuUrl = menus[i].menuUrl;
			if(menuUrl&&menuUrl.indexOf("?username=")!=-1){
				menuUrl += cardMgtUsername;
			}
            dl.append('<dt><a href="'+ menuUrl +'" target="view_frame">'+menus[i].menuName+'</a></dt>');
        }
    }*/
}

function changeMenu(id){
	var dts = $("#dl"+id).find("dt");
	var cls = $("#dl"+id).attr("class");
	if(cls=='hideDiv'){
		if(dts&&dts.length>0){
			$("#dl"+id).removeClass("hideDiv");
			$("#dl"+id).addClass("showDiv");
			$("#dl"+id).find("i").removeClass();
			$("#dl"+id).find("i").addClass("fM-l-icon2");
			for(var i=0;i<dts.length;i++){
				$(dts[i]).show();		
			}
		}
	}else{
		if(dts&&dts.length>0){
			$("#dl"+id).removeClass("showDiv");
			$("#dl"+id).addClass("hideDiv");
			$("#dl"+id).find("i").removeClass();
			$("#dl"+id).find("i").addClass("showicon2");
			for(var i=0;i<dts.length;i++){
				$(dts[i]).hide();		
			}
		}
	}
	
}

function openMenu(url,menuId,name,isClose){
    WEB.addTab({title: name, 'url': url,closable:isClose});
}

function queryMenuByParent(parentMenuId,menuId){
    loadMenuTree(menuId);
    var target = $("#menuTree").find("a")[0].target;
    if(target=="view_frame"){
        $("#menuTree").find("a")[0].click();
    }else{
        $("#menuTree").find("a")[1].click();
    }
		//TAB.closeAll();
		/*var o_id = "pmenuId_"+menuId;
		var menus = $(".nav-item").find("div"); 
		if(menus&&menus.length>0){
			for(var i=0;i<menus.length;i++){
				var temp = $(menus[i]).attr("id"); 
				if(o_id==temp){
					$(menus[i]).attr("class","nav02");
					$("#menuTree").html('');
					loadMenuTree(menuId);
					var target = $("#menuTree").find("a")[0].target;
					if(target=="view_frame"){
						$("#menuTree").find("a")[0].click();
					}else{
						$("#menuTree").find("a")[1].click();
					}
				}else{
					$(menus[i]).attr("class","nav01"); 
				}
			} 
		}*/
}

function switchBarl(){
	 var imgsrc=document.all("makeleft").src;
	 if (imgsrc.indexOf("fM-left.png")>1){  
		 document.all("makeleft").src=GLOBAL.WEBROOT+"/whm/statics/pages/css/index/images/fM-right.png";
		 document.all("makeleft").title="隐藏左边的菜单";  
		 $("#left_content").removeClass();
		 $("#left_content").addClass("fl all_content");
//		 $("#left_scroll").removeClass();
//		 $("#left_scroll").addClass("fl l_left");
		 $("#left_scroll").css("left",0);
		 $("#menuTree").hide(); 
		 
	 }else{  
		 document.all("makeleft").src=GLOBAL.WEBROOT+"/whm/statics/pages/css/index/images/fM-left.png";
		 document.all("makeleft").title="显示左边的菜单";  
		 $("#left_content").removeClass();
		 $("#left_content").addClass("fl right");
		 $("#left_scroll").css("left",240);
//		 $("#left_scroll").addClass("fl l_close");
		 $("#menuTree").show(); 
	 }
}
