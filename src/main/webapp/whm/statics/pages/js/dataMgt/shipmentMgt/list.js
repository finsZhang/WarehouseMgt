var masterTypeMap;
var daughterTypeMap;
var statusMap;
var sexMap;

$(function () {
    selectList();
    initDicts();
    resetCondition();
});

function resetCondition() {
    com.ai.bdx.util.reset('searchForm');
    if (userStation == 'EMM_ADMIN') {
        com.ai.bdx.util.ztreeComp("orgNames", true, top.optionForMulti, callFuncForOrgNames);
    } else {
        $('#orgCodes').val(orgCode);
        $('#orgNames').val(orgName);
    }
    reloadGrid();
}
function callFuncForOrgNames(zTree) {
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
function reloadGrid(){
    var data = $("#searchForm").serializeObject();
	$("#makeCard").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#shipment";
    var pager_selector = "#shipment_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT + "/shipmentMgt/queryShipmentList.ajax",
        mtype : "post",
        datatype: "json",
        height: '100%',
        width : '100%',
        colNames: ['当日编号','类型','商品明细','金额', '行数', '付款方式', '客户信息','备注','派单员','后期更改','创建时间','创建星期','创建人姓名'],
        colModel: [
            {name: 'dayNo', index: 'dayNo', sortable: false,fixed:false,width:130,align:'center'},
            {name: 'type', index: 'type', sortable: false,align:'center',resizable:true,fixed:false ,width:80},
            {name: 'prodDetail', index: 'prodDetail', sortable: false,resizable:true,fixed:false,width:80,align:'center'},
            {name: 'amount', index: 'amount',  sortable: false,align:'center',resizable:true,fixed:false,width:150},
            {name: 'lineNum', index: 'lineNum', sortable: false,align:'center',resizable:true,fixed:false ,width:80},
            {name: 'payType', index: 'payType', sortable: false,width:100},
            {name: 'custInfo', index: 'custInfo',  sortable: false,align:'right' ,width:80 },
            {name: 'comment', index: 'comment',  sortable: false,align:'right',width:80 },
            {name: 'dispatchClerk', index: "dispatchClerk", sortable: false,align:'right',width:100},
            {name: 'modifyConent', index: "modifyConent", sortable: false,align:'right',width:80},
            {name: 'createDate', index: "createDate", sortable: false,align:'right',width:100},
            {name: 'weekNo', index: "weekNo", sortable: false,align:'right',width:100},
            {name: 'creatorName', index: "creatorName",  sortable: false,width:100,align:'center'}
            ],
        viewrecords: false,
        rowNum:10,
        rowList:[10,15,20,30],
        multiselect: false,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function () {
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

function uploadFile(){
    layer.open({
        type: 2,
        title:"上传文件",
        area: ['700px', '600px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + "/makeCardDataMgt/page/uploadData.html",
        closeBtn:0
    });
}


function dwlZipFile(fileName,oldName){
    $("#oldName").val(oldName);
    $("#fileName").val(fileName);
    $("#dwlForm").attr("action" , GLOBAL.WEBROOT + "/common/downloadFile.ajax");
    $("#dwlForm").submit();
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
