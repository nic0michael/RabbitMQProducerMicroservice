package za.co.nico.rabbitmq.poc.services.impl;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusCodes;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusMessages;
import za.co.nico.rabbitmq.poc.enums.TestType;
import za.co.nico.rabbitmq.poc.exceptions.FailedToSendToQueueException;
import za.co.nico.rabbitmq.poc.services.MessageQueueSendService;

public class MockMessageQueueSendServiceImpl implements MessageQueueSendService{
	private TestType testType;

	private MockMessageQueueSendServiceImpl() {}
	
	public MockMessageQueueSendServiceImpl(TestType testType) {
		this.testType=testType;
	}
	
	@Override
	public SendToQueueResponse sendToMessageQueue(String json) throws FailedToSendToQueueException {
		String operationType=testType.name();
		SendToQueueResponse response=null;
		
		switch (operationType) {
		case "PASSING_TEST":
			response=makePassingTestResponse();
			break;
			
		case "FAILING_TEST":		
			response=makeMqFailureTestResponse();
			break;
			
		case "THROWS_EXCEPTION":			
			throw new FailedToSendToQueueException();

		default:
			response=makeMqFailureTestResponse();
		}

		return response;
	}

	public static SendToQueueResponse makePassingTestResponse() {		

		SendToQueueResponse response = new SendToQueueResponse();
		response.setMessageDescription("dummy_value");
		response.setMessageType("dummy_value");
		response.setSenderId("dummy_value");
		response.setTransactionId("dummy_value");
		response.setTransactionType("dummy_value");
		response.setSenderSystemId("dummy_value");
		String responseStatusCode=ResponseStatusCodes.OK.getResponseStatusCode();
		String responseStatusMessage=ResponseStatusMessages.OK.getResponseStatusMessage();
		response.setResponseStatusCode(responseStatusCode);
		response.setResponseStatusMessage(responseStatusMessage);
		return response;
	}

	public static SendToQueueResponse makeMqFailureTestResponse() {

		SendToQueueResponse response = new SendToQueueResponse();
		String responseStatusCode=ResponseStatusCodes.BAD_REQUEST.getResponseStatusCode();
		String responseStatusMessage=ResponseStatusMessages.BAD_REQUEST.getResponseStatusMessage();	
		response.setResponseStatusCode(responseStatusCode);
		response.setResponseStatusMessage(responseStatusMessage);
		return response;
	}


}
