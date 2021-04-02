package za.co.nico.rabbitmq.poc.managers;

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
import za.co.nico.rabbitmq.poc.enums.TestType;
import za.co.nico.rabbitmq.poc.services.MessageQueueService;
import za.co.nico.rabbitmq.poc.services.impl.MockMessageQueueServiceImpl;

@RunWith(SpringRunner.class)
public class ServiceManagerTest {

	@Test
	public void passingTest() {
		MessageQueueService messageQueueService = new MockMessageQueueServiceImpl(TestType.PASSING_TEST);
		String expectedResponseStatusCode = ResponseStatusCodes.OK.getResponseStatusCode();
		String expectedResponseStatusMessage = ResponseStatusMessages.OK.getResponseStatusMessage();

		ServiceManager manager = new ServiceManager(messageQueueService);
		SendToQueueRequest request = makeSendToQueueRequest();
		SendToQueueResponse response = manager.sendToMessageQueue(request);

		assertThat(response, is(notNullValue()));

		String responseStatusCode = response.getResponseStatusCode();
		String responseStatusMessage = response.getResponseStatusMessage();
		assertThat(responseStatusCode, is(expectedResponseStatusCode));
		assertThat(responseStatusMessage, is(expectedResponseStatusMessage));

	}
	
	@Test
	public void failingTest() {
		MessageQueueService messageQueueService = new MockMessageQueueServiceImpl(TestType.FAILING_TEST);
		String expectedResponseStatusCode = ResponseStatusCodes.MQ_FAILURE.getResponseStatusCode();
		String expectedResponseStatusMessage = ResponseStatusMessages.MQ_FAILURE.getResponseStatusMessage();

		ServiceManager manager = new ServiceManager(messageQueueService);
		SendToQueueRequest request = makeSendToQueueRequest();
		SendToQueueResponse response = manager.sendToMessageQueue(request);

		assertThat(response, is(notNullValue()));

		String responseStatusCode = response.getResponseStatusCode();
		String responseStatusMessage = response.getResponseStatusMessage();
		assertThat(responseStatusCode, is(expectedResponseStatusCode));
		assertThat(responseStatusMessage, is(expectedResponseStatusMessage));

	}

	
	@Test
	public void exceptionThrowingTest() {
		MessageQueueService messageQueueService = new MockMessageQueueServiceImpl(TestType.THROWS_EXCEPTION);
		String expectedResponseStatusCode = ResponseStatusCodes.SYSTEM_FAILURE.getResponseStatusCode();
		String expectedResponseStatusMessage = ResponseStatusMessages.SYSTEM_FAILURE.getResponseStatusMessage();

		ServiceManager manager = new ServiceManager(messageQueueService);
		SendToQueueRequest request = makeSendToQueueRequest();
		SendToQueueResponse response = manager.sendToMessageQueue(request);

		assertThat(response, is(notNullValue()));

		String responseStatusCode = response.getResponseStatusCode();
		String responseStatusMessage = response.getResponseStatusMessage();
		assertThat(responseStatusCode, is(expectedResponseStatusCode));
		assertThat(responseStatusMessage, is(expectedResponseStatusMessage));

	}

	private SendToQueueRequest makeSendToQueueRequest() {
		SendToQueueRequest request = new SendToQueueRequest();
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
