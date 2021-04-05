package za.co.nico.rabbitmq.poc.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.queue.QueueProducer;
import za.co.nico.rabbitmq.poc.queue.QueueProducerManager;
import za.co.nico.rabbitmq.poc.services.MessageQueueService;
import za.co.nico.rabbitmq.poc.utils.Utils;

@Service
public class MessageQueueServiceImpl implements MessageQueueService{
	private static final Logger log = LoggerFactory.getLogger(MessageQueueServiceImpl.class);
	SendToQueueResponse response = null;
	
	@Autowired
	QueueProducerManager queueProducerManager;

	@Override
	public SendToQueueResponse sendToMessageQueueService(SendToQueueRequest request) {
		log.debug("sendToMessageQueueService called");

		try {
			response = queueProducerManager.sendToMessageQueueService(request);
		} catch (Exception e) {
			log.error("Error calling QueueProducer " ,e);
			response = Utils.makeSystemFailureResponse(request); 
		}
		
		return response;
	}

}
