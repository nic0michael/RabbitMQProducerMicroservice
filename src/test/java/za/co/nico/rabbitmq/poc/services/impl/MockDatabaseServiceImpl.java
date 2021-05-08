package za.co.nico.rabbitmq.poc.services.impl;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.enums.TestType;
import za.co.nico.rabbitmq.poc.exceptions.FailedToWriteToDatabaseException;
import za.co.nico.rabbitmq.poc.services.DatabaseService;

public class MockDatabaseServiceImpl implements DatabaseService {
	private TestType testType;

	private MockDatabaseServiceImpl() {
	}

	public MockDatabaseServiceImpl(TestType testType) {
		this.testType = testType;
	}

	@Override
	public void updateRecord(SendToQueueRequest request, SendToQueueResponse response)
			throws FailedToWriteToDatabaseException {

		String operationType = testType.name();

		switch (operationType) {
		case "PASSING_TEST":
			// DO NOTHING
			break;

		case "THROWS_EXCEPTION":
			throw new FailedToWriteToDatabaseException();

		default:
			// DO NOTHING
		}
	}

	@Override
	public void insertRecord(SendToQueueRequest request) throws FailedToWriteToDatabaseException {

		String operationType = testType.name();

		switch (operationType) {
		case "PASSING_TEST":
			// DO NOTHING
			break;

		case "THROWS_EXCEPTION":
			throw new FailedToWriteToDatabaseException();

		default:
			// DO NOTHING

		}

	}
}
