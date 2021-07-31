package za.co.nico.rabbitmq.poc.business.logic;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import za.co.nico.rabbitmq.poc.adaptors.DatabaseAdaptor;
import za.co.nico.rabbitmq.poc.adaptors.MessageQueueAdaptor;
import za.co.nico.rabbitmq.poc.business.logic.BusinessLogicProcessor;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusCodes;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusMessages;
import za.co.nico.rabbitmq.poc.enums.TestType;
import za.co.nico.rabbitmq.poc.exceptions.FailedToWriteToDatabaseException;
import za.co.nico.rabbitmq.poc.services.impl.MockDatabaseAdaptorServiceImpl;
import za.co.nico.rabbitmq.poc.services.impl.MockMessageQueueAdaptorServiceImpl;

@RunWith(SpringRunner.class)
public class BusinessLogicProcessorTest {

	@Test
	public void sendToMessageQueuePassingTest() {
		DatabaseAdaptor databaseService = new MockDatabaseAdaptorServiceImpl(TestType.PASSING_TEST);
		MessageQueueAdaptor messageQueueService = new MockMessageQueueAdaptorServiceImpl(TestType.PASSING_TEST);
		String expectedResponseStatusCode = ResponseStatusCodes.OK.getResponseStatusCode();
		String expectedResponseStatusMessage = ResponseStatusMessages.OK.getResponseStatusMessage();

		BusinessLogicProcessor manager = new BusinessLogicProcessor(messageQueueService, databaseService);
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
		DatabaseAdaptor databaseService = new MockDatabaseAdaptorServiceImpl(TestType.PASSING_TEST);
		MessageQueueAdaptor messageQueueService = new MockMessageQueueAdaptorServiceImpl(TestType.FAILING_TEST);
		String expectedResponseStatusCode = ResponseStatusCodes.BAD_REQUEST.getResponseStatusCode();
		String expectedResponseStatusMessage = ResponseStatusMessages.BAD_REQUEST.getResponseStatusMessage();

		BusinessLogicProcessor manager = new BusinessLogicProcessor(messageQueueService, databaseService);
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
		DatabaseAdaptor databaseService = new MockDatabaseAdaptorServiceImpl(TestType.PASSING_TEST);
		MessageQueueAdaptor messageQueueService = new MockMessageQueueAdaptorServiceImpl(TestType.THROWS_EXCEPTION);
		String expectedResponseStatusCode = ResponseStatusCodes.MQ_FAILURE.getResponseStatusCode();
		String expectedResponseStatusMessage = ResponseStatusMessages.MQ_FAILURE.getResponseStatusMessage();

		BusinessLogicProcessor manager = new BusinessLogicProcessor(messageQueueService, databaseService);
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
		DatabaseAdaptor databaseService = new MockDatabaseAdaptorServiceImpl(TestType.PASSING_TEST);
		MessageQueueAdaptor messageQueueService = new MockMessageQueueAdaptorServiceImpl(TestType.PASSING_TEST);

		BusinessLogicProcessor manager = new BusinessLogicProcessor(messageQueueService, databaseService);
		SendToQueueRequest request = makeSendToQueueRequest();

		boolean testPasses = false;
		SendToQueueResponse response = null;
		manager.writeToDatabase(request, response);
		testPasses = true;

		assertThat(testPasses, is(true));
	}
	
	

	@Test
	public void updateToDatabasePassingTest() throws FailedToWriteToDatabaseException {
		DatabaseAdaptor databaseService = new MockDatabaseAdaptorServiceImpl(TestType.PASSING_TEST);
		MessageQueueAdaptor messageQueueService = new MockMessageQueueAdaptorServiceImpl(TestType.PASSING_TEST);

		BusinessLogicProcessor manager = new BusinessLogicProcessor(messageQueueService, databaseService);
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
		DatabaseAdaptor databaseService = new MockDatabaseAdaptorServiceImpl(TestType.THROWS_EXCEPTION);
		MessageQueueAdaptor messageQueueService = new MockMessageQueueAdaptorServiceImpl(TestType.PASSING_TEST);
		String expectedResponseStatusCode = ResponseStatusCodes.DATABASE_FAILURE.getResponseStatusCode();
		String expectedResponseStatusMessage = ResponseStatusMessages.DATABASE_FAILURE.getResponseStatusMessage();

		BusinessLogicProcessor manager = new BusinessLogicProcessor(messageQueueService, databaseService);
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
		DatabaseAdaptor databaseService = new MockDatabaseAdaptorServiceImpl(TestType.THROWS_EXCEPTION);
		MessageQueueAdaptor messageQueueService = new MockMessageQueueAdaptorServiceImpl(TestType.PASSING_TEST);

		BusinessLogicProcessor manager = new BusinessLogicProcessor(messageQueueService, databaseService);
		SendToQueueRequest request = makeSendToQueueRequest();
		SendToQueueResponse response = null;
		
		manager.writeToDatabase(request, response);

	}
	


	@Test(expected = FailedToWriteToDatabaseException.class)
	public void x() throws FailedToWriteToDatabaseException {
		DatabaseAdaptor databaseService = new MockDatabaseAdaptorServiceImpl(TestType.THROWS_EXCEPTION);
		MessageQueueAdaptor messageQueueService = new MockMessageQueueAdaptorServiceImpl(TestType.PASSING_TEST);

		BusinessLogicProcessor manager = new BusinessLogicProcessor(messageQueueService, databaseService);
		SendToQueueRequest request = makeSendToQueueRequest();

		SendToQueueResponse response = new SendToQueueResponse();
		manager.writeToDatabase(request, response);

	}

	@Test(expected = FailedToWriteToDatabaseException.class)
	public void updateToDatabaseExceptionThrowingTest() throws FailedToWriteToDatabaseException {
		DatabaseAdaptor databaseService = new MockDatabaseAdaptorServiceImpl(TestType.THROWS_EXCEPTION);
		MessageQueueAdaptor messageQueueService = new MockMessageQueueAdaptorServiceImpl(TestType.PASSING_TEST);

		BusinessLogicProcessor manager = new BusinessLogicProcessor(messageQueueService, databaseService);
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
