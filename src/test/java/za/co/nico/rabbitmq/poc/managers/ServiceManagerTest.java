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
import za.co.nico.rabbitmq.poc.exceptions.FailedToWriteToDatabaseException;
import za.co.nico.rabbitmq.poc.services.DatabaseService;
import za.co.nico.rabbitmq.poc.services.MessageQueueSendService;
import za.co.nico.rabbitmq.poc.services.impl.MockDatabaseServiceImpl;
import za.co.nico.rabbitmq.poc.services.impl.MockMessageQueueSendServiceImpl;

@RunWith(SpringRunner.class)
public class ServiceManagerTest {

	@Test
	public void sendToMessageQueuePassingTest() {
		DatabaseService databaseService = new MockDatabaseServiceImpl(TestType.PASSING_TEST);
		MessageQueueSendService messageQueueService = new MockMessageQueueSendServiceImpl(TestType.PASSING_TEST);
		String expectedResponseStatusCode = ResponseStatusCodes.OK.getResponseStatusCode();
		String expectedResponseStatusMessage = ResponseStatusMessages.OK.getResponseStatusMessage();

		ServiceManager manager = new ServiceManager(messageQueueService, databaseService);
		SendToQueueRequest request = makeSendToQueueRequest();
		SendToQueueResponse response = manager.sendToMessageQueue(request);

		assertThat(response, is(notNullValue()));

		String responseStatusCode = response.getResponseStatusCode();
		String responseStatusMessage = response.getResponseStatusMessage();
		assertThat(responseStatusCode, is(expectedResponseStatusCode));
		assertThat(responseStatusMessage, is(expectedResponseStatusMessage));

	}

	@Test
	public void sendToMessageQueueFailingTest() {
		DatabaseService databaseService = new MockDatabaseServiceImpl(TestType.PASSING_TEST);
		MessageQueueSendService messageQueueService = new MockMessageQueueSendServiceImpl(TestType.FAILING_TEST);
		String expectedResponseStatusCode = ResponseStatusCodes.BAD_REQUEST.getResponseStatusCode();
		String expectedResponseStatusMessage = ResponseStatusMessages.BAD_REQUEST.getResponseStatusMessage();

		ServiceManager manager = new ServiceManager(messageQueueService, databaseService);
		SendToQueueRequest request = makeSendToQueueRequest();
		SendToQueueResponse response = manager.sendToMessageQueue(request);

		assertThat(response, is(notNullValue()));

		String responseStatusCode = response.getResponseStatusCode();
		String responseStatusMessage = response.getResponseStatusMessage();
		assertThat(responseStatusCode, is(expectedResponseStatusCode));
		assertThat(responseStatusMessage, is(expectedResponseStatusMessage));

	}

	@Test
	public void sendToMessageQueueExceptionThrowingTest() {
		DatabaseService databaseService = new MockDatabaseServiceImpl(TestType.PASSING_TEST);
		MessageQueueSendService messageQueueService = new MockMessageQueueSendServiceImpl(TestType.THROWS_EXCEPTION);
		String expectedResponseStatusCode = ResponseStatusCodes.MQ_FAILURE.getResponseStatusCode();
		String expectedResponseStatusMessage = ResponseStatusMessages.MQ_FAILURE.getResponseStatusMessage();

		ServiceManager manager = new ServiceManager(messageQueueService, databaseService);
		SendToQueueRequest request = makeSendToQueueRequest();
		SendToQueueResponse response = manager.sendToMessageQueue(request);

		assertThat(response, is(notNullValue()));

		String responseStatusCode = response.getResponseStatusCode();
		String responseStatusMessage = response.getResponseStatusMessage();
		assertThat(responseStatusCode, is(expectedResponseStatusCode));
		assertThat(responseStatusMessage, is(expectedResponseStatusMessage));

	}

	@Test
	public void InsertToDatabasePassingTest() throws FailedToWriteToDatabaseException {
		DatabaseService databaseService = new MockDatabaseServiceImpl(TestType.PASSING_TEST);
		MessageQueueSendService messageQueueService = new MockMessageQueueSendServiceImpl(TestType.PASSING_TEST);

		ServiceManager manager = new ServiceManager(messageQueueService, databaseService);
		SendToQueueRequest request = makeSendToQueueRequest();

		boolean testPasses = false;
		SendToQueueResponse response = null;
		manager.writeToDatabase(request, response);
		testPasses = true;

		assertThat(testPasses, is(true));
	}
	
	

