package com.touhid.didemo.services;

import org.springframework.stereotype.Service;

@Service
public class ConstructorGreetingService implements GreetingService {

    @Override
    public String sayHello() {
        return "i WAS injected by constructor greetingService";
    }
}
