/**
* 功能：固定表头
* @Param viewid     表格的id
* @Param scrollid   滚动条所在容器的id
* @Param size       表头的行数（复杂表头可能不止一行）
*/
function scroll(viewid, scrollid, size) {
    // 获取滚动条容器
    var scroll = document.getElementById(scrollid);
    // 将表格拷贝一份
    var tb2 = document.getElementById(viewid).cloneNode(true);
    // 获取表格的行数
    var len = tb2.rows.length;
    // 将拷贝得到的表格中非表头行删除
    for (var i = tb2.rows.length; i > size; i--) {
        // 每次删除数据行的第一行
        tb2.deleteRow(size);
    }
// 创建一个div
    var bak = document.getElementById("over-display");
    if(bak == null) {
        bak = document.createElement("div");
        bak.id = "over-display";
        // 将div添加到滚动条容器中
        scroll.appendChild(bak);
    }
    bak.innerHTML = "";
    // 将拷贝得到的表格在删除数据行后添加到创建的div中
    bak.appendChild(tb2);
    // 设置创建的div的position属性为absolute，即绝对定于滚动条容器（滚动条容器的position属性必须为relative）
    bak.style.position = "absolute";
    // 设置创建的div的背景色与原表头的背景色相同（貌似不是必须）
//        bak.style.backgroundColor = "#cfc";
    // 设置div的display属性为block，即显示div（貌似也不是必须，但如果你不希望总是显示拷贝得来的表头，这个属性还是有用处的）
    bak.style.display = "block";
    // 设置创建的div的left属性为0，即该div与滚动条容器紧贴
//    bak.style.left = 0;
    bak.style.left = $("#" + scrollid).scrollLeft;
    bak.style.width = $("#" + scrollid).width();
    // 设置div的top属性为0，初期时滚动条位置为0，此属性与left属性协作达到遮盖原表头
    bak.style.top = "0px";
    // 给滚动条容器绑定滚动条滚动事件，在滚动条滚动事件发生时，调整拷贝得来的表头的top值，保持其在可视范围内，且在滚动条容器的顶端
    scroll.onscroll = function () {
        // 设置div的top值为滚动条距离滚动条容器顶部的距离值
        bak.style.top = this.scrollTop + "px";
    }
}

function sortTable(sTableId, iCol, sDataType) {
    var oTable = document.getElementById(sTableId);//获得表
    var oTBody = oTable.tBodies[0];//获得放数据的body,因为该表格只有一个tbody,所以直接用[0]
    var colRows = oTBody.rows;//获得tbody里所有的tr
    var aTRs = new Array();//声明一个数组
    for (var i = 0; i < colRows.length; i++) {
        aTRs[i] = colRows[i];//将tr依次放入数组中;
    }
    if (oTable.sortCol == iCol) {
        aTRs.reverse();//如果当前要排的列和上次排的列是一样的,就直接逆向排序;也就是说连着对一列点两次,就会进行升序,降序的转换.
    }
    else {
        aTRs.sort(getSortFunction(iCol, sDataType));//排序,并且传入一个获得到的比较函数;
    }

    var oFragement = document.createDocumentFragment();//创建文档碎片,用来存放排好的tr
    for (var i = 0; i < aTRs.length; i++) {
        oFragement.appendChild(aTRs[i]);//将tr绑定到碎片上.
    }
    oTBody.appendChild(oFragement);//将碎片绑定在表格上
    oTable.sortCol = iCol;//记住当前列,这个可以用来判断是对数组进行反向排序还是重新按列排;
}
function getSortFunction(iCol, sDataType) {
    return function compareTRs(oTR1, oTR2) {
        var vValue1, vValue2;
        if (oTR1.cells[iCol].getAttribute("value")) {
            vValue1 = convert(oTR1.cells[iCol].getAttribute("value"), sDataType);
            vValue2 = convert(oTR2.cells[iCol].getAttribute("value"), sDataType);
        }
        else {
            vValue1 = convert(oTR1.cells[iCol].firstChild.nodeValue, sDataType);
            vValue2 = convert(oTR2.cells[iCol].firstChild.nodeValue, sDataType)
        }
        if (vValue1 < vValue2) {
            return -1;
        }
        else if (vValue1 > vValue2) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
function convert(sValue, sDataType) {
    switch (sDataType) {
        case "int":
            return parseInt(sValue);
        case "float":
            return parseFloat(sValue);
        case "date":
            return new Date(Date.parse(sValue));
        default:
            return sValue;
    }
}