package com.zx.whm.vo;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/4/15
 * Time: 13:21
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
public class SysDictitemVo {
    private String dictName;
    private String itemName;
    private String itemNo;
    private String parentItemNo;
    private Integer itemOrder;
    private String itemState;
    private String itemDesc;
    private Timestamp createDate;

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getParentItemNo() {
        return parentItemNo;
    }

    public void setParentItemNo(String parentItemNo) {
        this.parentItemNo = parentItemNo;
    }

    public Integer getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }

    public String getItemState() {
        return itemState;
    }

    public void setItemState(String itemState) {
        this.itemState = itemState;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }


}
