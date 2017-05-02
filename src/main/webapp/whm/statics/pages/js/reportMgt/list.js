var dispatchClerkGlobal;

$(function () {
    // $("#dispatchClerk").select2();
    initUserList();
    selectList();
});


function resetCondition() {
    com.ai.bdx.util.reset('searchForm');
    reloadGrid();
}

function reloadGrid(){
    if(dispatchClerkGlobal!=getDispatchClerk()){//动态改变表格列
        var dispatchClerk = getDispatchClerkOrderByUsername();
        $("#user_table").GridUnload();
        var colNames = ['日期','星期','日派单总金额','日派单总行数','日派单总个数'];
        var colModel = [
            {name: 'createDate', index: 'createDate', sortable: false,fixed:false,width:130,align:'center' },
            {name: 'weekNo', index: 'weekNo', sortable: false,align:'center',resizable:true,fixed:false ,width:80},
            {name: 'amt', index: "amt", sortable: false,align:'right',width:100},
            {name: 'lineNum', index: "lineNum", sortable: false,align:'right',width:100},
            {name: 'num', index: "num", sortable: false,align:'right',width:100},
        ];
        var groupHeaders = [
            {startColumnName: 'createDate', numberOfColumns: 5, titleText: '<bold>统计汇总</bold>'}
        ];
        for(var i=0;i<dispatchClerk.length;i++){
            colNames.push("派单总金额");
            colNames.push("派单总行数");
            colNames.push("派单总个数");

            colModel.push({name: 'amt_'+(i+1), index: "amt_"+(i+1), sortable: false,align:'right',width:100});
            colModel.push({name: 'lineNum_'+(i+1), index: "lineNum_"+(i+1), sortable: false,align:'right',width:100});
            colModel.push({name: 'num_'+(i+1), index: "num_"+(i+1), sortable: false,align:'right',width:100});

            groupHeaders.push({startColumnName: 'amt_'+(i+1), numberOfColumns: 3, titleText: '<bold>'+dispatchClerk[i]+'</bold>'});
        }

        initGrid(groupHeaders,colNames,colModel);

    }else{//无需处理
        var data = $("#searchForm").serializeObject();
        data.dispatchClerk = getDispatchClerk();
        $("#user_table").jqGrid("setGridParam", {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
    }

}

function getDispatchClerk() {
    var dispatchClerkArray = $("#dispatchClerk").select2("val");
    //按用户名从小到大排序
    dispatchClerkArray.sort(function(a,b){
        return a>b?1:-1;});
    var dispatchClerk = "";
    for(var i=0;i<dispatchClerkArray.length;i++){
        if(dispatchClerkArray.length==1){
            dispatchClerk = "'"+dispatchClerkArray[i]+"'";
        }else{
            if(i==dispatchClerkArray.length-1){
                dispatchClerk = dispatchClerk + "'"+dispatchClerkArray[i]+"'";
            }else{
                dispatchClerk = dispatchClerk + "'"+dispatchClerkArray[i]+"',";
            }
        }
    }
    return dispatchClerk;
}

//查询列表
function selectList() {
    var colNames = ['日期','星期','日派单总金额','日派单总行数','日派单总个数'];
    var colModel = [
        {name: 'createDate', index: 'createDate', sortable: false,fixed:false,width:130,align:'center' },
        {name: 'weekNo', index: 'weekNo', sortable: false,align:'center',resizable:true,fixed:false ,width:80},
        {name: 'amt', index: "amt", sortable: false,align:'right',width:100},
        {name: 'lineNum', index: "lineNum", sortable: false,align:'right',width:100},
        {name: 'num', index: "num", sortable: false,align:'right',width:100}
    ];
    var groupHeaders = [ {startColumnName: 'createDate', numberOfColumns: 5, titleText: '<em>统计汇总</em>'}];
    initGrid(groupHeaders,colNames,colModel);
}

var records;
/**
 * 导出统计记录
 */
function exportRecordTotalRcds(){
    if(!records){
        info("温馨提示","导出条数为空！");
        return;
    }
    var data = $("#searchForm").appendParam();
    if(data){
        window.location.href= GLOBAL.WEBROOT+"/reportMgt/exportRecordTotalRcds.html?"+data;
    }else{
        window.location.href= GLOBAL.WEBROOT+"/reportMgt/exportRecordTotalRcds.html";
    }
}

var obj;
var objs;
//初始化用户下拉列表
function initUserList() {
    var flag = false;
    //检查状态下拉列表
    $.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT+"/reportMgt/getSysUserList.ajax",
        success: function (data) {
            $("#dispatchClerk").select2();
            var jsonStatus = eval(data.userList);
            var statusSelect = $("#dispatchClerk");
            objs = new Array();
            for (var i = 0; i < jsonStatus.length; i++) {
                statusSelect.append("<option value='" + jsonStatus[i].userName  + "'>" + jsonStatus[i].name + "</option>");
                obj = new Object();
                obj.userName = jsonStatus[i].userName;
                obj.name = jsonStatus[i].name;
                objs.push(obj);
            }
            $("#dispatchClerk").select2();
            objs.sort(function(a,b){
                return a.userName>b.userName?1:-1;});
            flag = true;
        }
    });
    return flag;
}

