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
        return "Hello world!!!, Tim Server is" + LocalDateTime.now();
    }

    @GetMapping("/check")
    public String check() {
        return "check";
    }
}
