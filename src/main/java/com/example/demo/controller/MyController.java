package com.example.demo.controller;

import com.example.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private MyService myService;

    @Autowired
    public MyController(MyService myService) {
        this.myService = myService;
    }

    @PostMapping("${test.url}")
    public Object test(@RequestBody String string) throws Exception {
        return myService.doSomething(string);
    }

    @PostMapping("test2")
    public Object test2(@RequestBody String string) {
        return myService.doSomething(string);
    }
}
