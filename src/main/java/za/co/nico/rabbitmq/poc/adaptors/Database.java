package za.co.nico.rabbitmq.poc.adaptors;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.exceptions.FailedToReadFromDatabaseException;
import za.co.nico.rabbitmq.poc.exceptions.FailedToWriteToDatabaseException;

public interface Database extends Adaptor{
	
	@Override
	public SendToQueueResponse send(SendToQueueRequest request) throws FailedToWriteToDatabaseException;
	
	@Override
	public SendToQueueResponse receive() throws FailedToReadFromDatabaseException;
	
	@Override
	public SendToQueueResponse receive(String searchValue) throws FailedToReadFromDatabaseException;

}
