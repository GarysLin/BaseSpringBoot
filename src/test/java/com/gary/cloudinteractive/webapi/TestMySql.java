package com.gary.cloudinteractive.webapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gary.cloudinteractive.webapi.mapper.ZipCodeMapper;
import com.gary.cloudinteractive.webapi.model.mybatis.CustZipCode;
import com.gary.cloudinteractive.webapi.model.mybatis.ZipCode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMySql {
    @Autowired
    private ZipCodeMapper zipCodeMapper;

    @Test
    public void findByName() throws Exception {
        System.out.println("aaa");
//        List<Map<String, Object>> a = zipCodeMapper.getAll();
//        System.out.println(a.toString());
//        List<JSONObject> k = a.stream().map(JSONObject::new).collect(Collectors.toList());
//        System.out.println(k);
//        List<JsonObject> j = a.stream().map(o -> new Gson().toJsonTree(o).getAsJsonObject()).collect(Collectors.toList());
//        System.out.println(j);
//
//        Map<String, Object> b = zipCodeMapper.getOne(2);
//        System.out.println(b);
//        System.out.println(new JSONObject(b));
//
//        List<ZipCode> i = zipCodeMapper.getAllModel();
//        System.out.println(i);
//
        Map<String, Object> param = new HashMap<>();
        param.put("id", "1");
        param.put("zip", "100");
        CustZipCode t = zipCodeMapper.getOneModel(param);
        System.out.println(t);
    }
}
