package com.gary.cloudinteractive.webapi;

import com.gary.cloudinteractive.webapi.model.Session;
import com.gary.cloudinteractive.webapi.redis.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WebApiApplicationTest {
    private static final Logger logger = LoggerFactory.getLogger(WebApiApplicationTest.class);

    @Autowired
    private RedisService redisService;

    @Test
    @Order(1)
    public void setSession() {
        logger.debug("redisService:" + redisService);
        Session user = new Session("01", "userName", "passWord");
        redisService.set("01", user);
//        Assert.assertThat(redisService.set("01", user) ,is("嘟嘟MD獨立博客"));
        Assert.assertTrue(true);
    }

    @Test
    @Order(2)
    public void getSession() {
        logger.debug("redisService:" + redisService);
        Session session = (Session) redisService.get("01");
        logger.debug(session.toString());
        System.out.println(session.toString());
        Assert.assertThat(session.toString() ,is("Session(sessionId=01, accessToken=userName, refreshToken=passWord)"));
//        Assert.assertTrue(true);
    }

}
