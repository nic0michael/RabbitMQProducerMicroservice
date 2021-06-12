package za.co.nico.rabbitmq.poc.services.impl;

import java.io.IOException;
import java.net.ConnectException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.exceptions.FailedToSendToQueueException;
import za.co.nico.rabbitmq.poc.services.MessageQueueSendService;
import za.co.nico.rabbitmq.poc.utils.Utils;

@Service
public class MessageQueueSendServiceImpl implements MessageQueueSendService {
	private static final Logger log = LoggerFactory.getLogger(MessageQueueSendServiceImpl.class);
	SendToQueueResponse response = null;

	@Value("${spring.rabbitmq.username}")
	private String userName;

	@Value("${spring.rabbitmq.password}")
	private String password;

	@Value("${spring.rabbitmq.host}")
	private String host;

	@Value("${spring.rabbitmq.port}")
	private String port;

	@Value("${spring.rabbitmq.virtual.host}") // /nico_vhost
	private String virtualHost;

	@Value("${rabbitmq.exchange.input}")
	private String exchange;

	@Value("${rabbitmq.rlpty.routingkey}")
	private String rlptyRoutingKey;

	@Value("${rabbitmq.rlpty.input.queue}") // rabbitmq.in.rlpty.q
	private String rlptyInputQueue;

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Override
	public SendToQueueResponse sendToMessageQueue(String json) throws FailedToSendToQueueException {
		log.debug("sendToMessageQueueService called");
		SendToQueueResponse response = null;

		log.info("sendToMessageQueueService called : exchange: " + exchange + " rlptyRoutingKey: " + rlptyRoutingKey);
		sendToInputQueueUsingRoutingKey(exchange, rlptyRoutingKey, json);
		sendToInputQueueUsingVhost(json);
		response = Utils.makeSuccessResponse();
		return response;
	}

	/**
	 * This method is used to send to the Queue in the default VHost / using a
	 * routing key to select the queue reference : https://www.baeldung.com/rabbitmq
	 */
	private void sendToInputQueueUsingRoutingKey(String exchange2, String routingKey2, String json) throws FailedToSendToQueueException { 
		try {
			log.info("sendToInputQueueUsingRoutingKey | Sending message to Queue | exchange : "+exchange2+" routingKey : "+routingKey2);
			amqpTemplate.convertAndSend(exchange2, routingKey2, json);
			log.info("sendToInputQueueUsingRoutingKey | Sent message to Queue");
		} catch (AmqpConnectException e) {
			log.error("sendToInputQueueUsingRoutingKey | Failed to send message ro RabbitMQ Queue AmqpConnectException caught", e);
			throw new FailedToSendToQueueException(e);
		}
	}

	/**
	 * This method is used to send to the Queue rabbitmq.in.rlpty.q in the VHost
	 * /nico_vhost https://www.rabbitmq.com/api-guide.html
	 * 
	 */
	private void sendToInputQueueUsingVhost(String json) throws FailedToSendToQueueException { 
		log.debug("sendToInputQueueUsingVhost called | json : " + json);
		ConnectionFactory factory = new ConnectionFactory();
		factory.setVirtualHost(virtualHost);
		factory.setHost(host);
		Integer portValue = new Integer(port);
		factory.setPort(portValue);
		factory.setUsername(userName);
		factory.setPassword(password);
		Connection connection;
		Channel channel;

		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.queueDeclare("products_queue", false, false, false, null);
			channel.basicPublish("", rlptyInputQueue, null, json.getBytes());
			channel.close();
			connection.close();

		} catch (IOException | TimeoutException e) {
			log.error("sendToInputQueueUsingVhost | Failed to send message ro RabbitMQ Queue TimeoutException caught", e);
			throw new FailedToSendToQueueException(e);
		} catch (AmqpConnectException e) {
			log.error("sendToInputQueueUsingVhost | Failed to send message ro RabbitMQ Queue AmqpConnectException caught", e);
			throw new FailedToSendToQueueException(e);
		}

		log.info("sendToInputQueueUsingVhost | Sent message to Queue");
	}

}
