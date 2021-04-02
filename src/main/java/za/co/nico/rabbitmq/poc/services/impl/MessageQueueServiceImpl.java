package za.co.nico.rabbitmq.poc.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.queue.QueueProducer;
import za.co.nico.rabbitmq.poc.services.MessageQueueService;

@Service
public class MessageQueueServiceImpl implements MessageQueueService{
	private static final Logger log = LoggerFactory.getLogger(MessageQueueServiceImpl.class);
	
	@Autowired
	QueueProducer queueProducer;

	@Override
	public SendToQueueResponse sendToMessageQueueService(SendToQueueRequest request) {

		return queueProducer.sendToMessageQueueService(request);
	}

}
