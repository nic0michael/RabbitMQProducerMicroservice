package za.co.nico.rabbitmq.poc.managers;

import java.net.ConnectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueDto;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.services.MessageQueueSendService;
import za.co.nico.rabbitmq.poc.utils.Utils;
import za.co.nico.rabbitmq.poc.validators.RequestValidator;

@Component
public class ServiceManager {
	private static final Logger log = LoggerFactory.getLogger(ServiceManager.class);
	SendToQueueResponse response = null;
	
	@Autowired
	RequestValidator validator;
	
	@Autowired
	MessageQueueSendService messageQueueService;
	
	
	public ServiceManager() {}
	
	public ServiceManager(MessageQueueSendService messageQueueService) {
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
			String json=makeJsonString(request);
			response = messageQueueService.sendToMessageQueue(json);
		} catch (AmqpConnectException ea) {
			log.error("sendToMessageQueue | Error calling MessageQueueService",ea);
			response = Utils.makeMqSendFailureResponse(request); 
		} catch (ConnectException ec) {
			log.error("sendToMessageQueue | Error calling MessageQueueService",ec);
			response = Utils.makeMqSendFailureResponse(request); 
		} catch (Exception e) {
			log.error("sendToMessageQueue | Error calling MessageQueueService",e);
			response = Utils.makeSystemFailureResponse(request); 
		}
		return response;
	}
	
	public String makeJsonString(SendToQueueRequest request) {
		log.info("sendToMessageQueueService called");
		SendToQueueResponse response=null;
		ObjectMapper objectMapper = new ObjectMapper();
		SendToQueueDto dto=new SendToQueueDto(request);
		String json=null;
		
		try {
			json = objectMapper.writeValueAsString(dto);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return json;
		
	}
	
	
	

}
