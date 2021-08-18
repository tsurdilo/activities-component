package com.example.demo.activities;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface DemoActivities {
    String getGreeting(String name);
}
