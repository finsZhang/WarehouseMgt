package com.zx.whm.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Walter on 2017/4/17.
 */
public class SysUserVo {

    private long id;
    private String name;
    private Integer sex;
    private String userName;
    private String password;
    private Integer status;
    private String stationCode;
    private String roleCode;
    private Timestamp createDate;
    private String comment;

    public SysUserVo() {
    }

    public SysUserVo(long id, String name, Integer sex, String userName, String password, Integer status, String stationCode, String roleCode, Timestamp createDate, String comment) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.userName = userName;
        this.password = password;
        this.status = status;
        this.stationCode = stationCode;
        this.roleCode = roleCode;
        this.createDate = createDate;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }




}
