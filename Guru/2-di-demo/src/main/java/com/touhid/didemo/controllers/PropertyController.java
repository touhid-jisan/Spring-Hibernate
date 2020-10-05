package com.touhid.didemo.controllers;

import com.touhid.didemo.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class PropertyController {


    @Autowired
    @Qualifier("propertyGreetingService")
    private GreetingService greetingService;

    public String sayHello() {
        return greetingService.sayHello();
    }


}
