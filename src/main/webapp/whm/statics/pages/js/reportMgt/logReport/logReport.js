var operatorCode = top.user.operatorCode;
var userStation =top.userStation;
var orgCode = top.org.orgCode;
var orgName = top.org.orgName;

$(function () {
    selectList();
    resetCondition();
    initDict();
    initDicts();

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
        url: GLOBAL.WEBROOT + "/reportMgt/queryLogList.ajax",
        mtype : "post",
        datatype: "json",
        cache:false,
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['机构代码','机构名称','时间段','业务类型', '操作数量'],
        colModel: [
            {name: 'orgCode', index: 'orgCode', sortable: false,fixed:false,width:130,align:'center'},
            {name: 'orgName', index: 'orgName', sortable: false,fixed:false,width:130,align:'center'},
            {name: 'timePeriod', index: 'timePeriod', sortable: false,fixed:false,width:150,align:'center'},
            {name: 'businessType', index: 'businessType', sortable: false,fixed:false,width:130,align:'center',
                formatter: function (cellvalue, options, rowObject) {
                    var businessType = rowObject.businessType;
                    //01:制卡 02:发卡|03:充值|04:退费
                    if(businessType=='01'){
                        return '制卡';
                    } else if(businessType=='02'){
                        return '发卡';
                    }else if(businessType=='03'){
                        return '充值';
                    }else if(businessType=='04'){
                        return '退费';
                    }else if(businessType=='05'){
                        return "擦卡";
                    }
                }
            },
            {name: 'operateCount', index: 'operateCount', sortable: false,fixed:false,width:130,align:'center'},
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
        data:{"businessType":"BUS_TYPE"},
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