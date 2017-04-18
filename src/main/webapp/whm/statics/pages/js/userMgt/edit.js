

$(function () {
    var roleCode = $("#_roleCode").val();
    var sex = $("#_sex").val();
    var status = $("#_status").val();
    var stationCode = $("#_stationCode").val();

    $("#roleCode").val(roleCode);
    $("#sex").val(sex);
    $("#status").val(status);
    $("#stationCode").val(stationCode);


});

function saveSuccess(){
    parent.reloadGrid();
    closeLayer();
}

//删除
function findUserById(id) {
        var data = "id=" + id;
        $.ajax({
            type: "GET",
            async: true,
            url: GLOBAL.WEBROOT + "/userMgt/getUserById.ajax",
            dataType: 'json',
            data: data,
            success: function (data) {
                var user = data.bean;
                $("#userName").val(user.userName);
                $("#name").val(user.name);
                $("#sex").val(user.sex);
                $("#stationCode").val(user.stationCode);
                $("#roleCode").val(user.roleCode);
                $("#comment").val(user.comment);
                $("#password").val(user.password);
            }
        });
}


function save() {
    if($("#userName").val()==""){
        $("#userName").focus();
        info('温馨提示',"用户名不能为空！");
        return;
    }

    if($("#password").val()==""){
        $("#password").focus();
        info('温馨提示',"用户密码不能为空！");
        return;
    }

    var isUpdatePwd = '0';
    if($("#_password").val()&&$("#_password").val()==$("#password").val()){
        isUpdatePwd = '1';//未修改
    }

    var data = $.toJSON($("#editForm").serializeObject());
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/userMgt/saveUser.ajax?isUpdatePwd="+isUpdatePwd,
        dataType: 'json',
        contentType: 'application/json',
        data: data,
        success: function (data) {
            if (data.ERRCODE == "0") {
                infoCall('温馨提示',"保存成功！",saveSuccess);
            }
            else {
                info('温馨提示',data.ERRINFO);
            }
        }
    });
}

function closeLayer(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}