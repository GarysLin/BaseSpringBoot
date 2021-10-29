package com.gary.cloudinteractive.webapi.model.mybatis;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias(value="custZipCode")
public class CustZipCode {
    private int id;
    private String name;
    private String zip;
    private String zipName;
}
