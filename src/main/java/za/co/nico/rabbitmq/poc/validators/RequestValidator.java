package za.co.nico.rabbitmq.poc.validators;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusCodes;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusMessages;


@Component
public class RequestValidator {
	private static final Logger log = LoggerFactory.getLogger(RequestValidator.class);
	
	private static final boolean VALIDATION_FAILED=false;
	private static final boolean VALIDATION_PASSED=true;
	
	private String responseStatusMessage;
	private String responseStatusCode;
	private SendToQueueResponse failedToSendToQueueResponse;
	
	public SendToQueueResponse validateSendToQueueRequest(SendToQueueRequest request) {
		validateRequest(request);
		SendToQueueResponse response =makeSendToQueueResponse(request);
		return response;
	}

	private void validateRequest(SendToQueueRequest request) {
		if(request==null) {
			responseStatusCode=ResponseStatusCodes.BAD_REQUEST.getResponseStatusCode();
			responseStatusMessage=ResponseStatusMessages.BAD_REQUEST.getResponseStatusMessage();
			
		} else if(StringUtils.isEmpty(request.getMessageId())) {
			responseStatusCode=ResponseStatusCodes.MISSING_MESSAGE_ID.getResponseStatusCode();
			responseStatusMessage=ResponseStatusMessages.MISSING_MESSAGE_ID.getResponseStatusMessage();
			
		} else if(StringUtils.isEmpty(request.getMessageType())) {
			responseStatusCode=ResponseStatusCodes.MISSING_MESSAGE_TYPE.getResponseStatusCode();
			responseStatusMessage=ResponseStatusMessages.MISSING_MESSAGE_TYPE.getResponseStatusMessage();
			
		} else if(StringUtils.isEmpty(request.getTransaction())) {
			responseStatusCode=ResponseStatusCodes.MISSING_TRANSACTION.getResponseStatusCode();
			responseStatusMessage=ResponseStatusMessages.MISSING_TRANSACTION.getResponseStatusMessage();
			
		}else if(StringUtils.isEmpty(request.getTransactionId())) {
			responseStatusCode=ResponseStatusCodes.MISSING_TRANSACTION_ID.getResponseStatusCode();
			responseStatusMessage=ResponseStatusMessages.MISSING_TRANSACTION_ID.getResponseStatusMessage();
			
		}else if(StringUtils.isEmpty(request.getTransactionType())) {
			responseStatusCode=ResponseStatusCodes.MISSING_TRANSACTION_TYPE.getResponseStatusCode();
			responseStatusMessage=ResponseStatusMessages.MISSING_TRANSACTION_TYPE.getResponseStatusMessage();
			
		} else if(StringUtils.isEmpty(request.getSenderSystemId())) {
			responseStatusCode=ResponseStatusCodes.MISSING_SENDER_SYSTEM_ID.getResponseStatusCode();
			responseStatusMessage=ResponseStatusMessages.MISSING_SENDER_SYSTEM_ID.getResponseStatusMessage();
			
		}  else {
			responseStatusCode=ResponseStatusCodes.OK.getResponseStatusCode();
			responseStatusMessage=ResponseStatusMessages.OK.getResponseStatusMessage();
		}

	}


	private SendToQueueResponse makeSendToQueueResponse(SendToQueueRequest request) {
		SendToQueueResponse response=new SendToQueueResponse();
		response.setResponseStatusCode(responseStatusCode);
		response.setResponseStatusMessage(responseStatusMessage);
		return response;
	}
}
