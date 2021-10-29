package com.gary.cloudinteractive.webapi.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
public class ZipCode  implements Serializable {
    @ApiModelProperty(example = "100", value = "郵遞區號", required=true)
    private String zipCode;
    @ApiModelProperty(example = "100 臺北市中正區", value = "郵遞區號名稱", required=true)
    private String zipName;

    public ZipCode(String zipCode, String zipName) {
        this.zipCode = zipCode;
        this.zipName = zipName;
    }
}
