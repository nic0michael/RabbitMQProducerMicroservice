package za.co.nico.rabbitmq.poc.services.impl;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusCodes;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusMessages;
import za.co.nico.rabbitmq.poc.enums.TestType;
import za.co.nico.rabbitmq.poc.services.MessageQueueService;

public class MockMessageQueueServiceImpl implements MessageQueueService{
	private TestType testType;
	private String responseStatusMessage;
	private String responseStatusCode;

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
			responseStatusCode=ResponseStatusCodes.OK.getResponseStatusCode();
			responseStatusMessage=ResponseStatusMessages.OK.getResponseStatusMessage();
			response=returnPassingResponse();
			break;
			
		case "FAILING_TEST":
			responseStatusCode=ResponseStatusCodes.MQ_FAILURE.getResponseStatusCode();
			responseStatusMessage=ResponseStatusMessages.MQ_FAILURE.getResponseStatusMessage();			
			response=returnFailingResponse();
			break;
			
		case "THROWS_EXCEPTION":			
			throw new Exception();

		default:
			response=returnFailingResponse();
		}

		return response;
	}

	private SendToQueueResponse returnFailingResponse() {

		SendToQueueResponse response=new SendToQueueResponse();
		response.setResponseStatusCode(responseStatusCode);
		response.setResponseStatusMessage(responseStatusMessage);
		response.setMessageDescription("dummy_value");
		response.setMessageId("dummy_value");
		response.setMessageType("dummy_value");
		response.setSenderId("dummy_value");
		response.setTransactionId("dummy_value");
		response.setTransactionType("dummy_value");
		response.setSenderSystemId("dummy_value");
		return response;
	}

	private SendToQueueResponse returnPassingResponse() {		

		SendToQueueResponse response=new SendToQueueResponse();
		response.setResponseStatusCode(responseStatusCode);
		response.setResponseStatusMessage(responseStatusMessage);
		response.setMessageDescription("dummy_value");
		response.setMessageId("dummy_value");
		response.setMessageType("dummy_value");
		response.setSenderId("dummy_value");
		response.setTransactionId("dummy_value");
		response.setTransactionType("dummy_value");
		response.setSenderSystemId("dummy_value");
		return response;
	}

}
