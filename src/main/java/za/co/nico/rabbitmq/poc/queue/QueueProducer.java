package za.co.nico.rabbitmq.poc.queue;

import java.net.ConnectException;

import org.springframework.amqp.AmqpConnectException;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;

public interface QueueProducer {

	public void sendToInputQueue(String exchange2, String routingKey2, String json) throws AmqpConnectException,ConnectException,Exception;

}
