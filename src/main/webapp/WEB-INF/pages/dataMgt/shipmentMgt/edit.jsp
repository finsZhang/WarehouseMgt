<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="cache-control" content="no-cache">
    <%@ include file="/common/taglibs.jsp" %>
    <%@ include file="/common/meta.jsp" %>

    <script type="text/javascript" src="${ctx}/whm/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    <link type="text/css" rel="stylesheet"
          href="${ctx}/whm/statics/common/js/jquery-easyui/themes/default/easyui.css"/>

    <script type="text/javascript" src="${ctx}/whm/statics/pages/js/dataMgt/shipmentMgt/edit.js?_dc=${staticVersion}"></script>

    <link rel="stylesheet" href="${ctx}/whm/statics/common/plugins/validate/jquery.validate.css"
          type="text/css">
    <script type="text/javascript" src="${ctx}/whm/statics/common/plugins/validate/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctx}/whm/statics/common/plugins/validate/additional-methods.js"></script>
    <script type="text/javascript" src="${ctx}/whm/statics/common/plugins/validate/messages_cn.js"></script>
</head>

<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12" style="padding:6px;">
                <form class="form-horizontal" role="form" id="editForm">
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 当日编号： </label>
                        <div class="col-xs-4">
                            <input id="dayNo" name="dayNo" value="${dayNo}" readonly="readonly"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 类型： </label>
                        <div class="col-xs-4">
                            <select name="type" id="type">
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 商品明细： </label>

                        <div class="col-xs-10">
                            <textarea rows="2" cols="60" name="prodDetail" id="prodDetail"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 金额(元)： </label>

                        <div class="col-xs-4">
                            <input id="amount" name="amount"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 行数： </label>

                        <div class="col-xs-4">
                            <input id="lineNum" name="lineNum" class="inputW150"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 付款方式： </label>

                        <div class="col-xs-4">
                            <select name="payType" id="payType">
                            </select>
                            <label id="payTypeView" class="formlabel"></label>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 派单员： </label>

                        <div class="col-xs-4">
                            <select name="dispatchClerk" id="dispatchClerk">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 客户信息： </label>

                        <div class="col-xs-10">
                            <textarea rows="2" cols="60" name="custInfo" id="custInfo"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 备注： </label>

                        <div class="col-xs-10">
                            <textarea rows="2" cols="60" name="comment" id="comment"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 后期更改： </label>

                        <div class="col-xs-10">
                            <textarea rows="2" cols="60" name="modifyConent" id="modifyConent"></textarea>
                        </div>
                    </div>
                    <div class="clearfix form-actions">
                        <div class="col-bt-center">
                            <button class="btn btn-primary" type="button" id="saveBtn">
                                <i class="ace-icon fa fa-check bigger-110 icon-save"></i>
                                确 认
                            </button>
                            <button class="btn btn-gray" type="button" id="closeBtn">
                                <i class="ace-icon fa fa-check bigger-110 icon-remove"></i>
                                关 闭
                            </button>
                        </div>
                    </div>
                    <input type="hidden" name="id" id="id" value="${id}"/>
                </form>
            </div>
        </div>
    </div>
</div>
<div id="divExecute" class="executing">请等待...</div>
<div id="divBack" class="overlay"/>
</html>