package com.gary.cloudinteractive.webapi.model.response;
import com.gary.cloudinteractive.webapi.utils.DateTimeUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DefaultHttpResponse<T> {
    @ApiModelProperty(example = "0000", value = "回傳代碼")
    private String resultCode = "9999";
    @ApiModelProperty(example = "test", value = "回傳訊息")
    private String resultMessage = "";
    @ApiModelProperty(example = "{code: 1}", value = "回傳內容")
    private T resultData;
    @ApiModelProperty(example = "2020-08-07 14:03:07.961", value = "回傳時間")
    private String responseDatetime =  DateTimeUtil.getNowStringMs();
    @ApiModelProperty(example = "", value = "回傳代碼2")
    private String subResultCode = "";
    @ApiModelProperty(example = "", value = "回傳訊息2")
    private String subResultMessage = "";
    @ApiModelProperty(example = "", value = "備註")
    private String info = "";
    @ApiModelProperty(example = "zh-tw", value = "語系")
    private String lang = "";
    @ApiModelProperty(example = "", value = "token")
    private String token = "";

    public DefaultHttpResponse(String resultCode, T resultData){
        this.resultCode = resultCode;
        this.resultData = resultData;
    }

    public DefaultHttpResponse(String resultCode, String resultMessage, T resultData){
        this.resultCode = resultCode;
        this.resultData = resultData;
        this.resultMessage = resultMessage;
    }

    public DefaultHttpResponse(String resultCode, String resultMessage, String subResultMessage, T resultData){
        this.resultCode = resultCode;
        this.resultData = resultData;
        this.subResultMessage = subResultMessage;
        this.resultMessage = resultMessage;
    }
}



