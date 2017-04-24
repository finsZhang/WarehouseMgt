package com.zx.whm.service.impl;

import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.dao.CommonDao;
import com.zx.whm.exception.DBException;
import com.zx.whm.service.CommonSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by lenovo on 2017/1/3.
 */
@Service
public class CommonSVImpl  implements CommonSV {
    @Autowired
    private CommonDao commonDao;
    public long getIdBySeqName(String seqName) throws DBException {
        return commonDao.getIdBySeqName(seqName);
    }
    public <T> ResultDTO<T> findPageListBySql(String sql, ResultDTO<T> resultDTO, Class<T> clz)throws DBException{
        return commonDao.findPageListBySql(sql,resultDTO,clz);
    }

    public <T> ResultDTO<T> findPageListBySql(String sql, ResultDTO<T> resultDTO, Class<T> clz,boolean isMysql)throws DBException{
        return commonDao.findPageListBySql(sql,resultDTO,clz,isMysql);
    }

}
