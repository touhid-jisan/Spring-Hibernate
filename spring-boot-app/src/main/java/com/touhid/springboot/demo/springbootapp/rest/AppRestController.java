package com.touhid.springboot.demo.springbootapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
//@RequestMapping("/api")
public class AppRestController {

    //@GetMapping("/hello")
    @GetMapping("/")
    public String sayHello () {
        return "Hello world!!!, Time on Server is" + LocalDateTime.now();
    }
}
