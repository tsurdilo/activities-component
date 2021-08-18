package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class GreetingBean {
    private String greeting = "Hello ";

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
