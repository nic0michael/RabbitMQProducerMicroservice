package za.co.nico.rabbitmq.poc.bdd;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import za.co.nico.rabbitmq.poc.adaptors.DatabaseAdaptor;
import za.co.nico.rabbitmq.poc.adaptors.MessageQueueAdaptor;
import za.co.nico.rabbitmq.poc.business.logic.BusinessLogicProcessor;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusCodes;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusMessages;
import za.co.nico.rabbitmq.poc.enums.TestType;
import za.co.nico.rabbitmq.poc.services.impl.MockDatabaseAdaptorServiceImpl;
import za.co.nico.rabbitmq.poc.services.impl.MockMessageQueueAdaptorServiceImpl;
import za.co.nico.rabbitmq.poc.validators.RequestValidator;

public class SendMessageSteps extends Steps {
	String expectedResponseStatusCode=null;
	String expectedResponseStatusMessage=null;
	String responseStatusCode= null;
	String responseStatusMessage= null;
	
	SendToQueueRequest request =null;
	SendToQueueResponse response = null;
	MessageQueueAdaptor messageQueueService = null;
	
	RequestValidator validator=null;
	DatabaseAdaptor databaseService = null;
	BusinessLogicProcessor manager = null;




	/**
	 * Generating reports view to '/system/projects_folder/STS_Projects/NewWorkSpace/RabbitMqPoc/target/jbehave-report'
	 * 
	 * 
	 Scenario: 1 the sent request received from another service is valid send a message to the rabbitmq server
	 Given the request has all fields populated with needed values 
	 When I receive a valid request from another service 
	 Then I should send a message to the rabbitmq server 
	 */
	@Given("the request has all fields populated with needed values")
	public void givenTheRequestHasAllFieldsPopulatedWithNeededValues() {
		setupValidationTestPassingObjects();
	}

	@When("I receive a valid request from another service")
	public void whenIReceiveAValidRequestFromAnotherService() {
		SendToQueueResponse response = validator.validateSendToQueueRequest(request);
		assertThat( response,is(notNullValue()));
		
		responseStatusCode=response.getResponseStatusCode();
		responseStatusMessage=response.getResponseStatusMessage();
		assertThat(responseStatusCode,is(expectedResponseStatusCode));
		assertThat(responseStatusMessage,is(expectedResponseStatusMessage));
	}

	@Then("I should send a message to the rabbitmq server")
	public void thenIShouldSendAMessageToTheRabbitmqServer() {
		setupTestWithPassingObjects();
		response = manager.sendToMessageQueue(request);
		assertThat(response, is(notNullValue()));
		
		responseStatusCode=response.getResponseStatusCode();
		responseStatusMessage=response.getResponseStatusMessage();
		assertThat(responseStatusCode,is(expectedResponseStatusCode));
		assertThat(responseStatusMessage,is(expectedResponseStatusMessage));
	}



	/**	 
	Scenario: 2 the sent requests received from another service has missing fields dont send a message to the rabbitmq server
	Given the request does not have all needed fields populated (PENDING)
	When I receive an invalid request from another service (PENDING)
	Then I should not send a message to the rabbitmq server (PENDING)
	 */
	@Given("the request does not have all needed fields populated")
	public void givenTheRequestDoesNotHaveAllNeededFieldsPopulated() {
		setupValidationTestFailingObjects();
	}

	@When("I receive an invalid request from another service")
	public void whenIReceiveAnInvalidRequestFromAnotherService() {
		SendToQueueResponse response = validator.validateSendToQueueRequest(request);
		assertThat( response,is(notNullValue()));
		
		responseStatusCode=response.getResponseStatusCode();
		responseStatusMessage=response.getResponseStatusMessage();
		assertThat(responseStatusCode,is(expectedResponseStatusCode));
		assertThat(responseStatusMessage,is(expectedResponseStatusMessage));
	}

	@Then("I should not send a message to the rabbitmq server")
	public void thenIShouldNotSendAMessageToTheRabbitmqServer() {
		setupTestWithFailingObjects();
		response = manager.sendToMessageQueue(request);
		assertThat(response, is(notNullValue()));
		
		responseStatusCode=response.getResponseStatusCode();
		responseStatusMessage=response.getResponseStatusMessage();
		assertThat(responseStatusCode,is(expectedResponseStatusCode));
		assertThat(responseStatusMessage,is(expectedResponseStatusMessage));
	  
	}
	
	
	
