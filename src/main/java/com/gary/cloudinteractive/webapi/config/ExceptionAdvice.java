package com.gary.cloudinteractive.webapi.config;

import com.gary.cloudinteractive.webapi.model.response.DefaultHttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.SocketTimeoutException;
import java.util.List;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionAdvice {

//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(SocketTimeoutException.class)
//    public CustHttpResponse<?> handleSocketTimeout(SocketTimeoutException exception){
//        log.error(exception.getMessage(), exception);
//        return new CustHttpResponse<>("9998", "中台連線逾時", null);
//    }
//
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DefaultHttpResponse<?> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
        log.warn(exception.getMessage());
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        StringBuilder errMsg = new StringBuilder();
        for(FieldError f : fieldErrors) {
            errMsg.append(f.getField()).append(" ").append(f.getDefaultMessage()).append("; ");
        }

        return new DefaultHttpResponse<>("9997", "資料有誤", errMsg.toString(), null);
    }
}
