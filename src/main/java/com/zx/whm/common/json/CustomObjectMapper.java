package com.zx.whm.common.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/9
 * Time: 11:09
 * Email:zhangfengzhou@asiainfo.com
 */
public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Timestamp.class, new JsonDeserializer<Timestamp>() {
            @Override
            public Timestamp deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException{
                DateFormat formatDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                formatDateTime.setLenient(false);
                formatDate.setLenient(false);
                String timeText = jp.getValueAsString();
                long longTime;
                try {
                    longTime = formatDateTime.parse(timeText).getTime();
                } catch (ParseException e) {
                    try {
                        longTime = formatDate.parse(timeText).getTime();
                    } catch (ParseException e1) {
                        throw  new IOException("date parse error!");
                    }
                }
                return new Timestamp(longTime);
            }
        });
        this.registerModule(module);
    }

}
