package com.example.demo.activities;

import com.example.demo.GreetingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoActivitiesImpl implements  DemoActivities {

    @Autowired
    GreetingBean greetingBean;

    @Override
    public String getGreeting(String name) {
        return greetingBean.getGreeting() + name;
    }
}
