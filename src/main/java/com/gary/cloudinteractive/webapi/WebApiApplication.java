package com.gary.cloudinteractive.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@EnableFeignClients
@SpringBootApplication
@Controller
public class WebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApiApplication.class, args);
    }

    @ApiIgnore
    @RequestMapping("/swagger")
    public String greeting() {
        return "redirect:/swagger-ui/index.html";
    }
}
