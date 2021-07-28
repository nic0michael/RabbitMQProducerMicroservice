package za.co.nico.rabbitmq.poc.services;

import za.co.nico.rabbitmq.poc.dtos.ReadFromQueueResponse;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.exceptions.FailedToReadFromDatabaseException;
import za.co.nico.rabbitmq.poc.exceptions.FailedToReadFromQueueException;
import za.co.nico.rabbitmq.poc.exceptions.FailedToSendToQueueException;
import za.co.nico.rabbitmq.poc.exceptions.FailedToWriteToDatabaseException;

public interface ServiceInterface {

	SendToQueueResponse sendToMessageQueue( String json) throws FailedToSendToQueueException;
	
	/**
	 * 
	 * This is used for unit testing
	 */
	ReadFromQueueResponse retrieveFromMessageQueue() throws FailedToReadFromQueueException;
	
	
	void insertRecord(SendToQueueRequest request) throws FailedToWriteToDatabaseException;
	void updateRecord(SendToQueueRequest request,SendToQueueResponse response) throws FailedToWriteToDatabaseException;  
	
	/**
	 * 
	 * This is used for unit testing
	 */
	SendToQueueResponse retrieveRecord(String searchValue) throws FailedToReadFromDatabaseException; 
}
