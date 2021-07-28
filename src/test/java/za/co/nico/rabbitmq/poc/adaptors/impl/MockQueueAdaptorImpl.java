package za.co.nico.rabbitmq.poc.adaptors.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import za.co.nico.rabbitmq.poc.adaptors.QueueAdaptor;
import za.co.nico.rabbitmq.poc.dtos.ReadFromQueueResponse;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusCodes;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusMessages;
import za.co.nico.rabbitmq.poc.enums.TestType;
import za.co.nico.rabbitmq.poc.exceptions.FailedToReadFromQueueException;
import za.co.nico.rabbitmq.poc.exceptions.FailedToSendToQueueException;
import za.co.nico.rabbitmq.poc.exceptions.FailedToWriteToDatabaseException;

/**
 * 
 * @author nickm This is based on :
 * 
 *         Dave Farley's : When TDD is Difficult - Try This!
 *         ](https://www.youtube.com/watch?v=ESHn53myB88&pp=sAQA)
 *         https://www.youtube.com/watch?v=ESHn53myB88&pp=sAQA
 *
 */
public class MockQueueAdaptorImpl implements QueueAdaptor {
	private TestType testType;
	private List<String> queue = new LinkedList<>();

	private MockQueueAdaptorImpl() {
	}

	public MockQueueAdaptorImpl(TestType testType) {
		this.testType = testType;
	}

	@Override
	public SendToQueueResponse sendToMessageQueue(String json) throws FailedToSendToQueueException {
		String operationType = testType.name();
		SendToQueueResponse response = new SendToQueueResponse();
		String responseStatusCode = null;
		String responseStatusMessage = null;

		switch (operationType) {
		case "PASSING_TEST":
			if (StringUtils.isNotEmpty(json)) {
				queue.add(json);
				responseStatusCode = ResponseStatusCodes.OK.getResponseStatusCode();
				responseStatusMessage = ResponseStatusMessages.OK.getResponseStatusMessage();
				response.setResponseStatusCode(responseStatusCode);
				response.setResponseStatusMessage(responseStatusMessage);
				
			}else {
				responseStatusCode = ResponseStatusCodes.JSON_FAILURE.getResponseStatusCode();
				responseStatusMessage = ResponseStatusMessages.JSON_FAILURE.getResponseStatusMessage();
				response.setResponseStatusCode(responseStatusCode);
				response.setResponseStatusMessage(responseStatusMessage);				
			}

			break;

		case "FAILING_TEST":
			responseStatusCode = ResponseStatusCodes.MQ_FAILURE.getResponseStatusCode();
			responseStatusMessage = ResponseStatusMessages.MQ_FAILURE.getResponseStatusMessage();
			response.setResponseStatusCode(responseStatusCode);
			response.setResponseStatusMessage(responseStatusMessage);
			break;

		case "THROWS_EXCEPTION":
			throw new FailedToSendToQueueException();

		default:
			// DO NOTHING
		}
		return response;
	}

	@Override
	public ReadFromQueueResponse retrieveFromMessageQueue() throws FailedToReadFromQueueException {
		String operationType = testType.name();
		ReadFromQueueResponse response = new ReadFromQueueResponse();
		String responseStatusCode = null;
		String responseStatusMessage = null;

		switch (operationType) {
		case "PASSING_TEST":

			if (!queue.isEmpty()) {
				String message = queue.get(0);
				queue.remove(0);
				if (StringUtils.isNotEmpty(message)) {
					responseStatusCode = ResponseStatusCodes.OK.getResponseStatusCode();
					responseStatusMessage = ResponseStatusMessages.OK.getResponseStatusMessage();
					response.setResponseStatusCode(responseStatusCode);
					response.setResponseStatusMessage(responseStatusMessage);
					response.setMessageDescription(message);
					break;
				}
			}

		case "FAILING_TEST":
			responseStatusCode = ResponseStatusCodes.MQ_FAILURE.getResponseStatusCode();
			responseStatusMessage = ResponseStatusMessages.MQ_FAILURE.getResponseStatusMessage();
			response.setResponseStatusCode(responseStatusCode);
			response.setResponseStatusMessage(responseStatusMessage);
			break;

		case "THROWS_EXCEPTION":
			throw new FailedToReadFromQueueException();

		default:
			// DO NOTHING
		}
		return response;
	}


}
