package za.co.nico.rabbitmq.poc.adaptors.impl;

import org.springframework.stereotype.Component;

import za.co.nico.rabbitmq.poc.adaptors.DatabaseAdaptor;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.exceptions.FailedToReadFromDatabaseException;
import za.co.nico.rabbitmq.poc.exceptions.FailedToWriteToDatabaseException;

@Component
public class DatabaseAdaptorImpl implements DatabaseAdaptor {

	@Override
	public void insertRecord(SendToQueueRequest request) throws FailedToWriteToDatabaseException {
		
	}

	@Override
	public void updateRecord(SendToQueueRequest request, SendToQueueResponse response) throws FailedToWriteToDatabaseException {
		
	}

	/**
	 * 
	 * This is used for unit testing only
	 * 
	 */
	@Override
	public SendToQueueResponse retrieveRecord(String searchValue) throws FailedToReadFromDatabaseException {
		return null;
	}



}
