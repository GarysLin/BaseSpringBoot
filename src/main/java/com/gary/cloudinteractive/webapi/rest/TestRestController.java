package com.gary.cloudinteractive.webapi.rest;

import com.gary.cloudinteractive.webapi.model.Session;
import com.gary.cloudinteractive.webapi.redis.RedisService;
import com.gary.cloudinteractive.webapi.redis.RedisServiceImpl;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@Api(tags = {"測試用API"})
public class TestRestController {

    @Autowired
    private RedisService redisService;

    @ApiOperation(("測試01"))
    @RequestMapping(path = "/test/redis", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "resultCode(0000):成功\nresultCode(9999):未預期錯誤"),
            @ApiResponse(code = 500, message = "未預期錯誤")})
    public ResponseEntity<?> getW01Questionnaire() throws IOException {
        log.debug("");
        Session user = new Session("02", "userName", "passWord");
        redisService.set("02", user, (long) 1800);
        return new ResponseEntity<>("Set", HttpStatus.OK);
    }

    @ApiOperation(("測試02"))
    @RequestMapping(path = "/test/redis", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "resultCode(0000):成功\nresultCode(9999):未預期錯誤"),
            @ApiResponse(code = 500, message = "未預期錯誤")})
    public ResponseEntity<?> getRedis(
            @ApiParam(required = true, value = "01", example = "01") @RequestParam String id) throws IOException {
        log.debug("id: " + id);
        Session session = (Session) redisService.get(id, (long) 1800);
        log.debug("session: " + session);
        return new ResponseEntity<>(session, HttpStatus.OK);
    }
}
