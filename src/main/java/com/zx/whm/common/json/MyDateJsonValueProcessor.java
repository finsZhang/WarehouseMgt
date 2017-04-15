package com.zx.whm.common.json;

import net.sf.json.JsonConfig;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-2-6
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public class MyDateJsonValueProcessor implements net.sf.json.processors.JsonValueProcessor{

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        if(null == value ){
            return "" ;
        }else{
            if(value instanceof java.util.Date||value instanceof java.sql.Date||value instanceof Timestamp){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return df.format(value);
            }
        }
        return value.toString();
    }

    public Object processObjectValue(String s, Object object, JsonConfig jsonConfig) {
        if(null == object ){
            return "" ;
        }else{

            if(object instanceof java.util.Date||object instanceof java.sql.Date||object instanceof Timestamp){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return df.format(object);
            }
        }
        return object.toString();
    }
}