package za.co.nico.rabbitmq.poc.services.impl;

import org.springframework.stereotype.Service;

import za.co.nico.rabbitmq.poc.adaptors.DatabaseAdaptor;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.exceptions.FailedToWriteToDatabaseException;

@Service
public class DatabaseAdaptorServiceImpl implements DatabaseAdaptor{

	@Override
	public void updateRecord(SendToQueueRequest request, SendToQueueResponse response) 
			throws FailedToWriteToDatabaseException {

		
	}

	@Override
	public void insertRecord(SendToQueueRequest request) throws FailedToWriteToDatabaseException {

		
	}

}
