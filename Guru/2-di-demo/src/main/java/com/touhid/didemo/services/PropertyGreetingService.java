package com.touhid.didemo.services;

import org.springframework.stereotype.Service;

@Service
public class PropertyGreetingService implements GreetingService{

    @Override
    public String sayHello() {
        return "I was injected by property injection";
    }
}
