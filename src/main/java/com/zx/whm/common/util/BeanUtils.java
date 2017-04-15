package com.zx.whm.common.util;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-9-17
 * Time: 上午10:06
 * To change this template use File | Settings | File Templates.
 */
public class BeanUtils {
    public static String getGetMethodNameFromFiledName(String fieldName){
        return "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
    }
}