	/**
  	Scenario: 3 the sent request received from another service is valid sending a message to the rabbitmq server fails
	Given the request has all fields populated with valid values  
	When I receive a valid request from the other service  
	Then I trying to send the message fails I should receive a suitable response  
	 */
	@Given("the request has all fields populated with valid values")
	public void givenTheRequestHasAllFieldsPopulatedWithValidValues() {
		setupValidationTestPassingObjects();
	}

	@When("I receive a valid request from the other service")
	public void whenIReceiveAValidRequestFromTheOtherService() {
		SendToQueueResponse response = validator.validateSendToQueueRequest(request);
		assertThat( response,is(notNullValue()));
		
		responseStatusCode=response.getResponseStatusCode();
		responseStatusMessage=response.getResponseStatusMessage();
		assertThat(responseStatusCode,is(expectedResponseStatusCode));
		assertThat(responseStatusMessage,is(expectedResponseStatusMessage));
	}

	@Then("I trying to send the message fails I should receive a suitable response")
	public void thenITryingToSendTheMessageFailsIShouldReceiveASuitableResponse() {
		setupTestWithExceptionThrowinggObjects();
		response = manager.sendToMessageQueue(request);
		assertThat(response, is(notNullValue()));
		
		responseStatusCode=response.getResponseStatusCode();
		responseStatusMessage=response.getResponseStatusMessage();
		assertThat(responseStatusCode,is(expectedResponseStatusCode));
		assertThat(responseStatusMessage,is(expectedResponseStatusMessage));
	}

	
	private void setupValidationTestPassingObjects() {
		expectedResponseStatusCode=ResponseStatusCodes.OK.getResponseStatusCode();
		expectedResponseStatusMessage=ResponseStatusMessages.OK.getResponseStatusMessage();
		validator=new RequestValidator();
		request =makeSendToQueueRequest();

	}

	private void setupValidationTestFailingObjects() {
		expectedResponseStatusCode=ResponseStatusCodes.MISSING_TRANSACTION.getResponseStatusCode();
		expectedResponseStatusMessage=ResponseStatusMessages.MISSING_TRANSACTION.getResponseStatusMessage();
		validator=new RequestValidator();
		request =makeSendToQueueRequest();
		request.setTransaction(null);

	}

	private void setupTestWithPassingObjects() {
		expectedResponseStatusCode = ResponseStatusCodes.OK.getResponseStatusCode();
		expectedResponseStatusMessage = ResponseStatusMessages.OK.getResponseStatusMessage();
		
		databaseService = new MockDatabaseAdaptorServiceImpl(TestType.PASSING_TEST);
		messageQueueService = new MockMessageQueueAdaptorServiceImpl(TestType.PASSING_TEST);
		manager = new BusinessLogicProcessor(messageQueueService, databaseService);		
		request =makeSendToQueueRequest();
	}

	private void setupTestWithFailingObjects() {
		expectedResponseStatusCode = ResponseStatusCodes.BAD_REQUEST.getResponseStatusCode();
		expectedResponseStatusMessage = ResponseStatusMessages.BAD_REQUEST.getResponseStatusMessage();
		
		databaseService = new MockDatabaseAdaptorServiceImpl(TestType.PASSING_TEST);
		messageQueueService = new MockMessageQueueAdaptorServiceImpl(TestType.FAILING_TEST);
		manager = new BusinessLogicProcessor(messageQueueService, databaseService);	
		request =makeSendToQueueRequest();
	}

	private void setupTestWithExceptionThrowinggObjects() {
		expectedResponseStatusCode = ResponseStatusCodes.MQ_FAILURE.getResponseStatusCode();
		expectedResponseStatusMessage = ResponseStatusMessages.MQ_FAILURE.getResponseStatusMessage();
		
		databaseService = new MockDatabaseAdaptorServiceImpl(TestType.PASSING_TEST);
		messageQueueService = new MockMessageQueueAdaptorServiceImpl(TestType.THROWS_EXCEPTION);
		manager = new BusinessLogicProcessor(messageQueueService, databaseService);	
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
