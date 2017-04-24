package com.zx.whm.service;


import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.exception.DBException;

/**
 * Created by lenovo on 2017/1/3.
 */
public interface CommonSV {
    public long getIdBySeqName(String seqName)throws DBException;
    <T> ResultDTO<T>  findPageListBySql(String sql, ResultDTO<T> resultDTO, Class<T> clz)throws DBException;
    <T> ResultDTO<T>  findPageListBySql(String sql, ResultDTO<T> resultDTO, Class<T> clz,boolean isMysql)throws DBException;
}