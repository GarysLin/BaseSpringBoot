package com.gary.cloudinteractive.webapi.rest;

import com.alibaba.fastjson.JSONObject;
import com.gary.cloudinteractive.webapi.dao.ZipCodeMapper;
import com.gary.cloudinteractive.webapi.model.Message;
import com.gary.cloudinteractive.webapi.model.Session;
import com.gary.cloudinteractive.webapi.model.mybatis.ZipCode;
import com.gary.cloudinteractive.webapi.model.request.ZipCodeRequest;
import com.gary.cloudinteractive.webapi.redis.RedisService;
import com.gary.cloudinteractive.webapi.service.client.TestFeignService;
import com.gary.cloudinteractive.webapi.utils.*;
import com.gary.cloudinteractive.webapi.ws.ChetRoomSessionManager;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@Api(tags = {"測試用API"})
public class TestRestController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private TestFeignService testFeignService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ZipCodeMapper zipCodeMapper;


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

    @ApiOperation(("ChetRoom發送訊息"))
    @RequestMapping(path = "/myws/{message}", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "resultCode(0000):成功\nresultCode(9999):未預期錯誤"),
            @ApiResponse(code = 500, message = "未預期錯誤")})
    public ResponseEntity<?> myWebSocketMessage(
            @ApiParam(required = true, value = "發送訊息", example = "111314520") @PathVariable("message") String message) throws IOException {
        log.debug("message: " + message);
        ChetRoomSessionManager.broadcast(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(("測試Feign用偽API"))
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

    @ApiOperation(("測試Feign打到message"))
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

    @ApiOperation(("測試i18n讀取不同檔案"))
    @RequestMapping(path = "/i18n/doc", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "resultCode(0000):成功\nresultCode(9999):未預期錯誤"),
            @ApiResponse(code = 500, message = "未預期錯誤")})
    public ResponseEntity<?> testI18nDoc(
            @ApiParam(value = "語系", example = "en_US") @RequestParam(required = false) String lang) throws IOException {
        String message = LocaleUtil.get("user.appname");
//        LocaleContextHolder.getLocale();
        log.debug(LocaleContextHolder.getLocale().toString());
        System.out.println(LocaleContextHolder.getLocale());
        System.out.println(request.getLocale());

//        String doc = "zipCode.json";
//        JSONArray zipCode = FileUtil.loadJsonArrayFile(doc);
//        JsonArray zipCode = GsonFileUtil.loadJsonArrayFile(doc);
        String doc = "doc/w01question.json";
        JSONObject w01 = I18nFileUtil.loadJsonFile(doc);

        return new ResponseEntity<>(w01, HttpStatus.OK);
    }

    @ApiOperation(("測試mybatis-R"))
    @RequestMapping(path = "/mybatis", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "resultCode(0000):成功\nresultCode(9999):未預期錯誤"),
            @ApiResponse(code = 500, message = "未預期錯誤")})
    public ResponseEntity<?> mybatisTest() throws IOException {
        List<ZipCode> data = zipCodeMapper.getAllModel();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @ApiOperation(("測試mybatis-R2"))
    @RequestMapping(path = "/mybatis/{id}", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = ZipCode.class,
                    message = "resultCode(0000):成功\nresultCode(9999):未預期錯誤"),
            @ApiResponse(code = 500, message = "未預期錯誤")})
    public ResponseEntity<?> mybatisTestOne(
            @ApiParam(value = "id", example = "1") @PathVariable("id") int id ) throws IOException {
        ZipCode data = zipCodeMapper.getOne(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @Transactional
    @ApiOperation(("測試mybatis-C"))
    @RequestMapping(path = "/mybatis", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "resultCode(0000):成功\nresultCode(9999):未預期錯誤"),
            @ApiResponse(code = 500, message = "未預期錯誤")})
    public ResponseEntity<?> mybatisTest_c(@Valid @RequestBody ZipCodeRequest requestBdy) throws IOException {
        JSONObject param = (JSONObject) JSONObject.toJSON(requestBdy);
        System.out.println(param);
        int data = zipCodeMapper.insert(param);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
