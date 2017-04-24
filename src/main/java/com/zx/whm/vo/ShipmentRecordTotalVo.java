package com.zx.whm.vo;

import java.sql.Timestamp;

/**
 * Created by Walter on 2017/4/20.
 * 派单统计
 */
public class ShipmentRecordTotalVo {
    private Timestamp createDate;
    private String weekNo;
    private String dispatchClerk;
    private String startDate;
    private String endDate;
    private double amt;
    private long  num;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public String getDispatchClerk() {
        return dispatchClerk;
    }

    public void setDispatchClerk(String dispatchClerk) {
        this.dispatchClerk = dispatchClerk;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }




}
