package com.gary.cloudinteractive.webapi.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.IOException;
import java.io.InputStream;

public class GsonI18nFileUtil {
    public static String loadFile(String doc, String encodeing) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String lang = LocaleContextHolder.getLocale().toString();
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

    public static JsonObject loadJsonFile(String doc, String encodeing) throws IOException {
        return JsonParser.parseString(loadFile(doc, encodeing)).getAsJsonObject();
    }

    public static JsonObject loadJsonFile(String doc) throws IOException {
        return loadJsonFile(doc, "utf8");
    }

    public static JsonObject loadJsonArrayFile(String doc, String encodeing) throws IOException {
        return JsonParser.parseString(loadFile(doc, encodeing)).getAsJsonObject();
    }

    public static JsonObject loadJsonArrayFile(String doc) throws IOException {
        return loadJsonArrayFile(doc, "utf8");
    }

    public static <T> T loadFileToModel(String doc, Class<T> clazz) throws IOException {
        return new Gson().fromJson(loadFile(doc), clazz);
    }

    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }
}
