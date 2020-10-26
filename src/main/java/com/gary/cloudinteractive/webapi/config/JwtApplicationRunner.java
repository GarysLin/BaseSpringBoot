package com.gary.cloudinteractive.webapi.config;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.SigningKeyNotFoundException;
import com.auth0.jwk.UrlJwkProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;

@Slf4j
@Component
public class JwtApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws SigningKeyNotFoundException {

    }

}
