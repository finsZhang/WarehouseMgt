
$(function () {
    var id = $("#id").val();
    init(id);
    $("select").select2();
    $("#closeBtn").click(closeLayer);

    $("#saveBtn").click(function(){
        $("#editForm").submit();
    });

    $("#editForm").validate({

        // 非空校验
        rules : {
            'amount' : {
                required : true,
                number:true,
                numFormat30_2:true
            }
        },
        submitHandler : save
    });
});

//新增
function init(id) {
    if (id){
        edit(id);
    } else{
        add();
    }
}
function add(){
    initDicts();
}

function edit(id) {
    initDicts();
    var data = "id=" + id;
    $.ajax({
        type: "GET",
        async: false,
        url: GLOBAL.WEBROOT + "/shipmentMgt/getRecord",
        cache:false,
        dataType: 'json',
        data: data,
        success: function (data) {
            if (data.ERRCODE == "0") {
                setForm(data.RECORD);
            }
            else {
                info('温馨提示',data.ERRINFO);
            }
        }
    });
}

function setForm(data) {
    $("#dayNo").val(data.dayNo);
    $("#type option").each(function () {
        if ($(this).val() == data.type) {
            $(this).attr("selected", true);
        }
    });
    $("#prodDetail").val(data.prodDetail);
    $("#amount").val(data.amount);
    $("#lineNum").val(data.lineNum);
    $("#payType option").each(function () {
        if ($(this).val() == data.payType) {
            $(this).attr("selected", true);
        }
    });

    $("#dispatchClerk option").each(function () {
        if ($(this).val() == data.dispatchClerk) {
            $(this).attr("selected", true);
        }
    });
    $("#comment").val(data.comment);
    $("#modifyConent").val(data.modifyConent);
}

function save() {
    displayExecuteLayer();
    var data = $("#editForm").serializeObject()
    data = $.toJSON(data);
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/shipmentMgt/saveRecord",
        dataType: 'json',
        contentType: 'application/json',
        data: data,
        success: function (data) {
            hideExecuteLayer();
            if (data.ERRCODE == "0") {
                infoCall('温馨提示',"保存成功！",saveSuccess);
            }
            else {
                info('温馨提示',data.ERRINFO);
            }
        }
    });
}

function initDicts() {
    $.ajax({
        type: "POST",
        async:false,
        data:{"type":"SHIPMENT_RECORD_TYPE","payType":"SHIPMENT_RECORD_PAY_TYPE","dispatchClerk":"SHIPMENT_RECORD_CLERK"},
        datatype: "json",
        url: GLOBAL.WEBROOT + "/common/dictItem/getDictItemCondition.ajax",
        success: function (data) {
            var jsonType = eval(data.type);
            var typeSelect = $("#type");
            typeSelect.empty();
            for (var i = 0; i < jsonType.length; i++) {
                typeSelect.append("<option value='" + jsonType[i].itemNo + "'>" + jsonType[i].itemName + "</option>");
            }

            var jsonPayType = eval(data.payType);
            var payTypeSelect = $("#payType");
            payTypeSelect.empty();
            for (var i = 0; i < jsonPayType.length; i++) {
                payTypeSelect.append("<option value='" + jsonPayType[i].itemNo + "'>" + jsonPayType[i].itemName + "</option>");
            }

            var jsonDispatchClerk = eval(data.dispatchClerk);
            var dispatchClerkSelect = $("#dispatchClerk");
            dispatchClerkSelect.empty();
            for (var i = 0; i < jsonDispatchClerk.length; i++) {
                dispatchClerkSelect.append("<option value='" + jsonDispatchClerk[i].itemNo + "'>" + jsonDispatchClerk[i].itemName + "</option>");
            }
        }
    });
}

function saveSuccess(){
    parent.reloadGrid();
    closeLayer();
}
function closeLayer(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}