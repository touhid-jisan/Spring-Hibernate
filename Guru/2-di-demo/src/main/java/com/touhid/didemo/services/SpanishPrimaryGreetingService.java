package com.touhid.didemo.services;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("ban")
@Primary
public class SpanishPrimaryGreetingService implements GreetingService{

    @Override
    public String sayHello() {
        return "I was injected by Spanish profile";
    }
}
