package com.zx.whm.dao;

import com.zx.whm.common.domain.ResultDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by lenovo on 2017/1/3.
 */
@Repository
public interface CommonDao{
        long getIdBySeqName(String seqName);
        <T> ResultDTO<T> findPageListBySql(String sql, ResultDTO<T> resultDTO, Class<T> clz);
}
