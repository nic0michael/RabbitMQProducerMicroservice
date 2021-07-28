package za.co.nico.rabbitmq.poc.adaptors;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;

public interface Adaptor {
	
	public SendToQueueResponse send(SendToQueueRequest request) throws Exception;
	public SendToQueueResponse receive() throws Exception;
	public SendToQueueResponse receive(String searchValue) throws Exception;

}
