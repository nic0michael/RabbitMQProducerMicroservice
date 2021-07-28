package za.co.nico.rabbitmq.poc.adaptors;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.exceptions.FailedToReanFromQueueException;
import za.co.nico.rabbitmq.poc.exceptions.FailedToSendToQueueException;

public interface Queue extends Adaptor{	
	
	@Override
	public SendToQueueResponse send(SendToQueueRequest request) throws 	FailedToSendToQueueException;
	
	@Override
	public SendToQueueResponse receive() throws FailedToReanFromQueueException;
	
	@Override
	public SendToQueueResponse receive(String searchValue) throws FailedToReanFromQueueException;

}
