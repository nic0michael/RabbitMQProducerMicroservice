package za.co.nico.rabbitmq.poc.services.impl;

import org.springframework.stereotype.Service;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.exceptions.FailedToWriteToDatabaseException;
import za.co.nico.rabbitmq.poc.services.DatabaseService;

@Service
public class DatabaseServiceImpl implements DatabaseService{

	@Override
	public void updateRecord(SendToQueueRequest request, SendToQueueResponse response) 
			throws FailedToWriteToDatabaseException {

		
	}

	@Override
	public void insertRecord(SendToQueueRequest request) throws FailedToWriteToDatabaseException {

		
	}

}
