package za.co.nico.rabbitmq.poc.queue.impl;

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
import za.co.nico.rabbitmq.poc.queue.QueueProducer;
import za.co.nico.rabbitmq.poc.utils.Utils;

@RunWith(SpringRunner.class)
public class QueueProducerManagerImplTest {

	@Test
	public void sendToMessageQueueServicePassingTest() {
		QueueProducer queueProducer = new MockQueueProducerImpl(TestType.PASSING_TEST);
		String expectedResponseStatusCode = ResponseStatusCodes.OK.getResponseStatusCode();
		String expectedResponseStatusMessage = ResponseStatusMessages.OK.getResponseStatusMessage();
		SendToQueueRequest request =Utils.makeSendToQueueTestRequest();
		request.setMessageId("dummy_message_id");
		String exchange2 = "dummy_exchange";
		String routingKey2 = "dummy_routing_key"; 
		QueueProducerManagerImpl queueProducerManagerImpl = new QueueProducerManagerImpl(exchange2, routingKey2, queueProducer);
		SendToQueueResponse response = queueProducerManagerImpl.sendToMessageQueueService(request);
		
		assertThat(response, is(notNullValue()));

		String responseStatusCode = response.getResponseStatusCode();
		String responseStatusMessage = response.getResponseStatusMessage();
		assertThat(responseStatusCode, is(expectedResponseStatusCode));
		assertThat(responseStatusMessage, is(expectedResponseStatusMessage));
	}

	@Test
	public void sendToMessageQueueServiceExceptionThrowingTest() {
		QueueProducer queueProducer = new MockQueueProducerImpl(TestType.THROWS_EXCEPTION);
		String expectedResponseStatusCode = ResponseStatusCodes.SYSTEM_FAILURE.getResponseStatusCode();
		String expectedResponseStatusMessage = ResponseStatusMessages.SYSTEM_FAILURE.getResponseStatusMessage();
		SendToQueueRequest request =Utils.makeSendToQueueTestRequest();
		request.setMessageId("dummy_message_id");
		String exchange2 = "dummy_exchange";
		String routingKey2 = "dummy_routing_key"; 
		QueueProducerManagerImpl queueProducerManagerImpl = new QueueProducerManagerImpl(exchange2, routingKey2, queueProducer);
		SendToQueueResponse response = queueProducerManagerImpl.sendToMessageQueueService(request);
		
		assertThat(response, is(notNullValue()));

		String responseStatusCode = response.getResponseStatusCode();
		String responseStatusMessage = response.getResponseStatusMessage();
		assertThat(responseStatusCode, is(expectedResponseStatusCode));
		assertThat(responseStatusMessage, is(expectedResponseStatusMessage));
	}
}
