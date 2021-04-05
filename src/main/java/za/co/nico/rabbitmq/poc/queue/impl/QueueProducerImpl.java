package za.co.nico.rabbitmq.poc.queue.impl;

import java.net.ConnectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueProducerImpl {
	private static final Logger log = LoggerFactory.getLogger(QueueProducerImpl.class);

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void sendToInputQueue(String exchange2, String routingKey2, String json) throws AmqpConnectException,ConnectException  {
		log.debug("sendToInputQueue called");
		amqpTemplate.convertAndSend(exchange2, routingKey2, json);
		log.info("sendToInputQueue | Sent message to Queue");
	}

}
