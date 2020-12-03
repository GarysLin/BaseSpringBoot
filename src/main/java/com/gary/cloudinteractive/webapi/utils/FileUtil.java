package com.gary.cloudinteractive.webapi.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

    public static String loadFile(String doc, String encodeing) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = null;
        String text = "";
        try {
            inputStream = classloader.getResourceAsStream(doc);
            text = IOUtils.toString(inputStream,encodeing);
        } finally {
            if (inputStream != null) inputStream.close();
        }

        return text;
    }

    public static String loadFile(String doc) throws IOException {
        return loadFile(doc, "utf8");
    }

    public static JSONObject loadJsonFile(String doc, String encodeing) throws IOException {
        return JSON.parseObject(loadFile(doc, encodeing));
    }

    public static JSONObject loadJsonFile(String doc) throws IOException {
        return loadJsonFile(doc, "utf8");
    }

    public static JSONArray loadJsonArrayFile(String doc, String encodeing) throws IOException {
        return JSON.parseArray(loadFile(doc, encodeing));
    }

    public static JSONArray loadJsonArrayFile(String doc) throws IOException {
        return loadJsonArrayFile(doc, "utf8");
    }

    public static <T> T loadFileToModel(String doc, Class<T> clazz) throws IOException {
        return JSONObject.parseObject(loadFile(doc), clazz);
    }

}
