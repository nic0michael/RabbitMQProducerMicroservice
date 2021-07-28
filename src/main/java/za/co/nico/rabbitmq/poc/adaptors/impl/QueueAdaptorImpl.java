package za.co.nico.rabbitmq.poc.adaptors.impl;

import za.co.nico.rabbitmq.poc.adaptors.Queue;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;

public class QueueAdaptorImpl implements Queue {

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
