package za.co.nico.rabbitmq.poc.adaptors;

import za.co.nico.rabbitmq.poc.dtos.ReadFromQueueResponse;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.exceptions.FailedToReadFromQueueException;
import za.co.nico.rabbitmq.poc.exceptions.FailedToSendToQueueException;

public interface QueueAdaptor {

	SendToQueueResponse sendToMessageQueue(String json) throws FailedToSendToQueueException;

	/**
	 * 
	 * This is used for unit testing
	 */
	ReadFromQueueResponse retrieveFromMessageQueue() throws FailedToReadFromQueueException;

}
