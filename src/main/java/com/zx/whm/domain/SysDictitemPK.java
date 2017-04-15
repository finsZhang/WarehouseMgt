package com.zx.whm.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/1/16
 * Time: 15:23
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
public class SysDictitemPK implements Serializable {
    private String dictName;
    private String itemNo;

    @Column(name = "DICT_NAME", nullable = false, length = 48)
    @Id
    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    @Column(name = "ITEM_NO", nullable = false, length = 50)
    @Id
    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysDictitemPK that = (SysDictitemPK) o;

        if (dictName != null ? !dictName.equals(that.dictName) : that.dictName != null) return false;
        if (itemNo != null ? !itemNo.equals(that.itemNo) : that.itemNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dictName != null ? dictName.hashCode() : 0;
        result = 31 * result + (itemNo != null ? itemNo.hashCode() : 0);
        return result;
    }
}
