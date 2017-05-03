package com.zx.whm.dao.impl;
import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.dao.CommonDao;
import org.apache.commons.beanutils.ConvertUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/12/27.
 */
@Repository
public class CommonDaoImpl implements CommonDao {
    @PersistenceContext(unitName="entityManagerFactoryWhm")
    EntityManager em;
    private Logger logger = LoggerFactory.getLogger(CommonDaoImpl.class);
    public long getIdBySeqName(String seqName) {
        String sql = "select "+seqName+".nextval from dual";
        //执行原生SQL
        Query nativeQuery = em.createNativeQuery(sql);
        BigDecimal id =  (BigDecimal)nativeQuery.getSingleResult();
        return  id.longValue();
    }
    @Transactional(readOnly = true)
    public <T> ResultDTO<T> findPageListBySql(String sql, ResultDTO<T> resultDTO , Class<T> clz) {
        int startNum = resultDTO.getStart();
        int endNum = resultDTO.getEnd();
        String pageSql = "select * from (select z.*,rownum as row_index from ("+sql+") z where rownum<="+endNum+") where row_index >="+startNum;
        String countSql = "select count(1) from ("+sql+") ";

        Query countQuery = em.createNativeQuery(countSql);
        int total = ((BigDecimal)countQuery.getSingleResult()).intValue();
        resultDTO.setRecords(total);
        if(total>0){
            List<T> datas = queryListEntity(pageSql,clz);
            resultDTO.setRows(datas);
            return resultDTO;
       }
       return resultDTO;
    }

    @Transactional(readOnly = true)
    public <T> ResultDTO<T> findPageListBySql(String sql, ResultDTO<T> resultDTO , Class<T> clz,boolean isMySql) {
        int startNum = resultDTO.getStart()-1;
        int endNum = resultDTO.getEnd();
        String pageSql = "select * from ("+sql+") _X LIMIT "+startNum+","+endNum;
        String countSql = "select count(1) from ("+sql+") _X";

        Query countQuery = em.createNativeQuery(countSql);
        int total = countQuery.getResultList().size();
        resultDTO.setRecords(total);
        if(total>0){
            List<T> datas = queryListEntity(pageSql,clz);
            resultDTO.setRows(datas);
            return resultDTO;
        }
        return resultDTO;
    }

    @Transactional(readOnly = true)
    public ResultDTO findPageListBySql(String sql,ResultDTO resultDTO) {
        int startNum = resultDTO.getStart()-1;
        int endNum = resultDTO.getEnd();
        String pageSql = "select * from ("+sql+") _X LIMIT "+startNum+","+endNum;
        String countSql = "select count(1) from ("+sql+") _X";

        Query countQuery = em.createNativeQuery(countSql);
        int total = countQuery.getResultList().size();
        resultDTO.setRecords(total);
        if(total>0) {
            Query query = em.createNativeQuery(sql);
            SQLQuery nativeQuery = query.unwrap(SQLQuery.class);
            nativeQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List<Map<String, Object>> result = nativeQuery.list();
            resultDTO.setRows(result);
        }
        return resultDTO;
    }


    @Transactional(readOnly = true)
    public <T>List<T> queryListEntity(String sql, Class<T> clz){
        Query query = em.createNativeQuery(sql);
        SQLQuery nativeQuery = query.unwrap(SQLQuery.class);
        nativeQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> result =  nativeQuery.list();
        List<T>  entityList = null;
        if (clz != null) {
             entityList = convert(clz, result);
        }
        return entityList;
    }

    private <T> List<T> convert(Class<T> clazz, List<Map<String, Object>> list) {
        List<T> result;
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        result = new ArrayList<T>();
        try {
            PropertyDescriptor[] props = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
            for (Map<String, Object> map : list) {
                T obj = clazz.newInstance();
                for (String key:map.keySet()) {
                    String attrName = key.toLowerCase();
                    for (PropertyDescriptor prop : props) {
                        attrName = removeUnderLine(attrName);
                        if (!attrName.equals(prop.getName())) {
                            continue;
                        }
                        Method method = prop.getWriteMethod();
                        Object value = map.get(key);
                        if (value != null) {
                            value = ConvertUtils.convert(value,prop.getPropertyType());
                        }
                        method.invoke(obj,value);
                    }
                }
                result.add(obj);
            }
        } catch (Exception e) {
            logger.error("数据转换错误:"+e.getMessage());
            throw new RuntimeException("数据转换错误");
        }
        return result;
    }

    private String removeUnderLine(String attrName) {
        //去掉数据库字段的下划线
        if(attrName.contains("_")) {
            String[] names = attrName.split("_");
            String firstPart = names[0];
            String otherPart = "";
            for (int i = 1; i < names.length; i++) {
                String word = names[i].replaceFirst(names[i].substring(0, 1), names[i].substring(0, 1).toUpperCase());
                otherPart += word;
            }
            attrName = firstPart + otherPart;
        }
        return attrName;
    }
}