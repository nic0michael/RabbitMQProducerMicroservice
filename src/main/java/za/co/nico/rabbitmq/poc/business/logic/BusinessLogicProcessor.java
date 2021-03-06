package za.co.nico.rabbitmq.poc.business.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.nico.rabbitmq.poc.adaptors.DatabaseAdaptor;
import za.co.nico.rabbitmq.poc.adaptors.MessageQueueAdaptor;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.exceptions.FailedToSendToQueueException;
import za.co.nico.rabbitmq.poc.exceptions.FailedToWriteToDatabaseException;
import za.co.nico.rabbitmq.poc.exceptions.failedToMakeJsonStringExeption;
import za.co.nico.rabbitmq.poc.utils.Utils;
import za.co.nico.rabbitmq.poc.validators.RequestValidator;

@Component
public class BusinessLogicProcessor {
	private static final Logger log = LoggerFactory.getLogger(BusinessLogicProcessor.class);
	SendToQueueResponse response = null;

	@Autowired
	RequestValidator validator;
	
	@Autowired
	MessageQueueAdaptor messageQueueService;

	@Autowired
	DatabaseAdaptor databaseService;

	public BusinessLogicProcessor() {}



	
	/**
	 * Overloaded constructor used for unit testing
	 */
	public BusinessLogicProcessor(MessageQueueAdaptor messageQueueService,DatabaseAdaptor databaseService) {
		log.info("Overridden constructor called");
		validator = new RequestValidator();
		this.messageQueueService = messageQueueService;
		this.databaseService=databaseService;
	}

	public SendToQueueResponse sendToMessageQueue(SendToQueueRequest request) {
		log.info("sendToMessageQueue called");

		request.setMessageId(Utils.generateMessageId("yyyy-MM-dd_HH:mm:ss"));
		response = validator.validateSendToQueueRequest(request);
		if (!Utils.validationFailed(response)) {

			try {
				response = null;
				String json = Utils.makeJsonString(request);
				writeToDatabase( request, response) ;
				response = messageQueueService.sendToMessageQueue(json);
				writeToDatabase( request, response) ;
				
			} catch (FailedToSendToQueueException ea) {
				log.error("sendToMessageQueue | Error calling MessageQueueService", ea);
				response = Utils.makeMqSendFailureResponse();
			} catch (failedToMakeJsonStringExeption e) {
				log.error("sendToMessageQueue | Error calling MessageQueueService", e);
				response = Utils.makeJsonCoversionFailureResponse();
			}catch (FailedToWriteToDatabaseException e) {
				log.error("saveToDatabase | Error saving to database", e);
				response=Utils.makeDatabaseSaveFailureResponse(response);
			}
		}
		
		return response;
	}
	
	public void writeToDatabase(SendToQueueRequest request,SendToQueueResponse response) 
			throws FailedToWriteToDatabaseException{
		
		log.info("writeToDatabase called");
		if(response!=null) {
			databaseService.updateRecord(request, response);
		} else {
			databaseService.insertRecord(request);
		}
		
	}

}
