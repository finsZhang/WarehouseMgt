<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script src="${ctx}/emm/statics/common/js/jquery-1.11.2.js"></script>
    <link href="${ctx}/emm/statics/pages/css/index/css/login.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/emm/statics/pages/css/index/css/public.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/emm/statics/pages/css/index/css/icon.css" rel="stylesheet" type="text/css">
    <script language="javascript" src="${ctx}/emm/statics/pages/login.js?_dc=${staticVersion}"></script>
    <title>加密机中间件系统</title>
    <script language="javascript">
        //        $.metadata.setType("attr", "validate");
        var GLOBAL = {
            WEBROOT: "${ctx}"
        };
    </script>
    <style>
        body, html {
            background: #eefdf9;
        }
    </style>
</head>

<body>

<!--top beign-->
<div class="wrap100 loginTopBg">
    <div class="wrap"><img src="${ctx}/emm/statics/pages/css/index/images/food_logo.png"></div>
</div>
<!--top end-->

<!--loginContent begin-->
<div class="wrap loginContent">
    <div class="fl leftAdver"><img src="${ctx}/emm/statics/pages/css/index/images/leftAdver1.jpg"/></div>
    <!--login begin-->
    <input type="hidden" name="errorCode" id="errorCode" value="${errorCode}"/>
    <input type="hidden" name="errorMessage" id="errorMessage" value="${errorMessage}"/>

    <form id="loginForm" method="post" action="${ctx}/emm/index.html">

        <div class="fl loginList" id="normal">
            <h5>登 录</h5>
            <ul>
                <li><i class="login_icon1"></i><input name="accountName" type="text" id="normalRegistName"
                                                      placeholder="请输入用户名" value="${registName}" class="login_input"
                                                      size="35"/></li>
                <li><i class="login_icon2"></i><input name="passwd" type="password" id="normalPass" placeholder="请输入密码"
                                                      class="login_input" size="35"/></li>
                <li>
                    <input name="imageCode" type="text" placeholder="验证码"
                           style="width:150px;font-size:16px;padding-left: 10px;"/>
                    <img id="imageCheckCode" src="${ctx}/emm/common/checkCode/code?page=login_user"/>
                </li>
            </ul>
            <ul class="prompt" id="alt_normal"> </ul>
            <div class="clear"></div>
            <div class="btn">
                <button type="button" id="loginSubmit" name="normal"><i class="login_icon"></i>登 录</button>
                <button type="reset" onclick="cleanAll()"><i class="reset_icon"></i>重 置</button>
            </div>
        </div>
    </form>
    <!--login end-->

    <div class="cl"></div>
    <!--清除漂浮-->
</div>
<!--loginContent end-->


<!--copyright begin-->
<div class="wrap100 copyright">©2015 asiainfo 亚信. All rights reserved.</div>
<!--copyright end-->

</body>
</html>
