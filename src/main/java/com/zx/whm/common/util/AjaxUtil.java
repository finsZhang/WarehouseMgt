package com.zx.whm.common.util;

import com.zx.whm.common.domain.ResultDTO;
import com.zx.whm.common.json.WebJsonConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-2-6
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class AjaxUtil {

    private static Log logger = LogFactory.getLog(AjaxUtil.class);

    public static JSONObject jqGridJson(ResultDTO resultDTO){
        JSONObject json = JSONObject.fromObject(resultDTO, WebJsonConfig.getInstance());
        return json;
    }

    public static JSONObject page2Json(long total, JSONArray jsonArray) {
        JSONObject json = new JSONObject();
        json.put("totalProperty", total);
        json.put("dataList", jsonArray);
        return json;
    }

    public static JSONObject list2Json(List dataList) {
        if (dataList == null || dataList.size() == 0) {
            return page2Json(null);
        }

        JSONObject json = new JSONObject();
        json.put("totalProperty", dataList.size());

        JSONArray jsonArray = new JSONArray();
        for (Object item : dataList) {
            Class c = item.getClass();
            if (c == int.class || c == Integer.class ||
                    c == long.class || c == Long.class ||
                    c == float.class || c == Float.class ||
                    c == double.class || c == Double.class ||
                    c == boolean.class || c == Boolean.class ||
                    c == byte.class || c == Byte.class ||
                    c == char.class || c == Byte.class ||
                    c == short.class || c == Short.class ||
                    c == String.class) {
                jsonArray.add(item);
            } else {
                jsonArray.add(JSONObject.fromObject(item), WebJsonConfig.getInstance());
            }
        }
        json.put("dataList", jsonArray);

        return json;
    }

    public static JSONObject page2Json(Page page) {
        JSONObject json = new JSONObject();
        if (page == null) {
            json.put("totalProperty", 0);
            json.put("dataList", ArrayUtils.EMPTY_OBJECT_ARRAY);
            return json;
        }

        json.put("totalProperty", page.getTotalElements());
        List dataList = page.getContent();
        if (dataList != null && dataList.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (Object item : dataList) {
                Class c = item.getClass();
                if (c == int.class || c == Integer.class ||
                        c == long.class || c == Long.class ||
                        c == float.class || c == Float.class ||
                        c == double.class || c == Double.class ||
                        c == boolean.class || c == Boolean.class ||
                        c == byte.class || c == Byte.class ||
                        c == char.class || c == Byte.class ||
                        c == short.class || c == Short.class ||
                        c == String.class) {
                    jsonArray.add(item);
                } else {
                    jsonArray.add(JSONObject.fromObject(item), WebJsonConfig.getInstance());
                }
            }
            json.put("dataList", jsonArray);
        } else {
            json.put("dataList", ArrayUtils.EMPTY_OBJECT_ARRAY);
        }
        return json;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static JSONObject output(boolean success, String msg, JSONObject extData) {
        Map map = new HashMap();
        map.put("success", success);

        map.put("_code", success ? 0 : 1);
        map.put("_msg", msg);
        map.put("errorMessage", msg);

        if (extData == null || extData.equals("null")) {
            extData = new JSONObject();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        jsonObject
                .put("data", extData);

        return jsonObject;
    }

    public static JSONObject success(String msg) {
        return output(true, msg, null);
    }

    public static JSONObject success(String msg, Object object) {
        JSONObject json = JSONObject.fromObject(object);
        return output(true, msg, json);
    }

    public static String emptyVm() {
        return "commons/empty";
    }

    public static JSONObject failure(String msg) {
        return output(false, msg, null);
    }

    public static JSONObject failure(String msg, Object object) {
        JSONObject json = JSONObject.fromObject(object);
        return output(false, msg, json);
    }

    public static JSONObject emptyPage() {
        return page2Json(null);
    }

}
