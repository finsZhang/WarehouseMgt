package com.zx.whm.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/4/15
 * Time: 13:01
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
@Entity
@Table(name = "sys_dictitem", schema = "warehouse", catalog = "")
@IdClass(SysDictitemPK.class)
public class SysDictitem implements Serializable{
    private String dictName;
    private String itemName;
    private String itemNo;
    private String parentItemNo;
    private Integer itemOrder;
    private String itemState;
    private String itemDesc;
    private Timestamp createDate;

    @Id
    @Basic
    @Column(name = "DICT_NAME", nullable = false, length = 48)
    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    @Basic
    @Column(name = "ITEM_NAME", nullable = true, length = 200)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Id
    @Basic
    @Column(name = "ITEM_NO", nullable = false, length = 50)
    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    @Basic
    @Column(name = "PARENT_ITEM_NO", nullable = true, length = 50)
    public String getParentItemNo() {
        return parentItemNo;
    }

    public void setParentItemNo(String parentItemNo) {
        this.parentItemNo = parentItemNo;
    }

    @Basic
    @Column(name = "ITEM_ORDER", nullable = true)
    public Integer getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }

    @Basic
    @Column(name = "ITEM_STATE", nullable = true, length = 1)
    public String getItemState() {
        return itemState;
    }

    public void setItemState(String itemState) {
        this.itemState = itemState;
    }

    @Basic
    @Column(name = "ITEM_DESC", nullable = true, length = 200)
    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysDictitem that = (SysDictitem) o;

        if (dictName != null ? !dictName.equals(that.dictName) : that.dictName != null) return false;
        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;
        if (itemNo != null ? !itemNo.equals(that.itemNo) : that.itemNo != null) return false;
        if (parentItemNo != null ? !parentItemNo.equals(that.parentItemNo) : that.parentItemNo != null) return false;
        if (itemOrder != null ? !itemOrder.equals(that.itemOrder) : that.itemOrder != null) return false;
        if (itemState != null ? !itemState.equals(that.itemState) : that.itemState != null) return false;
        if (itemDesc != null ? !itemDesc.equals(that.itemDesc) : that.itemDesc != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dictName != null ? dictName.hashCode() : 0;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (itemNo != null ? itemNo.hashCode() : 0);
        result = 31 * result + (parentItemNo != null ? parentItemNo.hashCode() : 0);
        result = 31 * result + (itemOrder != null ? itemOrder.hashCode() : 0);
        result = 31 * result + (itemState != null ? itemState.hashCode() : 0);
        result = 31 * result + (itemDesc != null ? itemDesc.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
