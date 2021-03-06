package za.co.nico.rabbitmq.poc.adaptors;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.exceptions.FailedToSendToQueueException;

public interface MessageQueueAdaptor {

	SendToQueueResponse sendToMessageQueue( String json) throws FailedToSendToQueueException;

}
