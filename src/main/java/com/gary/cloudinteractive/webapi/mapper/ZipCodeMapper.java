package com.gary.cloudinteractive.webapi.mapper;

import com.gary.cloudinteractive.webapi.model.mybatis.CustZipCode;
import com.gary.cloudinteractive.webapi.model.mybatis.ZipCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ZipCodeMapper {
    List<Map<String, Object>> getAll();
    List<ZipCode> getAllModel();
    Map<String, Object> getOne(int id);
    CustZipCode getOneModel(Map<String, Object> param);
}
