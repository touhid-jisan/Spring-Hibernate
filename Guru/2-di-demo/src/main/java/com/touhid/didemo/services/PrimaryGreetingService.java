package com.touhid.didemo.services;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"en", "default"})
@Primary
public class PrimaryGreetingService implements GreetingService{

    @Override
    public String sayHello() {
        return "I was injected by Primary En/Default Profile greetings";
    }
}
