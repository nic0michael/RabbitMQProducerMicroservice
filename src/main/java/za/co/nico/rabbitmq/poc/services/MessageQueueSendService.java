package za.co.nico.rabbitmq.poc.services;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.exceptions.FailedToSendToQueueException;

public interface MessageQueueSendService {

	SendToQueueResponse sendToMessageQueue( String json) throws FailedToSendToQueueException;

}
