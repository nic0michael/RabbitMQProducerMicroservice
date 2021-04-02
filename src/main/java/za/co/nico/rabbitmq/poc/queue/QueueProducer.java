package za.co.nico.rabbitmq.poc.queue;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;

public interface QueueProducer {

	SendToQueueResponse sendToMessageQueueService(SendToQueueRequest request);

}
