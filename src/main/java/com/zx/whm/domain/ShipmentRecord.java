package com.zx.whm.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/4/15
 * Time: 13:04
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
@Entity
@Table(name = "shipment_record", schema = "warehouse", catalog = "")
public class ShipmentRecord {
    private long id;
    private String dayNo;
    private String type;
    private String prodDetail;
    private BigDecimal amount;
    private Integer lineNum;
    private String payType;
    private String custInfo;
    private String comment;
    private String dispatchClerk;
    private String modifyConent;
    private Timestamp createDate;
    private String weekNo;
    private String creatorName;
    private String creatorUserName;
    private Timestamp modifyDate;

    @Id
    @Column(name = "ID", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DAY_NO", nullable = true, length = 32)
    public String getDayNo() {
        return dayNo;
    }

    public void setDayNo(String dayNo) {
        this.dayNo = dayNo;
    }

    @Basic
    @Column(name = "TYPE", nullable = true, length = 32)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "PROD_DETAIL", nullable = true, length = 4000)
    public String getProdDetail() {
        return prodDetail;
    }

    public void setProdDetail(String prodDetail) {
        this.prodDetail = prodDetail;
    }

    @Basic
    @Column(name = "AMOUNT", nullable = true, precision = 2)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "LINE_NUM", nullable = true, precision = 0)
    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    @Basic
    @Column(name = "PAY_TYPE", nullable = true, length = 32)
    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Basic
    @Column(name = "CUST_INFO", nullable = true, length = 255)
    public String getCustInfo() {
        return custInfo;
    }

    public void setCustInfo(String custInfo) {
        this.custInfo = custInfo;
    }

    @Basic
    @Column(name = "COMMENT", nullable = true, length = 4000)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "DISPATCH_CLERK", nullable = true, length = 64)
    public String getDispatchClerk() {
        return dispatchClerk;
    }

    public void setDispatchClerk(String dispatchClerk) {
        this.dispatchClerk = dispatchClerk;
    }

    @Basic
    @Column(name = "MODIFY_CONENT", nullable = true, length = 4000)
    public String getModifyConent() {
        return modifyConent;
    }

    public void setModifyConent(String modifyConent) {
        this.modifyConent = modifyConent;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "WEEK_NO", nullable = true, length = 32)
    public String getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(String weekNo) {
        this.weekNo = weekNo;
    }

    @Basic
    @Column(name = "CREATOR_NAME", nullable = true, length = 64)
    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    @Basic
    @Column(name = "CREATOR_USER_NAME", nullable = true, length = 64)
    public String getCreatorUserName() {
        return creatorUserName;
    }

    public void setCreatorUserName(String creatorUserName) {
        this.creatorUserName = creatorUserName;
    }

    @Basic
    @Column(name = "MODIFY_DATE", nullable = true)
    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShipmentRecord that = (ShipmentRecord) o;

        if (id != that.id) return false;
        if (dayNo != null ? !dayNo.equals(that.dayNo) : that.dayNo != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (prodDetail != null ? !prodDetail.equals(that.prodDetail) : that.prodDetail != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (lineNum != null ? !lineNum.equals(that.lineNum) : that.lineNum != null) return false;
        if (payType != null ? !payType.equals(that.payType) : that.payType != null) return false;
        if (custInfo != null ? !custInfo.equals(that.custInfo) : that.custInfo != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (dispatchClerk != null ? !dispatchClerk.equals(that.dispatchClerk) : that.dispatchClerk != null)
            return false;
        if (modifyConent != null ? !modifyConent.equals(that.modifyConent) : that.modifyConent != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (weekNo != null ? !weekNo.equals(that.weekNo) : that.weekNo != null) return false;
        if (creatorName != null ? !creatorName.equals(that.creatorName) : that.creatorName != null) return false;
        if (creatorUserName != null ? !creatorUserName.equals(that.creatorUserName) : that.creatorUserName != null)
            return false;
        if (modifyDate != null ? !modifyDate.equals(that.modifyDate) : that.modifyDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (dayNo != null ? dayNo.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (prodDetail != null ? prodDetail.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (lineNum != null ? lineNum.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        result = 31 * result + (custInfo != null ? custInfo.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (dispatchClerk != null ? dispatchClerk.hashCode() : 0);
        result = 31 * result + (modifyConent != null ? modifyConent.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (weekNo != null ? weekNo.hashCode() : 0);
        result = 31 * result + (creatorName != null ? creatorName.hashCode() : 0);
        result = 31 * result + (creatorUserName != null ? creatorUserName.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        return result;
    }
}
