<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="cache-control" content="no-cache">
    <%@ include file="/common/meta.jsp" %>
    <title>密码修改</title>

    <link rel="stylesheet" href="${ctx}/whm/statics/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <link rel="stylesheet" href="${ctx}/whm/statics/common/plugins/ztree/css/selectTree.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/whm/statics/common/css/execute.css" type="text/css">

    <script type="text/javascript" src="${ctx}/whm/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    <link type="text/css" rel="stylesheet"
          href="${ctx}/whm/statics/common/js/jquery-easyui/themes/default/easyui.css"/>

    <script type="text/javascript" src="${ctx}/whm/statics/common/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/whm/statics/common/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/whm/statics/common/plugins/ztree/js/jquery.ztree.exedit-3.5.js"></script>

    <script type="text/javascript" src="${ctx}/whm/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/whm/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/whm/statics/pages/js/userMgt/ModifyPassword.js?_dc=${staticVersion}"></script>
    <script type="text/javascript"
            src="${ctx}/whm/statics/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <style type="text/css">

    </style>
</head>
<body width="100%" height="100%">
<div class="wrap100 quickrecharge">
    <div class="q_r_list" style="width:98%; ">
        <!--交易记录查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">

                    <tr>
                        <th width="13%" class="r">请输入原密码：</th>
                        <td width="13%">
                            <input name="passwordOld" type="password" id="passwordOld" class="login_input" size="35"/>
                        </td>
                    </tr>
                    <tr>
                        <th width="13%" class="r">请输入新密码：</th>
                        <td width="13%">
                            <input name="passwordNew" type="password" id="passwordNew" class="login_input" size="35"/>
                        </td>
                    </tr>
                    <tr>
                        <th width="13%" class="r">请再次输入新密码：</th>
                        <td width="13%">
                            <input name="passwordNewAgain" type="password" id="passwordNewAgain" class="login_input" size="35"/>
                        </td>
                    </tr>
                </table>
            </form>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="r n_b_b">&nbsp;</th>
                    <td class="n_b_b">&nbsp;</td>
                    <th class="r n_b_b">&nbsp;</th>
                    <th colspan="3" class="r n_b_b">
                        <button class="btn btn-primary" type="button" id="saveBtn" onclick="save()">
                            <i class="ace-icon fa fa-check bigger-110 icon-save"></i>
                            保 存
                        </button>
                        <button class="btn btn-gray" type="button" id="closeBtn" onclick="closeLayer()">
                            <i class="ace-icon fa fa-check bigger-110 icon-remove"></i>
                            关 闭
                        </button>
                    </th>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>