$(function () {
    //PasswordSubmit();
});

function PasswordSubmit(){
    var passwordOld = $("#passwordOld").val();
    var passwordOldAgain = $("#passwordOldAgain").val();
    var passwordNew = $("#passwordNew").val();
    if(passwordOld == null){
        info("温馨提示","请输入原密码");
        return;
    }
    if(passwordOldAgain == null){
        info("温馨提示","请再次输入原密码");
        return;
    }
    if(passwordOld != passwordOldAgain){
        info("温馨提示","两次输入原密码不一致");
        return;
    }
    if(passwordNew == null){
        info("温馨提示","请输入新密码");
        return;
    }

    $.ajax({
        type: "POST",
        cache:false,
        async: false,
        url: GLOBAL.WEBROOT + "/whm/PasswordChange.ajax?passwordOld="+passwordOld+"&passwordOldAgain="+passwordOldAgain+"&passwordNew="+passwordNew,
        dataType: 'json',
        success: function (data) {
            if (data.code == "0") {
                layerConfirm('密码修改成功！',function () {
                    window.location="/whm/whm/login.html";
                });
            }
            else {
                info("温馨提示",data.message);
            }
        }
    });
}


