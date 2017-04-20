var roleCodeMap;
var stationCodeMap;
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
    $("#user_table").jqGrid("setGridParam", {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#user_table";
    var pager_selector = "#user_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT + "/userMgt/querySysUserList.ajax",
        mtype : "post",
        datatype: "json",
        height: '100%',
        width : '100%',
        colNames: ['用户ID','用户姓名','用户性别','用户名', '状态', '岗位编码','角色编码','创建时间','备注','操作'],
        colModel: [
            {name: 'id', index: 'id', sortable: false,fixed:false,width:130,align:'center'},
            {name: 'name', index: 'name', sortable: false,align:'center',resizable:true,fixed:false ,width:80},
            {name: 'sex', index: 'sex', sortable: false,resizable:true,fixed:false,width:80,align:'center',
                formatter: function (cellvalue, options, rowObject) {
                    if (sexMap.containsKey(cellvalue)) {
                        return sexMap.get(cellvalue);
                    }else{
                        return "";
                    }
                }
            },
            {name: 'userName', index: 'userName',  sortable: false,align:'center',resizable:true,fixed:false,width:150},
            {name: 'status', index: 'status', sortable: false,width:100,
                formatter: function (cellvalue, options, rowObject) {
                    if (statusMap.containsKey(cellvalue)) {
                        return statusMap.get(cellvalue);
                    }else{
                        return "";
                    }
                }
            },
            {name: 'stationCode', index: 'stationCode',  sortable: false,align:'right' ,width:80,
                formatter: function (cellvalue, options, rowObject) {
                    if (stationCodeMap.containsKey(cellvalue)) {
                        return stationCodeMap.get(cellvalue);
                    }else{
                        return "";
                    }
                }
            },
            {name: 'roleCode', index: 'roleCode',  sortable: false,align:'right',width:80,
                formatter: function (cellvalue, options, rowObject) {
                    if (roleCodeMap.containsKey(cellvalue)) {
                        return roleCodeMap.get(cellvalue);
                    }else{
                        return "";
                    }
                }
             },
            {name: 'createDate', index: "createDate", sortable: false,align:'right',width:100},
            {name: 'comment', index: "comment", sortable: false,align:'right',width:80},
            {name: 'id', index: "id",  sortable: false,width:100,align:'center'
                ,formatter: function (param1, param2, recode) {
                    var id =recode.id;
                    return '<a class="blue" href="javascript:void(0)" onclick="addUser(\'' + id + '\')" title="修改"><i class="icon-edit bigger-120"></i></a>&nbsp;' +
                        '<a class="red" href="javascript:void(0)" onclick="del(\'' + id + '\')" title="删除"><i class="icon-trash bigger-120"></i></a>' ;
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

function addUser(id){
    layer.open({
        type: 2,
        title:"用户编辑",
        area: ['700px', '250px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + "/userMgt/edit.html?id="+id,
        closeBtn:0
    });
}


//删除
function del(id) {
    layerConfirm('是否删除当前选中记录',function() {
        var data = "id=" + id;
        $.ajax({
            type: "GET",
            async: true,
            url: GLOBAL.WEBROOT + "/userMgt/userDel.ajax",
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



function initDicts() {

    $.ajax({
        type: "POST",
        async:false,
        data:{"stationCode":"SYS_USER_STATION_CODE","roleCode":"SYS_USER_ROLE_CODE","status":"SYS_USER_STATUS","sex":"SYS_USER_SEX"},
        datatype: "json",
        url: GLOBAL.WEBROOT + "/common/dictItem/getDictItemCondition.ajax",
        success: function (data) {
            var stationCode = eval(data.stationCode);
            stationCodeMap = new Map();
            for (var i = 0; i < stationCode.length; i++) {
                stationCodeMap.put(stationCode[i].itemNo,stationCode[i].itemName);
            }

            var roleCode = eval(data.roleCode);
            roleCodeMap = new Map();
            for (var i = 0; i < roleCode.length; i++) {
                roleCodeMap.put(roleCode[i].itemNo,roleCode[i].itemName);
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
