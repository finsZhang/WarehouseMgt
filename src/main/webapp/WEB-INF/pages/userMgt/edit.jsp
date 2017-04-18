<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="cache-control" content="no-cache">
    <%@ include file="/common/meta.jsp" %>
    <title>用户编辑</title>
    <script type="text/javascript" src="${ctx}/whm/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/whm/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="${ctx}/whm/statics/ace/plugins/select2-3.4.1/select2.js"></script>
    <script type="text/javascript" src="${ctx}/whm/statics/pages/js/userMgt/edit.js?_dc=${staticVersion}"></script>

</head>
<body>
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12">

                <form class="form-horizontal" role="form" id="editForm">
                    <input type="hidden" id="id" name="id" class="inputW150" value="${bean.id}"/>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 用户名： </label>
                        <div class="col-xs-4">
                            <input id="userName" name="userName" class="inputW150" value="${bean.userName}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 用户密码： </label>
                        <div class="col-xs-4">
                            <input id="_password" type="hidden" class="inputW150" value="${bean.password}"/>
                            <input id="password" type="password" name="password" class="inputW150" value="${bean.password}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 用户姓名： </label>
                        <div class="col-xs-4">
                            <input id="name" name="name" class="inputW150" value="${bean.name}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 性别： </label>
                        <div class="col-xs-4">
                            <input id="_sex" type="hidden" class="inputW150" value="${bean.sex}"/>
                            <select name="sex" id="sex" class="inputW150">
                                <option value="1">男</option>
                                <option value="2">女</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 角色： </label>
                        <div class="col-xs-4">
                            <input id="_roleCode" type="hidden"  class="inputW150" value="${bean.roleCode}"/>
                            <select name="roleCode" id="roleCode" class="inputW150">
                                <option value="MANAGER">管理员</option>
                                <option value="COMMON_TELLER">业务员</option>
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 状态： </label>
                        <div class="col-xs-4">
                            <input id="_status" type="hidden"  class="inputW150" value="${bean.status}"/>
                            <select name="status" id="status" class="inputW150">
                                <option value="1">可用</option>
                                <option value="0">禁用</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 岗位： </label>
                        <div class="col-xs-4">
                            <input id="_stationCode" type="hidden"  class="inputW150" value="${bean.stationCode}"/>
                            <select name="stationCode" id="stationCode" class="inputW150">
                                <option value="MANAGER">管理员</option>
                                <option value="COMMON_TELLER">业务员</option>
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 备注： </label>
                        <div class="col-xs-4">
                            <input id="comment" name="comment" class="inputW150" value="${bean.comment}"/>
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

