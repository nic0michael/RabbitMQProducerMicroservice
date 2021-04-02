package za.co.nico.rabbitmq.poc.validators;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusCodes;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusMessages;

@RunWith(SpringRunner.class)
public class RequestValidatorTest {
	
	RequestValidator validator=null;
	

	@Test
	public void goodRequestTest() {
		if(validator==null) {
			validator=new RequestValidator();
		}
		String expectedResponseStatusCode=ResponseStatusCodes.OK.getResponseStatusCode();
		String expectedResponseStatusMessage=ResponseStatusMessages.OK.getResponseStatusMessage();
		
		SendToQueueRequest request =makeSendToQueueRequest();
		SendToQueueResponse response = validator.validateSendToQueueRequest(request);
		assertThat( response,is(notNullValue()));
		
		String responseStatusCode=response.getResponseStatusCode();
		String responseStatusMessage=response.getResponseStatusMessage();
		assertThat(responseStatusCode,is(expectedResponseStatusCode));
		assertThat(responseStatusMessage,is(expectedResponseStatusMessage));
	}

	@Test
	public void badRequestTest() {
		if(validator==null) {
			validator=new RequestValidator();
		}
		String expectedResponseStatusCode=ResponseStatusCodes.BAD_REQUEST.getResponseStatusCode();
		String expectedResponseStatusMessage=ResponseStatusMessages.BAD_REQUEST.getResponseStatusMessage();
		
		SendToQueueRequest request =null;
		SendToQueueResponse response = validator.validateSendToQueueRequest(request);
		assertThat( response,is(notNullValue()));
		
		String responseStatusCode=response.getResponseStatusCode();
		String responseStatusMessage=response.getResponseStatusMessage();
		assertThat(responseStatusCode,is(expectedResponseStatusCode));
		assertThat(responseStatusMessage,is(expectedResponseStatusMessage));
	}

	@Test
	public void missingMessageRequestTest() {
		if(validator==null) {
			validator=new RequestValidator();
		}
		String expectedResponseStatusCode=ResponseStatusCodes.MISSING_MESSAGE_ID.getResponseStatusCode();
		String expectedResponseStatusMessage=ResponseStatusMessages.MISSING_MESSAGE_ID.getResponseStatusMessage();
		
		SendToQueueRequest request=makeSendToQueueRequest();
		request.setMessageId(null);
		SendToQueueResponse response = validator.validateSendToQueueRequest(request);
		assertThat( response,is(notNullValue()));
		
		String responseStatusCode=response.getResponseStatusCode();
		String responseStatusMessage=response.getResponseStatusMessage();
		assertThat(responseStatusCode,is(expectedResponseStatusCode));
		assertThat(responseStatusMessage,is(expectedResponseStatusMessage));
		

	}

	@Test
	public void missingMessageTypeRequestTest() {
		if(validator==null) {
			validator=new RequestValidator();
		}
		String expectedResponseStatusCode=ResponseStatusCodes.MISSING_MESSAGE_TYPE.getResponseStatusCode();
		String expectedResponseStatusMessage=ResponseStatusMessages.MISSING_MESSAGE_TYPE.getResponseStatusMessage();
		
		SendToQueueRequest request=makeSendToQueueRequest();
		request.setMessageType(null);
		SendToQueueResponse response = validator.validateSendToQueueRequest(request);
		assertThat( response,is(notNullValue()));
		
		String responseStatusCode=response.getResponseStatusCode();
		String responseStatusMessage=response.getResponseStatusMessage();
		assertThat(responseStatusCode,is(expectedResponseStatusCode));
		assertThat(responseStatusMessage,is(expectedResponseStatusMessage));

	}

