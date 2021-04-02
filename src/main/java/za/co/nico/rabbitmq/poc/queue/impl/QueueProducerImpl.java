package za.co.nico.rabbitmq.poc.queue.impl;


import java.net.ConnectException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueDto;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusCodes;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusMessages;
import za.co.nico.rabbitmq.poc.queue.QueueProducer;

@Component
public class QueueProducerImpl implements QueueProducer{
	private static final Logger log = LoggerFactory.getLogger(QueueProducerImpl.class);


	@Value("${rabbitmq.exchange.input}")
	private String exchange;
	
	@Value("${rabbitmq.rlpty.routingkey}") 
	private String rlptyRoutingKey;		

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Override
	public SendToQueueResponse sendToMessageQueueService(SendToQueueRequest request) {
		SendToQueueResponse response=null;
		ObjectMapper objectMapper = new ObjectMapper();
		SendToQueueDto dto=new SendToQueueDto(request);
		String json=null;
		
		try {
			json = objectMapper.writeValueAsString(dto);			
			sendToInputQueue(exchange,rlptyRoutingKey,json);
			response=makeSendToQueueResponse(request);
			
		} catch (JsonProcessingException e) {
			log.error("failed to convert dto to JSON String");
			response=makeJsonCoversionFailureResponse();
			
		}  catch (AmqpConnectException e) {
			log.error("failed to send to queue");
			response=makeSendToQueueFailureResponse();
			
		} catch (ConnectException e) {
			log.error("failed to send to queue");
			response=makeSendToQueueFailureResponse();
		} 
		
		return response;
	}
	
	private SendToQueueResponse makeSendToQueueResponse(SendToQueueRequest request) {
		SendToQueueResponse response =new SendToQueueResponse();
		String responseStatusCode=ResponseStatusCodes.OK.getResponseStatusCode();
		String responseStatusMessage=ResponseStatusMessages.OK.getResponseStatusMessage();
		response.setResponseStatusCode(responseStatusCode);
		response.setResponseStatusMessage(responseStatusMessage);
		
		response.setMessageDescription(request.getMessageDescription());
		response.setMessageId(request.getMessageId());
		response.setMessageType(request.getMessageType());
		response.setSenderId(request.getSenderId());
		response.setTransactionId(request.getTransactionId());
		response.setTransactionType(request.getTransactionType());
		response.setSenderSystemId(request.getTargetSystemId());
		
		return response;
	}

	private void sendToInputQueue(String exchange2, String routingKey2, String json) throws AmqpConnectException,ConnectException  { 
		amqpTemplate.convertAndSend(exchange2, routingKey2, json);
		log.info("Sent message to Queue");
	}

	private SendToQueueResponse makeSystemFailureResponse() {
		SendToQueueResponse response=new SendToQueueResponse();
		String responseStatusCode=ResponseStatusCodes.SYSTEM_FAILURE.getResponseStatusCode();
		String responseStatusMessage=ResponseStatusMessages.SYSTEM_FAILURE.getResponseStatusMessage();
		response.setResponseStatusCode(responseStatusCode);
		response.setResponseStatusMessage(responseStatusMessage);
		return response;
	}

	private SendToQueueResponse makeJsonCoversionFailureResponse() {
		SendToQueueResponse response=new SendToQueueResponse();
		String responseStatusCode=ResponseStatusCodes.JSON_FAILURE.getResponseStatusCode();
		String responseStatusMessage=ResponseStatusMessages.JSON_FAILURE.getResponseStatusMessage();
		response.setResponseStatusCode(responseStatusCode);
		response.setResponseStatusMessage(responseStatusMessage);
		return response;
	}

	private SendToQueueResponse makeSendToQueueFailureResponse() {
		SendToQueueResponse response=new SendToQueueResponse();
		String responseStatusCode=ResponseStatusCodes.MQ_FAILURE.getResponseStatusCode();
		String responseStatusMessage=ResponseStatusMessages.MQ_FAILURE.getResponseStatusMessage();
		response.setResponseStatusCode(responseStatusCode);
		response.setResponseStatusMessage(responseStatusMessage);
		return response;
	}

}
