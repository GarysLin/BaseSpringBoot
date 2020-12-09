package com.gary.cloudinteractive.webapi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gary.cloudinteractive.webapi.model.ZipCode;
import com.gary.cloudinteractive.webapi.utils.FileUtil;
import com.gary.cloudinteractive.webapi.utils.GsonFileUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TestMethod {
    public static void main(String[] args) throws Exception {
        TestMethod test = new TestMethod();
        test.convertZipCode();
        test.convertZipCodeGson();

    }

    public void convertZipCode() throws IOException {
        String doc = "zipCode.json";

        JSONArray zipCode = FileUtil.loadJsonArrayFile(doc);

        List<ZipCode> zipList = zipCode.stream().map(o -> (JSONObject)o)
                .map(o -> {
                    String name = o.getString("name");
                    return o.getJSONArray("districts").stream()
                            .map(l -> (JSONObject)l)
                            .map(l -> new ZipCode(l.getString("zip"), l.getString("zip") + " " + name + l.getString("name")))
                            .collect(Collectors.toList());
                }).flatMap(List::stream)
                .sorted(Comparator.comparing(ZipCode::getZipCode))
                .collect(Collectors.toList());

        System.out.println(zipList);
    }

    public void convertZipCodeGson() throws IOException {
        String doc = "zipCode.json";

        JsonArray zipCode = GsonFileUtil.loadJsonArrayFile(doc);
        Stream<JsonElement> stream = StreamSupport.stream(zipCode.spliterator(), true);

        List<ZipCode> zipList = stream.map(JsonElement::getAsJsonObject)
                .map(o -> {
                    String name = o.get("name").getAsString();
                    Stream<JsonElement> districts = StreamSupport.stream(o.getAsJsonArray("districts").spliterator(), true);
                    return districts.map(JsonElement::getAsJsonObject)
                            .map(l -> new ZipCode(l.get("zip").getAsString(), l.get("zip").getAsString() + " " + name + l.get("name").getAsString()))
                            .collect(Collectors.toList());
                }).flatMap(List::stream)
                .sorted(Comparator.comparing(ZipCode::getZipCode))
                .collect(Collectors.toList());

        System.out.println(zipList);
    }
}