	@Test
	public void missingSenderSystemIdRequestTest() {
		if(validator==null) {
			validator=new RequestValidator();
		}
		String expectedResponseStatusCode=ResponseStatusCodes.MISSING_SENDER_SYSTEM_ID.getResponseStatusCode();
		String expectedResponseStatusMessage=ResponseStatusMessages.MISSING_SENDER_SYSTEM_ID.getResponseStatusMessage();
		
		SendToQueueRequest request=makeSendToQueueRequest();
		request.setSenderSystemId(null);
		
		SendToQueueResponse response = validator.validateSendToQueueRequest(request);
		assertThat( response,is(notNullValue()));
		
		String responseStatusCode=response.getResponseStatusCode();
		String responseStatusMessage=response.getResponseStatusMessage();
		assertThat(responseStatusCode,is(expectedResponseStatusCode));
		assertThat(responseStatusMessage,is(expectedResponseStatusMessage));

	}

	@Test
	public void missingTransactionRequestTest() {
		if(validator==null) {
			validator=new RequestValidator();
		}
		String expectedResponseStatusCode=ResponseStatusCodes.MISSING_TRANSACTION.getResponseStatusCode();
		String expectedResponseStatusMessage=ResponseStatusMessages.MISSING_TRANSACTION.getResponseStatusMessage();
		
		SendToQueueRequest request=makeSendToQueueRequest();
		request.setTransaction(null);
		
		SendToQueueResponse response = validator.validateSendToQueueRequest(request);
		assertThat( response,is(notNullValue()));
		
		String responseStatusCode=response.getResponseStatusCode();
		String responseStatusMessage=response.getResponseStatusMessage();
		assertThat(responseStatusCode,is(expectedResponseStatusCode));
		assertThat(responseStatusMessage,is(expectedResponseStatusMessage));

	}

	@Test
	public void missingTransactionIdRequestTest() {
		if(validator==null) {
			validator=new RequestValidator();
		}
		String expectedResponseStatusCode=ResponseStatusCodes.MISSING_TRANSACTION_ID.getResponseStatusCode();
		String expectedResponseStatusMessage=ResponseStatusMessages.MISSING_TRANSACTION_ID.getResponseStatusMessage();
		
		SendToQueueRequest request=makeSendToQueueRequest();
		request.setTransactionId(null);
		
		SendToQueueResponse response = validator.validateSendToQueueRequest(request);
		assertThat( response,is(notNullValue()));
		
		String responseStatusCode=response.getResponseStatusCode();
		String responseStatusMessage=response.getResponseStatusMessage();
		assertThat(responseStatusCode,is(expectedResponseStatusCode));
		assertThat(responseStatusMessage,is(expectedResponseStatusMessage));

	}

	@Test
	public void missingTransactionTypeRequestTest() {
		if(validator==null) {
			validator=new RequestValidator();
		}
		String expectedResponseStatusCode=ResponseStatusCodes.MISSING_TRANSACTION_TYPE.getResponseStatusCode();
		String expectedResponseStatusMessage=ResponseStatusMessages.MISSING_TRANSACTION_TYPE.getResponseStatusMessage();
		
		SendToQueueRequest request=makeSendToQueueRequest();
		request.setTransactionType(null);
		
		SendToQueueResponse response = validator.validateSendToQueueRequest(request);
		assertThat( response,is(notNullValue()));
		
		String responseStatusCode=response.getResponseStatusCode();
		String responseStatusMessage=response.getResponseStatusMessage();
		assertThat(responseStatusCode,is(expectedResponseStatusCode));
		assertThat(responseStatusMessage,is(expectedResponseStatusMessage));

	}
	
	private SendToQueueRequest makeSendToQueueRequest() {
		SendToQueueRequest request =new SendToQueueRequest();
		request.setMessageDescription("dummy_value");
		request.setMessageId("dummy_value");
		request.setMessageType("dummy_value");
		request.setSenderId("dummy_value");
		request.setTransaction("dummy_value");
		request.setTransactionId("dummy_value");
		request.setTransactionType("dummy_value");
		request.setSenderSystemId("dummy_value");
		return request;
	}
}
