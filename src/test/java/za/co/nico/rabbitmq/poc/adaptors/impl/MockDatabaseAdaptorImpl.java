package za.co.nico.rabbitmq.poc.adaptors.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import za.co.nico.rabbitmq.poc.adaptors.DatabaseAdaptor;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusCodes;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusMessages;
import za.co.nico.rabbitmq.poc.enums.TestType;
import za.co.nico.rabbitmq.poc.exceptions.FailedToReadFromDatabaseException;
import za.co.nico.rabbitmq.poc.exceptions.FailedToWriteToDatabaseException;
import za.co.nico.rabbitmq.poc.utils.Utils;

/**
 * 
 * @author nickm
 *
 *         This is based on :
 * 
 *         Dave Farley's : When TDD is Difficult - Try This!
 *         ](https://www.youtube.com/watch?v=ESHn53myB88&pp=sAQA)
 *         https://www.youtube.com/watch?v=ESHn53myB88&pp=sAQA
 * 
 */
public class MockDatabaseAdaptorImpl implements DatabaseAdaptor {

	private static Map<Integer, String> mockDatabase = new HashMap<>();
	private static int recordNumber = 0;
	private TestType testType;

	private MockDatabaseAdaptorImpl() {
	}

	public MockDatabaseAdaptorImpl(TestType testType) {
		this.testType = testType;
	}

	@Override
	public void insertRecord(SendToQueueRequest request) throws FailedToWriteToDatabaseException {

		String operationType = testType.name();

		switch (operationType) {
		case "PASSING_TEST":
			mockDatabase.put(recordNumber, request.toString());
			recordNumber++;
			break;

		case "FAILING_TEST":
			// DO NOTHING
			break;

		case "THROWS_EXCEPTION":
			throw new FailedToWriteToDatabaseException();

		default:
			// DO NOTHING
		}
	}


	@Override
	public void updateRecord(SendToQueueRequest request, SendToQueueResponse response) throws FailedToWriteToDatabaseException {

		String operationType = testType.name();

		switch (operationType) {
		case "PASSING_TEST":
			mockDatabase.put(recordNumber, request.toString());
			recordNumber++;
			break;

		case "FAILING_TEST":
			// DO NOTHING
			break;

		case "THROWS_EXCEPTION":
			throw new FailedToWriteToDatabaseException();

		default:
			// DO NOTHING
		}
	}

	@Override
	public SendToQueueResponse retrieveRecord(String searchValue) throws FailedToReadFromDatabaseException {

		SendToQueueResponse response = new SendToQueueResponse();
		String operationType = testType.name();

		String databaseResponseStatusCode = null;
		String responseStatusMessage = null;

		switch (operationType) {
		case "PASSING_TEST":
			if (searchValue != null && StringUtils.isNumeric(searchValue)) {
				int theRecodrNumber = Integer.parseInt(searchValue);
				String request = mockDatabase.get(recordNumber);
				databaseResponseStatusCode = ResponseStatusCodes.OK.getResponseStatusCode();
				responseStatusMessage = ResponseStatusMessages.OK.getResponseStatusMessage();
				response.setDatabaseResponseStatusCode(databaseResponseStatusCode);
				response.setDatabaseResponseStatusMessage(responseStatusMessage);
				response.setMessageDescription(request);
				break;
			}

		case "FAILING_TEST":
			databaseResponseStatusCode = ResponseStatusCodes.DATABASE_FAILURE.getResponseStatusCode();
			responseStatusMessage = ResponseStatusMessages.DATABASE_FAILURE.getResponseStatusMessage();
			response.setDatabaseResponseStatusCode(databaseResponseStatusCode);
			response.setDatabaseResponseStatusMessage(responseStatusMessage);
			break;

		case "THROWS_EXCEPTION":
			throw new FailedToReadFromDatabaseException();

		default:
			// DO NOTHING
		}

		return response;

	}

}
