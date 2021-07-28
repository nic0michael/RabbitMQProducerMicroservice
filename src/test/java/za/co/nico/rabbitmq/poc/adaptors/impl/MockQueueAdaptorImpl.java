package za.co.nico.rabbitmq.poc.adaptors.impl;

import za.co.nico.rabbitmq.poc.adaptors.Queue;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.enums.TestType;

/**
 * 
 * @author nickm
 * This is based on :
 *   
 * Dave Farley's : When TDD is Difficult - Try This! ](https://www.youtube.com/watch?v=ESHn53myB88&pp=sAQA)
 * https://www.youtube.com/watch?v=ESHn53myB88&pp=sAQA
 *
 */
public class MockQueueAdaptorImpl implements Queue{
	private TestType testType;
	
	private MockQueueAdaptorImpl() {}
	
	public MockQueueAdaptorImpl(TestType testType) {
		this.testType = testType;
	}

	@Override
	public SendToQueueResponse send(SendToQueueRequest request) {
		return null;
	}

	@Override
	public SendToQueueResponse receive() {
		return null;
	}

	@Override
	public SendToQueueResponse receive(String searchValue) {
		return null;
	}

}
