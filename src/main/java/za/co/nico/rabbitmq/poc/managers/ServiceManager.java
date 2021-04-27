package za.co.nico.rabbitmq.poc.managers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.services.MessageQueueService;
import za.co.nico.rabbitmq.poc.utils.Utils;
import za.co.nico.rabbitmq.poc.validators.RequestValidator;

@Component
public class ServiceManager {
	private static final Logger log = LoggerFactory.getLogger(ServiceManager.class);
	SendToQueueResponse response = null;
	
	@Autowired
	RequestValidator validator;
	
	@Autowired
	MessageQueueService messageQueueService;
	
	
	public ServiceManager() {}
	
	public ServiceManager(MessageQueueService messageQueueService) {
		log.info("Overridden constructor called");
		validator=new RequestValidator();
		this.messageQueueService=messageQueueService;
	}

	public SendToQueueResponse sendToMessageQueue(SendToQueueRequest request) {
		log.info("sendToMessageQueue called");		
		
		request.setMessageId(Utils.generateMessageId("yyyy-MM-dd_HH:mm:ss"));
		response = validator.validateSendToQueueRequest(request);
		if(Utils.validationFailed(response)) {
			return response;
		}
		try {
			response = messageQueueService.sendToMessageQueueService(request);
		} catch (Exception e) {
			log.error("sendToMessageQueue | Error calling MessageQueueService",e);
			response = Utils.makeSystemFailureResponse(request); 
		}
		return response;
	}
	
	
	

}
