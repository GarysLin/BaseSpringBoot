package com.gary.cloudinteractive.webapi.model.mybatis;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.type.Alias;

@Data
//@Alias(value="zipCode")
public class ZipCode {
    private int id;
    private String name;
}
