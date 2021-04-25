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

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Override
	/**
	 * reference : https://www.baeldung.com/rabbitmq
	 */
	public void sendToInputQueue(String exchange2, String routingKey2, String json) throws AmqpConnectException,ConnectException  {
		log.info("sendToInputQueue called : exchange2: "+exchange2+ " routingKey2: "+routingKey2);
		
		
//		ConnectionFactory factory = new ConnectionFactory();
//		
////    https://www.rabbitmq.com/api-guide.html
//		
//		factory.setHost("localhost"); // http://localhost:5672/
//		factory.setVirtualHost("/nico_vhost");
//		factory.setPort(5672);
//		factory.setUsername("admin");
//		factory.setPassword("P@55w0rd");
//		
//		Connection connection;
//		Channel channel;
//		try {
//			connection = factory.newConnection();
//			channel = connection.createChannel();
//			channel.queueDeclare("products_queue", false, false, false, null);
//			String message = "product details"; 
//			channel.basicPublish("", "rabbitmq.in.rlpty.q", null, message.getBytes());
////			channel.basicPublish("", "rabbitmq.in.rlpty.q", null, json.getBytes());
//			channel.close();
//			connection.close();
//			
//		} catch (IOException | TimeoutException e) {
//			e.printStackTrace();
//			throw new ConnectException();
//		}
		
		
		
		amqpTemplate.convertAndSend(exchange2, routingKey2, json);
		log.info("sendToInputQueue | Sent message to Queue");
	}

}
