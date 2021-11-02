package com.gary.cloudinteractive.webapi.dao;

import com.gary.cloudinteractive.webapi.model.mybatis.ZipCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ZipCodeMapper {
    List<Map<String, Object>> getAll();
    List<ZipCode> getAllModel();
    ZipCode getOne(int id);
    ZipCode getOneModel(Map<String, Object> param);
    int insert(Map<String, Object> param);
}
