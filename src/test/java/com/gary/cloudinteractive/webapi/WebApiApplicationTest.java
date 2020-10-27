package com.gary.cloudinteractive.webapi;

import com.gary.cloudinteractive.webapi.model.Session;
import com.gary.cloudinteractive.webapi.redis.RedisServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith( SpringJUnit4ClassRunner.class )
public class WebApiApplicationTest {
    private static final Logger logger = LoggerFactory.getLogger(WebApiApplicationTest.class);

    @Autowired
    private RedisServiceImpl redisService;

    @Test
    public void setSession() {
        logger.debug("redisService:" + redisService);
        Session user = new Session("01", "userName", "passWord");
        redisService.set("01", user);
    }

    @Test
    public void getSession() {
        logger.debug("redisService:" + redisService);
        Session session = (Session) redisService.get("01");
        logger.debug(session.toString());
    }

}
