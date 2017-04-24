var masterTypeMap;
var daughterTypeMap;
var statusMap;
var sexMap;

$(function () {

    //检查状态下拉列表
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT+"/reportMgt/getSysUserList.ajax",
        success: function (data) {
            $("#dispatchClerk").select2();
            var jsonStatus = eval(data.SYS_USER);
            var statusSelect = $("#dispatchClerk");
            // statusSelect.empty();
            // statusSelect.append("<option value='-1'>请选择状态</option>");
            for (var i = 0; i < jsonStatus.length; i++) {
                statusSelect.append("<option value='" + jsonStatus[i].key  + "'>" + jsonStatus[i].value + "</option>");
            }
             $("#dispatchClerk").select2();
        }
    });


    $("#dispatchClerk").select2();
    selectList();
    initDicts();
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
        colNames: ['日期','星期','派单总数','派单总金额', '派单人'],
        colModel: [
            {name: 'createDate', index: 'createDate', sortable: false,fixed:false,width:130,align:'center'},
            {name: 'weekNo', index: 'weekNo', sortable: false,align:'center',resizable:true,fixed:false ,width:80},
            {name: 'num', index: "num", sortable: false,align:'right',width:100},
            {name: 'amt', index: "amt", sortable: false,align:'right',width:100},
            {name: 'dispatchClerk', index: "dispatchClerk", sortable: false,align:'right',width:80}
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

function afterProcessUpload(windowName){
    closeSubLayer(windowName);
    reloadGrid();
}

function closeSubLayer(name){
    var index = layer.getFrameIndex(name);
    layer.close(index);
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
    var  startDate=$("#startDate").val();
    var  dispatchClerk=getDispatchClerk();
    window.location.href= GLOBAL.WEBROOT+"/reportMgt/exportRecordTotalRcds.html?startDate="+startDate+"&dispatchClerk="+dispatchClerk;
}

function initDicts() {

    $.ajax({
        type: "POST",
        async:false,
        data:{"masterType":"CARD_MAIN_TYPE","daughterType":"CARD_SUB_TYPE","status":"MAKE_CARD_STATUS","sex":"CARD_SEX"},
        datatype: "json",
        url: GLOBAL.WEBROOT + "/common/dictItem/getDictItemCondition.ajax",
        success: function (data) {
            var masterType = eval(data.masterType);
            masterTypeMap = new Map();
            for (var i = 0; i < masterType.length; i++) {
                masterTypeMap.put(masterType[i].itemNo,masterType[i].itemName);
            }

            var daughterType = eval(data.daughterType);
            daughterTypeMap = new Map();
            for (var i = 0; i < daughterType.length; i++) {
                daughterTypeMap.put(daughterType[i].itemNo,daughterType[i].itemName);
            }

            var status = eval(data.status);
            statusMap = new Map();
            for (var i = 0; i < status.length; i++) {
                statusMap.put(status[i].itemNo,status[i].itemName);
            }

            var sex = eval(data.sex);
            sexMap = new Map();
            for (var i = 0; i < sex.length; i++) {
                sexMap.put(sex[i].itemNo,sex[i].itemName);
            }
        }
    });
}