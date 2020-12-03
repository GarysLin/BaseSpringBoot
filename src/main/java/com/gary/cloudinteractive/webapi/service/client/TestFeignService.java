package com.gary.cloudinteractive.webapi.service.client;

import com.gary.cloudinteractive.webapi.model.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8100", name = "test")
public interface TestFeignService {
    @PostMapping("/message")
    ResponseEntity<Message> getAddPackage(@RequestBody Message request);

}
