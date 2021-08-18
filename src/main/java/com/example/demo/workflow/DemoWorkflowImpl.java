package com.example.demo.workflow;

import com.example.demo.activities.DemoActivities;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class DemoWorkflowImpl implements DemoWorkflow {

    private final DemoActivities activities =
            Workflow.newActivityStub(
                    DemoActivities.class,
                    ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build());


    @Override
    public String sayHello(String name) {
        return activities.getGreeting(name);
    }
}
