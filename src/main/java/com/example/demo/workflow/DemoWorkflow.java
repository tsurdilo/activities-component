package com.example.demo.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface DemoWorkflow {
    @WorkflowMethod
    String sayHello(String name);
}
