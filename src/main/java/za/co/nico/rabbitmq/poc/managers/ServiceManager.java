package za.co.nico.rabbitmq.poc.managers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusCodes;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusMessages;
import za.co.nico.rabbitmq.poc.services.MessageQueueService;
import za.co.nico.rabbitmq.poc.validators.RequestValidator;

@Component
public class ServiceManager {
	private static final Logger log = LoggerFactory.getLogger(ServiceManager.class);
	
	@Autowired
	RequestValidator validator;
	
	@Autowired
	MessageQueueService messageQueueService;
	
	public ServiceManager() {}
	
	public ServiceManager(MessageQueueService messageQueueService) {
		validator=new RequestValidator();
		this.messageQueueService=messageQueueService;
	}

	public SendToQueueResponse sendToMessageQueue(SendToQueueRequest request) {
		SendToQueueResponse response=validator.validateSendToQueueRequest(request);
		if(validationFailed(response)) {
			return response;
		}
		return sendToMessageQueueService(request);
	}
	
	
	private SendToQueueResponse sendToMessageQueueService(SendToQueueRequest request) {
		SendToQueueResponse response;
		try {
			response= messageQueueService.sendToMessageQueueService(request);
		} catch (Exception e) {
			e.printStackTrace();
			response=makeSystemFailureResponse(); 
		}
		return response;
	}
	

	private boolean validationFailed(SendToQueueResponse response) {
		if(response==null) {
			response=makeSystemFailureResponse();
			return false;
		}
		String responseStatusCode=response.getResponseStatusCode();
		if(StringUtils.isNoneBlank(responseStatusCode) && responseStatusCode.equalsIgnoreCase(ResponseStatusCodes.OK.getResponseStatusCode())) {
			return false;
		}
		return true;
	}

	private SendToQueueResponse makeSystemFailureResponse() {
		SendToQueueResponse response=new SendToQueueResponse();
		String responseStatusCode=ResponseStatusCodes.SYSTEM_FAILURE.getResponseStatusCode();
		String responseStatusMessage=ResponseStatusMessages.SYSTEM_FAILURE.getResponseStatusMessage();
		response.setResponseStatusCode(responseStatusCode);
		response.setResponseStatusMessage(responseStatusMessage);
		return response;
	}
}
