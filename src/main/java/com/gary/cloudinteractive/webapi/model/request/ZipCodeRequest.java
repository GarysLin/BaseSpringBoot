package com.gary.cloudinteractive.webapi.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
public class ZipCodeRequest implements Serializable {
    @ApiModelProperty(example = "100 臺北市中正區", value = "郵遞區號名稱", required=true)
    private String name;

}
