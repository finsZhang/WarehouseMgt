var dispatchClerkMap;

$(function () {
    // $("#dispatchClerk").select2();
    selectList();
    initUserList();
    resetCondition();
});


function resetCondition() {
    com.ai.bdx.util.reset('searchForm');
    reloadGrid();
}

function reloadGrid(){
    var data = $("#searchForm").serializeObject();
    data.dispatchClerk = getDispatchClerk();
    $("#user_table").jqGrid("setGridParam", {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

function getDispatchClerk() {
    var dispatchClerkArray = $("#dispatchClerk").select2("val");
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
	var grid_selector = "#user_table";
    var pager_selector = "#user_pager";
    var data = $("#searchForm").serializeObject();

	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT + "/reportMgt/queryShipmenRcdAllList.ajax",
        mtype : "post",
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['日期','星期','日派单总金额','日派单总行数','日派单总个数'],
        colModel: [
            {name: 'createDate', index: 'createDate', sortable: false,fixed:false,width:130,align:'center' },
            {name: 'weekNo', index: 'weekNo', sortable: false,align:'center',resizable:true,fixed:false ,width:80},
            {name: 'amt', index: "amt", sortable: false,align:'right',width:100},
            {name: 'lineNum', index: "lineNum", sortable: false,align:'right',width:100},
            {name: 'num', index: "num", sortable: false,align:'right',width:100}
            ],
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
        },
        autowidth: true
    }).jqGrid("navGrid",pager_selector,{refresh: true});
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

//初始化用户下拉列表
function initUserList() {
    //检查状态下拉列表
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT+"/reportMgt/getSysUserList.ajax",
        success: function (data) {
            $("#dispatchClerk").select2();
            var jsonStatus = eval(data.userList);
            var statusSelect = $("#dispatchClerk");
            for (var i = 0; i < jsonStatus.length; i++) {
                statusSelect.append("<option value='" + jsonStatus[i].userName  + "'>" + jsonStatus[i].name + "</option>");
            }
            $("#dispatchClerk").select2();
        }
    });
}

