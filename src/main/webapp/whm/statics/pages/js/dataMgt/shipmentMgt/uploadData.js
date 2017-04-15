var operatorCode = top.user.operatorCode;
var userStation =top.userStation;
var orgCode = parent.org.orgCode;
var orgName = parent.org.orgName;

$(function () {
    $("select").select2();
});

function save() {
    if(!$("#cardDataFile").val()){
        info('温馨提示','请选择文件后重试!');
        return false;
    }
    var dataType = $("#dataType").val();
    $.ajaxFileUpload({
        url :GLOBAL.WEBROOT +  '/makeCardDataMgt/uploadCardData.ajax?&dataType='+dataType, //用于文件上传的服务器端请求地址
        secureuri : false, //一般设置为false
        fileElementId : 'cardDataFile', //文件上传空间的id属性  <input type="file" id="file" name="file" />
        type : 'post',
        dataType : 'json', //返回值类型 一般设置为json
        contentType : "text/html",
        success : function(data, status) //服务器成功响应处理函数
        {
          if(data.ERRCODE!=0){
              info('温馨提示',data.ERRINFO);
          }else{
              infoClose('温馨提示', "保存成功",function(){
                  parent.afterProcessUpload(window.name);
              });
          }
        },
        error : function(data, status, e)//服务器响应失败处理函数
        {
            info('温馨提示','系统异常');
        }
    });
    return false;
}

function closeLayer(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}