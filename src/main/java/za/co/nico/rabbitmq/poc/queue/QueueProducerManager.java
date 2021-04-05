package za.co.nico.rabbitmq.poc.queue;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;

public interface QueueProducerManager {

	SendToQueueResponse sendToMessageQueueService(SendToQueueRequest request) throws Exception ;

}
