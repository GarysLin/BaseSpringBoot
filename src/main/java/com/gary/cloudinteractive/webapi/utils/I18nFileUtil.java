package com.gary.cloudinteractive.webapi.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class I18nFileUtil {
    public static String loadFile(String doc, String encodeing) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String lang = LocaleContextHolder.getLocale().toString();
        log.debug(lang);
        log.debug(String.valueOf(LocaleUtils.isAvailableLocale(LocaleContextHolder.getLocale())));
        String localeDoc = replaceLast(doc, "\\.", "_" + lang + ".");
        InputStream inputStream = null;
        String text = "";

        try {
            inputStream = classloader.getResourceAsStream(localeDoc);
            if (inputStream == null) {
                inputStream = classloader.getResourceAsStream(doc);
            }
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

    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }
}
