<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="cache-control" content="no-cache">
    <%@ include file="/common/meta.jsp" %>
    <title>上传制卡数据</title>
    <script type="text/javascript" src="${ctx}/emm/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/emm/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="${ctx}/emm/statics/ace/plugins/select2-3.4.1/select2.js"></script>
    <script type="text/javascript" src="${ctx}/emm/statics/pages/js/dataMgt/makeCardMgt/uploadData.js?_dc=${staticVersion}"></script>

</head>
<body>
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12">
                <form class="form-horizontal" role="form" id="editForm">
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right must"> 数据类型： </label>
                        <div class="col-xs-10">
                            <select name="dataType" id="dataType">
                                <option value="01">标准卡</option>
                                <option value="02">衍生卡</option>
                            </select></td>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right must"> 请选择文件： </label>
                        <div class="col-xs-10">
                            <input id="cardDataFile" type="file" name="cardDataFile"/>
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

