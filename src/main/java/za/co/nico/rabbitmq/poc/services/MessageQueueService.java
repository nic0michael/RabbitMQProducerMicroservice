package za.co.nico.rabbitmq.poc.services;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;

public interface MessageQueueService {

	SendToQueueResponse sendToMessageQueueService(SendToQueueRequest request) throws Exception;

}
