package za.co.nico.rabbitmq.poc.adaptors;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;

public interface Adaptor {
	
	public SendToQueueResponse send(SendToQueueRequest request);
	public SendToQueueResponse receive();
	public SendToQueueResponse receive(String searchValue);

}
