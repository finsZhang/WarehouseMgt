<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="cache-control" content="no-cache">
    <%@ include file="/common/taglibs.jsp" %>
    <%@ include file="/common/meta.jsp" %>
    <title>出货列表管理</title>

    <%--<link href="${ctx}/whm/statics/pages/css/index/css/manage.css" rel="stylesheet" type="text/css">--%>
    <link href="${ctx}/whm/statics/common/css/css.css" rel="stylesheet" type="text/css"/>


    <link type="text/css" rel="stylesheet" href="${ctx}/whm/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="${ctx}/whm/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>

    <link type="text/css" rel="stylesheet" href="${ctx}/whm/statics/common/js/date/skin/WdatePicker.css"/>
    <script type="text/javascript" src="${ctx}/whm/statics/common/js/date/WdatePicker.js"></script>

    <script type="text/javascript" src="${ctx}/whm/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/whm/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/whm/statics/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>


    <script type="text/javascript" src="${ctx}/whm/statics/pages/js/reportMgt/list.js?_dc=${staticVersion}"></script>
    <style type="text/css">

    </style>
</head>
<body width="100%" height="100%">
<div class="wrap100 quickrecharge">
    <div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
            try {
                ace.settings.check('breadcrumbs', 'fixed')
            } catch (e) {
            }
        </script>

        <ul class="breadcrumb" style="margin-top: 8px;">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="${ctx}/userMgt/list.html">报表管理</a>
            </li>
            <li class="active">派单汇总记录表</li>
        </ul>
        <!-- .breadcrumb -->
    </div>
    <div class="q_r_list" style="width:98%; ">
        <!--交易记录查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <th width="10%" class="r">用户：</th>
                        <td width="13%">
                            <%--<input name="dispatchClerk" id="dispatchClerk" class="inputW160"/>--%>
                                <select name="dispatchClerk" id="dispatchClerk" multiple="multiple" class="js-example-basic-multiple">
                                    <option value="xiaowang">xiaowang</option>
                                </select>
                        </td>
                        <th width="10%" class="r">时间段：</th>
                        <td>
                        <input id="startDate" name="startDate" class="inputW180"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                        -
                        <input id="endDate" name="endDate" class="inputW180"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
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
                        <a href="javascript:void(0)" onclick="reloadGrid();"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                class="icon-search"></span>查 询</a>
                        <a href="javascript:void(0)" onclick="resetCondition()"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>重 置</a>
                        <a href="javascript:void(0)" onclick="addUser('')"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>新 曾</a>
                    </th>
                </tr>
            </table>
        </div>
        <!--交易记录查询条件 end-->
        <div>
            <table id="user_table" width="100%" border="0" cellspacing="0" cellpadding="0">

            </table>
            <div id="user_pager"></div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(".select_gallery-multiple").select2();
</script>
</body>
</html>