	@Test
	public void updateToDatabasePassingTest() throws FailedToWriteToDatabaseException {
		DatabaseService databaseService = new MockDatabaseServiceImpl(TestType.PASSING_TEST);
		MessageQueueSendService messageQueueService = new MockMessageQueueSendServiceImpl(TestType.PASSING_TEST);

		ServiceManager manager = new ServiceManager(messageQueueService, databaseService);
		SendToQueueRequest request = makeSendToQueueRequest();

		boolean testPasses = false;
		try {
			SendToQueueResponse response = new SendToQueueResponse();
			manager.writeToDatabase(request, response);
			testPasses = true;
		} catch (FailedToWriteToDatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertThat(testPasses, is(true));
	}
	


	@Test
	public void sendToMessageQueueWithDatabaseExceptionThrowingTest() {
		DatabaseService databaseService = new MockDatabaseServiceImpl(TestType.THROWS_EXCEPTION);
		MessageQueueSendService messageQueueService = new MockMessageQueueSendServiceImpl(TestType.PASSING_TEST);
		String expectedResponseStatusCode = ResponseStatusCodes.DATABASE_FAILURE.getResponseStatusCode();
		String expectedResponseStatusMessage = ResponseStatusMessages.DATABASE_FAILURE.getResponseStatusMessage();

		ServiceManager manager = new ServiceManager(messageQueueService, databaseService);
		SendToQueueRequest request = makeSendToQueueRequest();
		SendToQueueResponse response = manager.sendToMessageQueue(request);

		assertThat(response, is(notNullValue()));

		String responseStatusCode = response.getResponseStatusCode();
		String responseStatusMessage = response.getResponseStatusMessage();
		assertThat(responseStatusCode, is(expectedResponseStatusCode));
		assertThat(responseStatusMessage, is(expectedResponseStatusMessage));

	}

	@Test(expected = FailedToWriteToDatabaseException.class)
	public void InsertToDatabaseExceptionThrowingTest() throws FailedToWriteToDatabaseException {
		DatabaseService databaseService = new MockDatabaseServiceImpl(TestType.THROWS_EXCEPTION);
		MessageQueueSendService messageQueueService = new MockMessageQueueSendServiceImpl(TestType.PASSING_TEST);

		ServiceManager manager = new ServiceManager(messageQueueService, databaseService);
		SendToQueueRequest request = makeSendToQueueRequest();
		SendToQueueResponse response = null;
		
		manager.writeToDatabase(request, response);

	}
	


	@Test(expected = FailedToWriteToDatabaseException.class)
	public void x() throws FailedToWriteToDatabaseException {
		DatabaseService databaseService = new MockDatabaseServiceImpl(TestType.THROWS_EXCEPTION);
		MessageQueueSendService messageQueueService = new MockMessageQueueSendServiceImpl(TestType.PASSING_TEST);

		ServiceManager manager = new ServiceManager(messageQueueService, databaseService);
		SendToQueueRequest request = makeSendToQueueRequest();

		SendToQueueResponse response = new SendToQueueResponse();
		manager.writeToDatabase(request, response);

	}

	@Test(expected = FailedToWriteToDatabaseException.class)
	public void updateToDatabaseExceptionThrowingTest() throws FailedToWriteToDatabaseException {
		DatabaseService databaseService = new MockDatabaseServiceImpl(TestType.THROWS_EXCEPTION);
		MessageQueueSendService messageQueueService = new MockMessageQueueSendServiceImpl(TestType.PASSING_TEST);

		ServiceManager manager = new ServiceManager(messageQueueService, databaseService);
		SendToQueueRequest request = makeSendToQueueRequest();

		SendToQueueResponse response = new SendToQueueResponse();
		manager.writeToDatabase(request, response);

	}

	private SendToQueueRequest makeSendToQueueRequest() {
		SendToQueueRequest request = new SendToQueueRequest();
		request.setMessageDescription("dummy_value");
		request.setMessageType("dummy_value");
		request.setTransaction("dummy_value");
		request.setTransactionId("dummy_value");
		request.setTransactionType("dummy_value");
		request.setSenderSystemId("dummy_value");
		return request;
	}
}