function initGrid(groupHeaders,colNames,colModel){
    var grid_selector = "#user_table";
    var pager_selector = "#user_pager";
    var data = $("#searchForm").serializeObject();
    if(data.dispatchClerk){//选择了
        data.dispatchClerk = getDispatchClerk();
    }else{
        var dispatchClerk = "";
        for(var i=0;i<objs.length;i++) {
            if (objs.length == 1) {
                dispatchClerk = "'" + objs[i].userName + "'";
            } else {
                if (i == objs.length - 1) {
                    dispatchClerk = dispatchClerk + "'" + objs[i].userName + "'";
                } else {
                    dispatchClerk = dispatchClerk + "'" + objs[i].userName + "',";
                }
            }
        }

        for(var i=0;i<objs.length;i++){
            colNames.push("派单总金额");
            colNames.push("派单总行数");
            colNames.push("派单总个数");

            colModel.push({name: 'amt_'+(i+1), index: "amt_"+(i+1), sortable: false,align:'right',width:100});
            colModel.push({name: 'lineNum_'+(i+1), index: "lineNum_"+(i+1), sortable: false,align:'right',width:100});
            colModel.push({name: 'num_'+(i+1), index: "num_"+(i+1), sortable: false,align:'right',width:100});

            groupHeaders.push({startColumnName: 'amt_'+(i+1), numberOfColumns: 3, titleText: '<bold>'+objs[i].name+'</bold>'});
        }
        data.dispatchClerk = dispatchClerk;
    }
    $(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT + "/reportMgt/queryShipmenRcdAllList.ajax",
        mtype : "post",
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        caption: '仓库出货统计',
        colNames: colNames,
        colModel: colModel,
        viewrecords: false,
        rowNum:10,
        rowList:[10,15,20,30],
        multiselect: false,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function (data) {
            records = data.records;
            com.ai.bdx.util.updatePagerIcons(this);
            $(grid_selector).attr("width","100%");
        },
        autoWidth:true
    }).jqGrid("navGrid",pager_selector,{refresh: true});
    if(groupHeaders){ //跨列标头
        $(grid_selector).jqGrid('setGroupHeaders', {
            useColSpanStyle: false,
            groupHeaders:groupHeaders
        });
    }
}

function getDispatchClerkOrderByUsername(){
    var dispatchClerkArray = $("#dispatchClerk").select2("data");
    //按用户名从小到大排序
    dispatchClerkArray.sort(function(a,b){
        return a.id>b.id?1:-1;});

    var dispatchClerk = new Array();
    for(var i=0;i<dispatchClerkArray.length;i++){
        dispatchClerk.push(dispatchClerkArray[i].text);
    }
    return dispatchClerk;
}


