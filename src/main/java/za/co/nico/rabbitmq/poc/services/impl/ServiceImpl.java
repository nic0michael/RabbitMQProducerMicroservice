package za.co.nico.rabbitmq.poc.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.nico.rabbitmq.poc.adaptors.DatabaseAdaptor;
import za.co.nico.rabbitmq.poc.adaptors.QueueAdaptor;
import za.co.nico.rabbitmq.poc.dtos.ReadFromQueueResponse;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.exceptions.FailedToReadFromDatabaseException;
import za.co.nico.rabbitmq.poc.exceptions.FailedToReadFromQueueException;
import za.co.nico.rabbitmq.poc.exceptions.FailedToSendToQueueException;
import za.co.nico.rabbitmq.poc.exceptions.FailedToWriteToDatabaseException;
import za.co.nico.rabbitmq.poc.services.ServiceInterface;

@Service
public class ServiceImpl implements ServiceInterface{
	
	@Autowired
	QueueAdaptor queue;
	
	@Autowired
	DatabaseAdaptor database;
	
	public ServiceImpl() {}

	public ServiceImpl(QueueAdaptor queue,DatabaseAdaptor database) {
		this.database = database;
		this.queue = queue;
	}
	

	@Override
	public void insertRecord(SendToQueueRequest request) throws FailedToWriteToDatabaseException {
		database.insertRecord(request);
	}

	@Override
	public void updateRecord(SendToQueueRequest request, SendToQueueResponse response) throws FailedToWriteToDatabaseException {
		database.updateRecord(request, response);
	}

	/**
	 * 
	 * This is used for unit testing
	 * 
	 */
	@Override
	public SendToQueueResponse retrieveRecord(String searchValue) throws FailedToReadFromDatabaseException {
		return null;
	}

	@Override
	public SendToQueueResponse sendToMessageQueue(String json) throws FailedToSendToQueueException {
		return queue.sendToMessageQueue(json);
	}
	
	/**
	 * 
	 * This is used for unit testing
	 */
	@Override
	public ReadFromQueueResponse retrieveFromMessageQueue() throws FailedToReadFromQueueException {
		return null;
	}





}
