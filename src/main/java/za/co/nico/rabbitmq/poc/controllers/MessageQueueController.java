package za.co.nico.rabbitmq.poc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import za.co.nico.rabbitmq.poc.business.logic.BusinessLogicProcessor;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;

@RestController
public class MessageQueueController {
	private static final Logger log = LoggerFactory.getLogger(MessageQueueController.class);
	
	@Autowired
	BusinessLogicProcessor manager;
	
	@PostMapping(value = "/send", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public SendToQueueResponse sendToMessageQueue(@RequestBody SendToQueueRequest request) {
		
		log.info("sendToMessageQueue called");
		return manager.sendToMessageQueue(request);
	}

}
