package com.gary.cloudinteractive.webapi.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class GsonFileUtil {

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

    public static JsonObject loadJsonFile(String doc, String encodeing) throws IOException {
        return JsonParser.parseString(loadFile(doc, encodeing)).getAsJsonObject();
    }

    public static JsonObject loadJsonFile(String doc) throws IOException {
        return loadJsonFile(doc, "utf8");
    }

    public static JsonArray loadJsonArrayFile(String doc, String encodeing) throws IOException {
        return JsonParser.parseString(loadFile(doc, encodeing)).getAsJsonArray();
    }

    public static JsonArray loadJsonArrayFile(String doc) throws IOException {
        return loadJsonArrayFile(doc, "utf8");
    }

    public static <T> T loadFileToModel(String doc, Class<T> clazz) throws IOException {
        return new Gson().fromJson(loadFile(doc), clazz);
    }

}
