package com.touhid.didemo.services;

import org.springframework.stereotype.Service;

@Service
public class SetterGreetingService implements GreetingService {
    @Override
    public String sayHello() {
        return "I was injected by the Setter";
    }
}
