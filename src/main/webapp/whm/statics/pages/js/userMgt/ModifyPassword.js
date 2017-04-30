function save(){
    var passwordOld = $("#passwordOld").val();
    var passwordNew = $("#passwordNew").val();
    var passwordNewAgain = $("#passwordNewAgain").val();
    if(!passwordOld){
        info("温馨提示","请输入原密码");
        return;
    }
    if(!passwordNew){
        info("温馨提示","请输入新密码");
        return;
    }
    if(!passwordNewAgain){
        info("温馨提示","请再次输入新密码");
        return;
    }
    if(passwordNew != passwordNewAgain){
        info("温馨提示","两次输入新密码不一致");
        return;
    }
    if(passwordNew==passwordOld){
        info("温馨提示","新设置的密码不能和原密码一样");
        return;
    }

    $.ajax({
        type: "POST",
        cache:false,
        async: false,
        url: GLOBAL.WEBROOT + "/whm/PasswordChange.ajax?passwordOld="+passwordOld+"&passwordNew="+passwordNew,
        dataType: 'json',
        success: function (data) {
            if (data.code == "0") {
                infoCall('温馨提示','密码修改成功，请重新登陆！',function () {
                    window.location=GLOBAL.WEBROOT+"/whm/logout.html";
                });
            }
            else {
                info("温馨提示",data.message);
            }
        }
    });
}

function closeLayer(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

