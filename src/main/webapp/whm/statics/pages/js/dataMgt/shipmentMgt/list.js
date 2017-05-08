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


            var jsonDispatchClerk = eval(data.dispatchClerk);
            var dispatchClerkSelect = $("#dispatchClerk");
            dispatchClerkSelect.empty();
            dispatchClerkSelect.append("<option value='-1'>请选择送货人</option>");
            for (var i = 0; i < jsonDispatchClerk.length; i++) {
                dispatchClerkSelect.append("<option value='" + jsonDispatchClerk[i].itemNo + "'>" + jsonDispatchClerk[i].itemName + "</option>");
            }
            $('#dispatchClerk').val(-1);
            $('#dispatchClerk').select2();
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
        colNames: ['时间','星期','当日编号','类型','商品明细','金额', '行数', '付款方式', '客户信息','备注','送货员','后期更改','创建人姓名', '操作'],
        colModel: [
            {name: 'createDate', index: "createDate", sortable: false,align:'center',width:120},
            {name: 'weekNo', index: "weekNo", sortable: false,align:'center',width:80},
            {name: 'dayNo', index: 'dayNo', sortable: false,fixed:false,width:80,align:'center'},
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
            {name: 'prodDetail', index: 'prodDetail', sortable: false,resizable:true,fixed:false,width:100,align:'center'},
            {name: 'amount', index: 'amount',  sortable: false,align:'center',resizable:true,fixed:false,width:80},
            {name: 'lineNum', index: 'lineNum', sortable: false,align:'center',resizable:true,fixed:false ,width:80},
            {name: 'payType', index: 'payType', sortable: false,width:100,align:'center',
                formatter: function (cellvalue, options, rowObject) {
                    if (payTypeMap.containsKey(cellvalue)) {
                        return payTypeMap.get(cellvalue);
                    } else {
                        return "";
                    }
                }
            },
            {name: 'custInfo', index: 'custInfo',  sortable: false,align:'center' ,width:80 },
            {name: 'comment', index: 'comment',  sortable: false,align:'center',width:80 },
            {name: 'dispatchClerk', index: "dispatchClerk", sortable: false,align:'center',width:80,
                formatter: function (cellvalue, options, rowObject) {
                    if (dispatchClerkMap.containsKey(cellvalue)) {
                        return dispatchClerkMap.get(cellvalue);
                    } else {
                        return "";
                    }
                }
            },
            {name: 'modifyConent', index: "modifyConent", sortable: false,align:'center',width:100},
            {name: 'creatorName', index: "creatorName",  sortable: false,width:100,align:'center'},
            {
                name: 'id', index: "id", sortable: false,width:100,align:'center'
                , formatter: function (cellvalue, options, rowObject) {
                return '<a class="blue" href="javascript:void(0)" onclick="openEdit(\'' + cellvalue + '\')" title="编辑"><i class="icon-pencil bigger-130"></i></a>&nbsp;&nbsp;' +
                    '<a class="red" href="javascript:void(0)" onclick="deleteRecord(\'' + cellvalue + '\')" title="删除"><i class="icon-trash bigger-130"></i></a>';

            }
            }
            ],
        viewrecords: false,
        rowNum:10,
        rowList:[10,15,20,30],
        multiselect: false,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function () {
            records = data.records;
            com.ai.bdx.util.updatePagerIcons(this);
        },
        autowidth: true
    }).jqGrid("navGrid",pager_selector,{refresh: true});
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





function deleteRecord(id) {
    layerConfirm('是否删除当前选中记录',function() {
        var data = "id=" + id;
        $.ajax({
            type: "GET",
            async: true,
            cache:false,
            url: GLOBAL.WEBROOT + "/shipmentMgt/deleteRecord",
            dataType: 'json',
            data: data,
            success: function (data) {
                if (data.ERRCODE == "0") {
                    infoSuccess('温馨提示', '删除成功');
                    reloadGrid();
                }
                else {
                    info('温馨提示', data.ERRINFO);
                }
            }
        });
    });
}

function openEdit(id) {
    var url;
    if(id){
        url = GLOBAL.WEBROOT + "/shipmentMgt/page/edit.html?id="+id;
    }else{
        url = GLOBAL.WEBROOT + "/shipmentMgt/page/edit.html";
    }
    layer.open({
        type: 2,
        title:"现金上缴",
        area: ['620px', '500px'],
        fix: false, //不固定
        maxmin: true,
        content: url,
        closeBtn: 1
    });
}
var records;
function  exportAll(){
    if(!records){
        info("温馨提示","导出条数为空！");
        return;
    }
    var  startDate=$("#startDate").val();
    var  orgCodes=$("#orgCodes").val();
    window.location.href= GLOBAL.WEBROOT+"/report/exportAll.html?startDate="+startDate+"&orgCodes="+orgCodes;
}