package za.co.nico.rabbitmq.poc.queue.impl;

import java.net.ConnectException;

import org.springframework.amqp.AmqpConnectException;

import za.co.nico.rabbitmq.poc.enums.TestType;
import za.co.nico.rabbitmq.poc.queue.QueueProducer;

public class MockQueueProducerImpl implements QueueProducer {
	private TestType testType;

	private MockQueueProducerImpl() {}
	
	public MockQueueProducerImpl(TestType testType) {
		this.testType=testType;
	}
	
	@Override
	public void sendToInputQueue(String exchange2, String routingKey2, String json)
			throws AmqpConnectException, ConnectException , Exception{

		String operationType=testType.name();
		
		switch (operationType) {
		case "PASSING_TEST":
			break;
			
		case "FAILING_TEST":	
			break;
			
		case "THROWS_EXCEPTION":			
			throw new Exception();		
		}
	}

}
