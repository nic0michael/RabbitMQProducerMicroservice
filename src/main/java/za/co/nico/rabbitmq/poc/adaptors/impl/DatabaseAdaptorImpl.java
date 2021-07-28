package za.co.nico.rabbitmq.poc.adaptors.impl;

import za.co.nico.rabbitmq.poc.adaptors.Database;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.exceptions.FailedToReadFromDatabaseException;
import za.co.nico.rabbitmq.poc.exceptions.FailedToWriteToDatabaseException;

public class DatabaseAdaptorImpl implements Database {

	@Override
	public SendToQueueResponse send(SendToQueueRequest request) throws FailedToWriteToDatabaseException {
		return null;
	}

	@Override
	public SendToQueueResponse receive() throws FailedToReadFromDatabaseException {
		return null;
	}

	@Override
	public SendToQueueResponse receive(String searchValue) throws FailedToReadFromDatabaseException {
		return null;
	}


}
