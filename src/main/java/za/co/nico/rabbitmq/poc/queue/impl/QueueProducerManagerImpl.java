package za.co.nico.rabbitmq.poc.queue.impl;


import java.net.ConnectException;

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
import za.co.nico.rabbitmq.poc.queue.QueueProducer;
import za.co.nico.rabbitmq.poc.queue.QueueProducerManager;
import za.co.nico.rabbitmq.poc.utils.Utils;

@Component
public class QueueProducerManagerImpl implements QueueProducerManager{
	private static final Logger log = LoggerFactory.getLogger(QueueProducerManagerImpl.class);


	@Value("${rabbitmq.exchange.input}")
	private String exchange;
	
	@Value("${rabbitmq.rlpty.routingkey}") 
	private String rlptyRoutingKey;		

	@Autowired
	private QueueProducer queueProducer;
	
	public QueueProducerManagerImpl() {}
	
	public QueueProducerManagerImpl(String exchange,String rlptyRoutingKey,QueueProducer queueProducer) {
		this.exchange = exchange;
		this.rlptyRoutingKey = rlptyRoutingKey;
		this.queueProducer=queueProducer;
	}
	
	@Override
	public SendToQueueResponse sendToMessageQueueService(SendToQueueRequest request) {
		log.info("sendToMessageQueueService called");
		SendToQueueResponse response=null;
		ObjectMapper objectMapper = new ObjectMapper();
		SendToQueueDto dto=new SendToQueueDto(request);
		String json=null;
		
		try {
			json = objectMapper.writeValueAsString(dto);			
			queueProducer.sendToInputQueue(exchange,rlptyRoutingKey,json);
			response=Utils.makeSendToQueueResponse(request);
			
		} catch (JsonProcessingException e) {
			log.error("sendToMessageQueueService | Failed to convert dto to JSON String",e);
			response=Utils.makeJsonCoversionFailureResponse(request);
			
		}  catch (AmqpConnectException e) {
			log.error("sendToMessageQueueService | Failed to send to queue",e);
			response=Utils.makeMqSendFailureResponse(request);
			
		} catch (ConnectException e) {
			log.error("sendToMessageQueueService | Failed to connect to queue",e);
			response=Utils.makeMqSendFailureResponse(request);
		}  catch (Exception e) {
			log.error("sendToMessageQueueService | Unknown failere  to connect to queue",e);
			response=Utils.makeSystemFailureResponse(request);
		} 
		
		return response;
	}
	




}
