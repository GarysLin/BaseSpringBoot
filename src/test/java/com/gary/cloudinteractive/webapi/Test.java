package com.gary.cloudinteractive.webapi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gary.cloudinteractive.webapi.model.ZipCode;
import com.gary.cloudinteractive.webapi.utils.FileUtil;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) throws Exception {
        Test test = new Test();
        test.convertZipCode();

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
}
