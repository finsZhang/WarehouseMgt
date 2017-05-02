package com.zx.whm.dao;

import com.zx.whm.common.domain.ResultDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lenovo on 2017/1/3.
 */
@Repository
public interface CommonDao{
        long getIdBySeqName(String seqName);
        <T> ResultDTO<T> findPageListBySql(String sql, ResultDTO<T> resultDTO, Class<T> clz);
        public <T> ResultDTO<T> findPageListBySql(String sql, ResultDTO<T> resultDTO , Class<T> clz,boolean isMySql);
        public <T>List<T> queryListEntity(String sql, Class<T> clz);
        ResultDTO findPageListBySql(String sql,ResultDTO resultDTO);
}
