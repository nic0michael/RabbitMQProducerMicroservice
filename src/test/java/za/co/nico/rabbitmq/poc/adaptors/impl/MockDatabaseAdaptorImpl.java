package za.co.nico.rabbitmq.poc.adaptors.impl;

import za.co.nico.rabbitmq.poc.adaptors.Database;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.enums.TestType;

/**
 * 
 * @author nickm
 *
 * This is based on :
 *   
 * Dave Farley's : When TDD is Difficult - Try This! ](https://www.youtube.com/watch?v=ESHn53myB88&pp=sAQA)
 * https://www.youtube.com/watch?v=ESHn53myB88&pp=sAQA
 * 
 */
public class MockDatabaseAdaptorImpl implements Database {
	private TestType testType;
	
	private MockDatabaseAdaptorImpl() {}
	
	public MockDatabaseAdaptorImpl(TestType testType) {
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
