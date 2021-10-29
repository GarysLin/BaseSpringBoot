package com.gary.cloudinteractive.webapi.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.SafeHtml;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
public class Message implements Serializable {
    @ApiModelProperty(example = "", value = "訊息類型 1:推播 2:訊息 3:人員變動", required=true)
    private int messageType;
    @SafeHtml
    @ApiModelProperty(example = "", value = "訊息內容", required=true)
    private String message;
    @ApiModelProperty(example = "", value = "線上人數", required=true)
    private int count;
}
