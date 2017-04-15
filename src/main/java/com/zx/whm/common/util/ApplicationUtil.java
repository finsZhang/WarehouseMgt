package com.zx.whm.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/11/5
 * Time: 17:31
 * Email:zhangfz3@asiainfo.com
 */
public class ApplicationUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationUtil.applicationContext = applicationContext;
    }
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }
    
    public static <T> T getBean(String name,Class<T> clz){
        return applicationContext.getBean(name,clz);
    }
    public static <T> T getBean(Class<T> clz){
        return applicationContext.getBean(clz);
    }
}
