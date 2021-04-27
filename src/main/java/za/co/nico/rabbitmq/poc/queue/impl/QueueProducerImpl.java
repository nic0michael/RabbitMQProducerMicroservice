package za.co.nico.rabbitmq.poc.queue.impl;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;

import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.core.AmqpTemplate;

import za.co.nico.rabbitmq.poc.queue.QueueProducer;

@Component
public class QueueProducerImpl implements QueueProducer{
	private static final Logger log = LoggerFactory.getLogger(QueueProducerImpl.class);
	
	@Value("${spring.rabbitmq.username}") 
	private String userName;	
	
	@Value("${spring.rabbitmq.password}") 
	private String password;
	
	@Value("${spring.rabbitmq.host}")
	private String host;
	
	@Value("${spring.rabbitmq.port}")
	private String port;
	
	@Value("${spring.rabbitmq.virtual.host}")  // /nico_vhost
	private String virtualHost;
	

	@Value("${rabbitmq.exchange.input}")
	private String exchange;
	
	@Value("${rabbitmq.rlpty.routingkey}") 
	private String rlptyRoutingKey;		

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Override
	public void sendToInputQueue(String exchange2, String routingKey2, String json) throws AmqpConnectException,ConnectException  {
		log.info("sendToInputQueue called : exchange2: "+exchange2+ " routingKey2: "+routingKey2);
		sendToInputQueueUsingRoutingKey( exchange2,  routingKey2,  json);
		sendToInputQueueUsingVhost( json);
	}
	
	/**
	 * reference : https://www.baeldung.com/rabbitmq
	 */
	public void sendToInputQueueUsingRoutingKey(String exchange2, String routingKey2, String json) throws AmqpConnectException,ConnectException  {
		amqpTemplate.convertAndSend(exchange2, routingKey2, json);
		log.info("sendToInputQueueUsingRoutingKey | Sent message to Queue");

	}
	
	/**
	 * 
	 * https://www.rabbitmq.com/api-guide.html
	 * 
	 */
	public void sendToInputQueueUsingVhost(String json) throws AmqpConnectException,ConnectException  {			
		ConnectionFactory factory = new ConnectionFactory();
		factory.setVirtualHost(virtualHost); 		
		factory.setHost(host); 
		Integer portValue =new Integer(port);
		factory.setPort(portValue); 
		factory.setUsername(userName);  
		factory.setPassword(password); 		
		Connection connection;
		Channel channel;
		
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.queueDeclare("products_queue", false, false, false, null);
			channel.basicPublish("", "rabbitmq.in.rlpty.q", null, json.getBytes());
			channel.close();
			connection.close();
			
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
			throw new ConnectException();
		}
		
		log.info("sendToInputQueueUsingVhost | Sent message to Queue");
	}

}
