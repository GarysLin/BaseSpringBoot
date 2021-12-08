package com.gary.cloudinteractive.webapi.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class IndexController {

    @ApiIgnore
    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

}
