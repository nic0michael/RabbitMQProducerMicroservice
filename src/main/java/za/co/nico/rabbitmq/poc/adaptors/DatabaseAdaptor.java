package za.co.nico.rabbitmq.poc.adaptors;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.exceptions.FailedToReadFromDatabaseException;
import za.co.nico.rabbitmq.poc.exceptions.FailedToWriteToDatabaseException;

public interface DatabaseAdaptor {	
	

	void insertRecord(SendToQueueRequest request) throws FailedToWriteToDatabaseException;
	void updateRecord(SendToQueueRequest request,SendToQueueResponse response) throws FailedToWriteToDatabaseException;  
	
	/**
	 * 
	 * This is used for unit testing
	 */
	SendToQueueResponse retrieveRecord(String searchValue) throws FailedToReadFromDatabaseException; 

}
