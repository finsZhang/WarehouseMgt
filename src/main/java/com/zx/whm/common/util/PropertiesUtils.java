package com.zx.whm.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/12/17
 * Time: 17:45
 * Email:zhangfz3@asiainfo.com
 */
public class PropertiesUtils extends
        PropertyPlaceholderConfigurer {
    private String[] encryptPropNames = {};

    private static Map<String, String> ctxPropertiesMap;
    @Override
    protected String convertProperty(String propertyName, String propertyValue){
        //如果在加密属性名单中发现该属性
        if (isEncryptProp(propertyName))
        {
            if(propertyValue.startsWith("{o}")){
               return  propertyValue.substring(3);
            }else{
                return Encrypt.DoDecrypt(propertyValue);
            }
        }else {
            return propertyValue;
        }
    }
    @Override
    protected void processProperties(
            ConfigurableListableBeanFactory beanFactoryToProcess,
            Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        ctxPropertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }

    public static String getContextProperty(String name) {
        return ctxPropertiesMap.get(name);
    }

    private boolean isEncryptProp(String propertyName)
    {
        for (String encryptName : encryptPropNames)
        {
            if (encryptName.equals(propertyName))
            {
                return true;
            }
        }
        return false;
    }
}
