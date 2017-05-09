<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="cache-control" content="no-cache">
    <%@ include file="/common/meta.jsp" %>
    <title>出货列表管理</title>

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
    <script type="text/javascript" src="${ctx}/whm/statics/common/plugins/date/WdatePicker.js"></script>

    <script type="text/javascript" src="${ctx}/whm/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/whm/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/whm/statics/pages/js/dataMgt/shipmentMgt/list.js?_dc=${staticVersion}"></script>
    <script type="text/javascript"
            src="${ctx}/whm/statics/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
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
                <a href="${ctx}/shipmentMgt/page/list.html">出货记录管理</a>
            </li>
            <li class="active">出货记录查询</li>
        </ul>
        <!-- .breadcrumb -->
    </div>
    <div class="q_r_list" style="width:98%; ">
        <!--交易记录查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <th width="13%" class="r">类型：</th>
                        <td width="13%">
                            <select name="type" id="type">
                                <option value="-1">请选择类型</option>
                            </select>
                        </td>
                        <th width="13%" class="r">送货人：</th>
                        <td width="13%">
                            <select name="dispatchClerk" id="dispatchClerk">
                                <option value="-1">请选择送货人</option>
                            </select>
                        </td>
                        <th width="13%" class="r">付款方式：</th>
                        <td width="13%">
                            <select name="payType" id="payType">
                                <option value="-1">请选择付款方式</option>
                            </select>
                        </td>
                    </tr>
                   <tr>
                       <th width="13%" class="r">创建人：</th>
                       <td width="13%">
                            <input id="creatorName" style="width: 150px;" name="creatorName" class="inputW160"/>
                       </td>
                       <th width="13%" class="r">商品明细：</th>
                       <td width="13%">
                           <input id="prodDetail" style="width: 150px;" name="prodDetail" class="inputW160"/>
                       </td>
                        <th class="r" width="12%">查询创建时间段：</th>
                        <td width="50%">
                            <input id="startOperateDate" name="startOperateDate" class="inputW180"
                                   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            -
                            <input id="endOperateDate" name="endOperateDate" class="inputW180"
                                   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
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
                        <a href="javascript:void(0)" onclick="openEdit()" id="fbox_grid-table_search"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-credit-card"></span>新增</a>
                        <a href="javascript:void(0)" onclick="reloadGrid();"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                class="icon-search"></span>查 询</a>
                        <a href="javascript:void(0)" onclick="resetCondition()"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>重 置</a>
                    </th>
                </tr>
            </table>
        </div>
        <!--交易记录查询条件 end-->
        <div>
            <table id="shipment" width="100%" border="0" cellspacing="0" cellpadding="0">

            </table>
            <div id="shipment_pager"></div>
        </div>
    </div>
</div>
</body>
</html>