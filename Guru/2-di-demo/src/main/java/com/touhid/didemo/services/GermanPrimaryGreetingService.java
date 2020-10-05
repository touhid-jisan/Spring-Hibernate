package com.touhid.didemo.services;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("ge")
@Primary
public class GermanPrimaryGreetingService implements GreetingService{

    @Override
    public String sayHello() {
        return "I was injected by German profile";
    }
}
