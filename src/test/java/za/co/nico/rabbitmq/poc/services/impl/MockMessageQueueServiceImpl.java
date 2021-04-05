package za.co.nico.rabbitmq.poc.services.impl;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusCodes;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusMessages;
import za.co.nico.rabbitmq.poc.enums.TestType;
import za.co.nico.rabbitmq.poc.services.MessageQueueService;
import za.co.nico.rabbitmq.poc.utils.Utils;

public class MockMessageQueueServiceImpl implements MessageQueueService{
	private TestType testType;

	private MockMessageQueueServiceImpl() {}
	
	public MockMessageQueueServiceImpl(TestType testType) {
		this.testType=testType;
	}
	
	@Override
	public SendToQueueResponse sendToMessageQueueService(SendToQueueRequest request) throws Exception {
		String operationType=testType.name();
		SendToQueueResponse response=null;
		
		switch (operationType) {
		case "PASSING_TEST":
			response=Utils.makePassingTestResponse();
			break;
			
		case "FAILING_TEST":		
			response=Utils.makeMqFailureTestResponse();
			break;
			
		case "THROWS_EXCEPTION":			
			throw new Exception();

		default:
			response=Utils.makeMqFailureTestResponse();
		}

		return response;
	}


}
