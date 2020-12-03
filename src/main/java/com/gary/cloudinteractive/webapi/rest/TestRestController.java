package com.gary.cloudinteractive.webapi.rest;

import com.gary.cloudinteractive.webapi.model.Message;
import com.gary.cloudinteractive.webapi.model.Session;
import com.gary.cloudinteractive.webapi.redis.RedisService;
import com.gary.cloudinteractive.webapi.service.client.TestFeignService;
import com.gary.cloudinteractive.webapi.utils.LocaleUtil;
import com.gary.cloudinteractive.webapi.ws.ChetRoomSessionManager;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RestController
@Api(tags = {"測試用API"})
public class TestRestController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private TestFeignService testFeignService;


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

    @ApiOperation(("test03"))
    @RequestMapping(path = "/myws/{message}", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "resultCode(0000):成功\nresultCode(9999):未預期錯誤"),
            @ApiResponse(code = 500, message = "未預期錯誤")})
    public ResponseEntity<?> myWebSocketMessage(
            @ApiParam(required = true, value = "健檢編號(預約編號)", example = "111314520") @PathVariable("message") String message) throws IOException {
        log.debug("message: " + message);
        ChetRoomSessionManager.broadcast(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(("test04"))
    @RequestMapping(path = "/message", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "resultCode(0000):成功\nresultCode(9999):未預期錯誤"),
            @ApiResponse(code = 500, message = "未預期錯誤")})
    public ResponseEntity<?> getMessage(
            @Valid @RequestBody Message requestBdy) throws IOException {
        log.debug(requestBdy.toString());

        Message message = new Message();
        message.setMessageType(1);
        message.setMessage("測試Feign");
        message.setCount(2);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(("test05"))
    @RequestMapping(path = "/feign", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "resultCode(0000):成功\nresultCode(9999):未預期錯誤"),
            @ApiResponse(code = 500, message = "未預期錯誤")})
    public ResponseEntity<?> feignTest() throws IOException {

        Message message = new Message();
        message.setMessage("連線成功沒");

        ResponseEntity<Message> res = testFeignService.getAddPackage(message);
        final HttpStatus statusCode = res.getStatusCode();
        final Optional<Message> responseOp = Optional.ofNullable(res)
                .map(ResponseEntity::getBody);
        final Message response = responseOp.get();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(("測試i18n"))
    @RequestMapping(path = "/i18n", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "resultCode(0000):成功\nresultCode(9999):未預期錯誤"),
            @ApiResponse(code = 500, message = "未預期錯誤")})
    public ResponseEntity<?> testI18n(
            @ApiParam(value = "語系", example = "en_US") @RequestParam String lang) throws IOException {
        String message = LocaleUtil.get("user.appname");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(("測試i18n"))
    @RequestMapping(path = "/i18n", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "resultCode(0000):成功\nresultCode(9999):未預期錯誤"),
            @ApiResponse(code = 500, message = "未預期錯誤")})
    public ResponseEntity<?> testI18n(
            @ApiParam(value = "語系", example = "en_US") @RequestParam(required = false) String lang,
            @Valid @RequestBody Message requestBdy) throws IOException {
        String message = LocaleUtil.get("user.appname");
//        LocaleContextHolder.getLocale();
        log.debug(LocaleContextHolder.getLocale().toString());
        System.out.println(LocaleContextHolder.getLocale());

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
