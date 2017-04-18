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
    reloadGrid();
}

function reloadGrid(){
    var data = $("#searchForm").serializeObject();
	$("#dict_table").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#dict_table";
    var pager_selector = "#dict_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT + "/dictMgt/querySysDictList.ajax",
        mtype : "post",
        datatype: "json",
        height: '100%',
        width : '100%',
        colNames: ['字典名称','字典项名称','字典值','父字典值', '字典值顺序', '状态', '字典项描述','创建时间','操作'],
        colModel: [
            {name: 'dictName', index: 'dictName', sortable: false,fixed:false,width:130,align:'center'},
            {name: 'itemName', index: 'itemName', sortable: false,align:'center',resizable:true,fixed:false ,width:80},
            {name: 'itemNo', index: 'itemNo', sortable: false,resizable:true,fixed:false,width:80,align:'center'},
            {name: 'parentItemNo', index: 'parentItemNo',  sortable: false,align:'center',resizable:true,fixed:false,width:150},
            {name: 'itemOrder', index: 'itemOrder', sortable: false,align:'center',resizable:true,fixed:false ,width:80},
            {name: 'itemState', index: 'itemState', sortable: false,width:100,formatter:function (param1, param2, recode) {
                if('1'==recode.itemState){
                    return '可用';
                }else{
                    return '禁用';
                }
            }},
            {name: 'itemDesc', index: 'itemDesc',  sortable: false,align:'right' ,width:80 },
            {name: 'createDate', index: "createDate", sortable: false,align:'right',width:100},
            {name: 'dictName', index: "dictName",  sortable: false,width:100,align:'center'
                ,formatter: function (param1, param2, recode) {
                var dictName =recode.dictName;
                var itemNo = recode.itemNo;
                return '<a class="blue" href="javascript:void(0)" onclick="addDict(\''+dictName +'\',\''+itemNo+'\')" title="修改"><i class="icon-edit bigger-120"></i></a>&nbsp;' +
                    '<a class="red" href="javascript:void(0)" onclick="dictDel(\''+dictName + '\',\''+ itemNo+'\')" title="删除"><i class="icon-trash bigger-120"></i></a>' ;
            }}
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


function addDict(dictName,itemNo){
    layer.open({
        type: 2,
        title:"字典编辑",
        area: ['700px', '250px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + "/dictMgt/edit.html?dictName="+dictName+"&itemNo="+itemNo,
        closeBtn:0
    });
}

function dictDel(dictName,itemNo){
    layerConfirm('是否删除当前选中记录',function() {
        $.ajax({
            type: "GET",
            async: true,
            url: GLOBAL.WEBROOT + "/dictMgt/dictDel.ajax?dictName="+dictName+"&itemNo="+itemNo,
            dataType: 'json',
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
