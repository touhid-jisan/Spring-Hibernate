package com.touhid.didemo.services;

import org.springframework.stereotype.Component;

@Component
public class GreetingRepositoryImpl implements GreetingRepository {

    @Override
    public String getEnglishGreeting() {
        return "Primary Greeting Service";
    }

    @Override
    public String getSpanishGreeting() {
        return "Spanish Greeting: Servicio de Saludo Primario";
    }

    @Override
    public String getGermanGreeting() {
        return "German Greeting: Primärer Grußdienst";
    }
}
