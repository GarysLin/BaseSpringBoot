package com.gary.cloudinteractive.webapi;

import com.gary.cloudinteractive.webapi.dao.ZipCodeMapper;
import com.gary.cloudinteractive.webapi.model.mybatis.ZipCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;


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
//        ZipCode t = zipCodeMapper.getOneModel(param);
//        System.out.println(t);
    }
}
