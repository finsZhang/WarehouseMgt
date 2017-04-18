<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="cache-control" content="no-cache">
    <%@ include file="/common/meta.jsp" %>
    <title>字典编辑</title>
    <script type="text/javascript" src="${ctx}/whm/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/whm/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="${ctx}/whm/statics/ace/plugins/select2-3.4.1/select2.js"></script>
    <script type="text/javascript" src="${ctx}/whm/statics/pages/js/dictMgt/edit.js?_dc=${staticVersion}"></script>

</head>
<body>
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12">
                <form class="form-horizontal" role="form" id="editForm">
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 字典名称： </label>
                        <div class="col-xs-4">
                            <input id="dictName" name="dictName" class="inputW150" value="${bean.dictName}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 字典项名称： </label>
                        <div class="col-xs-4">
                            <input id="itemName" name="itemName" class="inputW150" value="${bean.itemName}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 字典值： </label>
                        <div class="col-xs-4">
                            <input id="itemNo" name="itemNo" class="inputW150" value="${bean.itemNo}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 字典项描述： </label>
                        <div class="col-xs-4">
                            <input id="itemDesc" name="itemDesc" class="inputW150" value="${bean.itemDesc}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 字典项状态： </label>
                        <div class="col-xs-4">
                            <input id="_itemState" type="hidden" class="inputW150" value="${bean.itemState}"/>
                            <select name="itemState" id="itemState" class="inputW150">
                                <option value="1">可用</option>
                                <option value="0">禁用</option>
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 父字典值： </label>
                        <div class="col-xs-4">
                            <input id="parentItemNo" name="parentItemNo" class="inputW150" value="${bean.parentItemNo}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 字典值顺序： </label>
                        <div class="col-xs-4">
                            <input id="itemOrder" name="itemOrder" class="inputW150" value="${bean.itemOrder}"/>
                        </div>
                    </div>

                    <div class="clearfix form-actions">
                        <div class="col-bt-center">
                            <button class="btn btn-primary" type="button" id="saveBtn" onclick="save()">
                                <i class="ace-icon fa fa-check bigger-110 icon-save"></i>
                                保 存
                            </button>

                            <button class="btn btn-gray" type="button" id="closeBtn" onclick="closeLayer()">
                                <i class="ace-icon fa fa-check bigger-110 icon-remove"></i>
                                关 闭
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

