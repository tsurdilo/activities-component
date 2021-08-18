package com.example.demo;

import com.example.demo.activities.DemoActivitiesImpl;
import com.example.demo.workflow.DemoWorkflow;
import com.example.demo.workflow.DemoWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoApplication {

	public static final String TASK_QUEUE = "demotaskqueue";
	private static final WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
	private static final WorkflowClient client = WorkflowClient.newInstance(service);
	private static final WorkerFactory factory = WorkerFactory.newInstance(client);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	DemoActivitiesImpl demoActivities;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Worker worker = factory.newWorker(TASK_QUEUE);
		worker.registerWorkflowImplementationTypes(DemoWorkflowImpl.class);
		worker.registerActivitiesImplementations(demoActivities);

		factory.start();

		// run the workflow and print results...
		DemoWorkflow workflow =
				client.newWorkflowStub(
						DemoWorkflow.class,
						WorkflowOptions.newBuilder()
								.setWorkflowId("demoworkflow")
								.setTaskQueue(TASK_QUEUE)
								.build());

		// async if you want to test
		//WorkflowClient.start(workflow::sayHello, "John");

		// sync
		System.out.println("********** Workflow result: " + workflow.sayHello("John"));
	}

}
