package com.zx.whm.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: zhangfz3
 * Date: 2017/4/15
 * Time: 13:21
 * Tel:18665598790
 * Email:zhangfz3@asiainfo.com
 */
public class ShipmentRecordVo {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDayNo() {
        return dayNo;
    }

    public void setDayNo(String dayNo) {
        this.dayNo = dayNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProdDetail() {
        return prodDetail;
    }

    public void setProdDetail(String prodDetail) {
        this.prodDetail = prodDetail;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCustInfo() {
        return custInfo;
    }

    public void setCustInfo(String custInfo) {
        this.custInfo = custInfo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDispatchClerk() {
        return dispatchClerk;
    }

    public void setDispatchClerk(String dispatchClerk) {
        this.dispatchClerk = dispatchClerk;
    }

    public String getModifyConent() {
        return modifyConent;
    }

    public void setModifyConent(String modifyConent) {
        this.modifyConent = modifyConent;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(String weekNo) {
        this.weekNo = weekNo;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorUserName() {
        return creatorUserName;
    }

    public void setCreatorUserName(String creatorUserName) {
        this.creatorUserName = creatorUserName;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }
}
