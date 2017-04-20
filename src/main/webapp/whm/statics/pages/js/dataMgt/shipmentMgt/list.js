var typeMap;
var payTypeMap;
var dispatchClerkMap;

$(function () {
    selectList();
    initDicts();
    initQueryDicts();
    resetCondition();
});

function initQueryDicts() {
    $.ajax({
        type: "POST",
        async:false,
        data:{"type":"SHIPMENT_RECORD_TYPE","payType":"SHIPMENT_RECORD_PAY_TYPE","dispatchClerk":"SHIPMENT_RECORD_CLERK"},
        datatype: "json",
        url: GLOBAL.WEBROOT + "/common/dictItem/getDictItemCondition.ajax",
        success: function (data) {
            var jsonType = eval(data.type);
            var typeSelect = $("#type");
            typeSelect.empty();
            typeSelect.append("<option value='-1'>请选择类型</option>");
            for (var i = 0; i < jsonType.length; i++) {
                typeSelect.append("<option value='" + jsonType[i].itemNo + "'>" + jsonType[i].itemName + "</option>");
            }
            $('#type').val(-1);
            $('#type').select2();

            var jsonPayType = eval(data.payType);
            var payTypeSelect = $("#payType");
            payTypeSelect.empty();
            payTypeSelect.append("<option value='-1'>请选择付款方式</option>");
            for (var i = 0; i < jsonPayType.length; i++) {
                payTypeSelect.append("<option value='" + jsonPayType[i].itemNo + "'>" + jsonPayType[i].itemName + "</option>");
            }
            $('#payType').val(-1);
            $('#payType').select2();
        }
    });
}

function resetCondition() {
    com.ai.bdx.util.reset('searchForm');
    reloadGrid();
}

function reloadGrid(){
    var data = $("#searchForm").serializeObject();
	$("#shipment").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
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
            {
                name: 'type', index: 'type', sortable: false, align: 'center', resizable: true, fixed: false, width: 80,
                formatter: function (cellvalue, options, rowObject) {
                    if (typeMap.containsKey(cellvalue)) {
                        return typeMap.get(cellvalue);
                    } else {
                        return "";
                    }
                }
            },
            {name: 'prodDetail', index: 'prodDetail', sortable: false,resizable:true,fixed:false,width:80,align:'center'},
            {name: 'amount', index: 'amount',  sortable: false,align:'center',resizable:true,fixed:false,width:150},
            {name: 'lineNum', index: 'lineNum', sortable: false,align:'center',resizable:true,fixed:false ,width:80},
            {name: 'payType', index: 'payType', sortable: false,width:100,
                formatter: function (cellvalue, options, rowObject) {
                    if (payTypeMap.containsKey(cellvalue)) {
                        return payTypeMap.get(cellvalue);
                    } else {
                        return "";
                    }
                }
            },
            {name: 'custInfo', index: 'custInfo',  sortable: false,align:'right' ,width:80 },
            {name: 'comment', index: 'comment',  sortable: false,align:'right',width:80 },
            {name: 'dispatchClerk', index: "dispatchClerk", sortable: false,align:'right',width:100,
                formatter: function (cellvalue, options, rowObject) {
                    if (dispatchClerkMap.containsKey(cellvalue)) {
                        return dispatchClerkMap.get(cellvalue);
                    } else {
                        return "";
                    }
                }
            },
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
        data:{"type":"SHIPMENT_RECORD_TYPE","payType":"SHIPMENT_RECORD_PAY_TYPE","dispatchClerk":"SHIPMENT_RECORD_CLERK"},
        datatype: "json",
        url: GLOBAL.WEBROOT + "/common/dictItem/getDictItemCondition.ajax",
        success: function (data) {
            var type = eval(data.type);
            typeMap = new Map();
            for (var i = 0; i < type.length; i++) {
                typeMap.put(type[i].itemNo,type[i].itemName);
            }

            var payType = eval(data.payType);
            payTypeMap = new Map();
            for (var i = 0; i < payType.length; i++) {
                payTypeMap.put(payType[i].itemNo,payType[i].itemName);
            }

            var dispatchClerk = eval(data.dispatchClerk);
            dispatchClerkMap = new Map();
            for (var i = 0; i < dispatchClerk.length; i++) {
                dispatchClerkMap.put(dispatchClerk[i].itemNo,dispatchClerk[i].itemName);
            }
        }
    });
}
