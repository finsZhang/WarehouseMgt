var businessTypeMap;
var daughterTypeMap;
var operateStatusMap;

var userStation =top.userStation;
var orgCode = top.org.orgCode;
var orgName = top.org.orgName;

$(function () {
    selectList();
    initDicts();
    initDict();
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
	var grid_selector = "#makeCard";
    var pager_selector = "#makeCard_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT + "/logMgr/queryLogList.ajax",
        mtype : "post",
        datatype: "json",
        cache:false,
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['ID','交易流水号','芯片序列号','发卡序列号', '卡号', '身份证号', '持卡人姓名','操作时间','PSAM卡号','组织机构名称','业务类型','卡类型','操作状态','回传操作时间'],
        colModel: [
            {name: 'id', index: 'id', sortable: false,fixed:false,width:130,align:'center',hidden:true},
            {name: 'tradeSerial', index: 'tradeSerial', sortable: false,fixed:false,width:130,align:'center'},
            {name: 'chipSerial', index: 'chipSerial', sortable: false,fixed:false,width:130,align:'center'},
            {name: 'cardSerial', index: 'cardSerial', sortable: false,fixed:false,width:130,align:'center'},
            {name: 'cardNo', index: 'cardNo', sortable: false,fixed:false,width:130,align:'center'},
            {name: 'idNo', index: 'idNo', sortable: false,fixed:false,width:130,align:'center'},
            {name: 'cardName', index: 'cardName', sortable: false,fixed:false,width:130,align:'center'},
            {name: 'operateDate', index: 'operateDate', sortable: false,fixed:false,width:130,align:'center'},
            {name: 'psamNo', index: 'psamNo', sortable: false,fixed:false,width:130,align:'center'},
            {name: 'orgName', index: 'orgName', sortable: false,fixed:false,width:130,align:'center'},
            {name: 'businessType', index: 'businessType', sortable: false,fixed:false,width:130,align:'center',
                formatter: function (cellvalue, options, rowObject) {
                    if (businessTypeMap.containsKey(cellvalue)) {
                        return businessTypeMap.get(cellvalue);
                    }else{
                        return "";
                    }
                }
            },
            {name: 'daughterType', index: 'daughterType', sortable: false,fixed:false,width:130,align:'center',
                formatter: function (cellvalue, options, rowObject) {
                    if (daughterTypeMap.containsKey(cellvalue)) {
                        return daughterTypeMap.get(cellvalue);
                    }else{
                        return "";
                    }
                }
            },
            {name: 'operateStatus', index: 'operateStatus', sortable: false,fixed:false,width:130,align:'center',
                formatter: function (cellvalue, options, rowObject) {
                    if (operateStatusMap.containsKey(cellvalue)) {
                        return operateStatusMap.get(cellvalue);
                    }else{
                        return "";
                    }
                }
            },
            {name: 'updateDate', index: 'updateDate', sortable: false,fixed:false,width:130,align:'center'},
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
function initDict() {
    $.ajax({
        type: "POST",
        async:false,
        data:{"businessType":"BUS_TYPE","operateStatus":"OPERATE_STATUS"},
        datatype: "json",
        url: GLOBAL.WEBROOT + "/common/dictItem/getDictItemCondition.ajax",
        success: function (data) {
            var jsonCardType = eval(data.businessType);
            var cardTypeSelect = $("#businessType");
            cardTypeSelect.empty();
            cardTypeSelect.append("<option value='-1'>请选择业务类型</option>");
            for (var i = 0; i < jsonCardType.length; i++) {
                cardTypeSelect.append("<option value='" + jsonCardType[i].itemNo + "'>" + jsonCardType[i].itemName + "</option>");
            }
            $('#businessType').val(-1);
            $('#businessType').select2();

            var jsonCardType = eval(data.operateStatus);
            var cardTypeSelect = $("#operateStatus");
            cardTypeSelect.empty();
            cardTypeSelect.append("<option value='-1'>请选择操作状态</option>");
            for (var i = 0; i < jsonCardType.length; i++) {
                cardTypeSelect.append("<option value='" + jsonCardType[i].itemNo + "'>" + jsonCardType[i].itemName + "</option>");
            }
            $('#operateStatus').val(-1);
            $('#operateStatus').select2();
        }
    });
}
function initDicts() {
    $.ajax({
        type: "POST",
        async:false,
        data:{"businessType":"BUS_TYPE","operateStatus":"OPERATE_STATUS","daughterType":"CARD_SUB_TYPE"},
        datatype: "json",
        url: GLOBAL.WEBROOT + "/common/dictItem/getDictItemCondition.ajax",
        success: function (data) {
            var businessType = eval(data.businessType);
            businessTypeMap = new Map();
            for (var i = 0; i < businessType.length; i++) {
                businessTypeMap.put(businessType[i].itemNo,businessType[i].itemName);
            }

            var daughterType = eval(data.daughterType);
            daughterTypeMap = new Map();
            for (var i = 0; i < daughterType.length; i++) {
                daughterTypeMap.put(daughterType[i].itemNo,daughterType[i].itemName);
            }

            var operateStatus = eval(data.operateStatus);
            operateStatusMap = new Map();
            for (var i = 0; i < operateStatus.length; i++) {
                operateStatusMap.put(operateStatus[i].itemNo,operateStatus[i].itemName);
            }
        }
    });
}
