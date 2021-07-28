package za.co.nico.rabbitmq.poc.adaptors.impl;

import za.co.nico.rabbitmq.poc.adaptors.Database;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;

public class DatabaseAdaptorImpl implements Database {

	@Override
	public SendToQueueResponse send(SendToQueueRequest request) {
		return null;
	}

	@Override
	public SendToQueueResponse receive() {
		return null;
	}

	@Override
	public SendToQueueResponse receive(String searchValue) {
		return null;
	}

}
