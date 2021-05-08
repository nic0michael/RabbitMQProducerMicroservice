package za.co.nico.rabbitmq.poc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueDto;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusCodes;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusMessages;
import za.co.nico.rabbitmq.poc.exceptions.failedToMakeJsonStringExeption;

public class Utils {
	private static final Logger log = LoggerFactory.getLogger(Utils.class);

	/**
	 * 
	 * @param pattern should be String pattern = "yyyy-MM-dd_HH:mm:ss";
	 * @return
	 */
	public static String generateMessageId(String pattern) {
		return dateTodayString(pattern)+"_"+generateUUID();
	}

	public static String dateTodayString(String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(new Date());
	}
	
	public static boolean validationFailed(SendToQueueResponse response) {
		if(response==null) {
			response = makeValidationFailureResponse();
			return true;
		}
		String responseStatusCode = response.getResponseStatusCode();
		if(StringUtils.isNotBlank(responseStatusCode) && responseStatusCode.equalsIgnoreCase(ResponseStatusCodes.OK.getResponseStatusCode())) {
			return false;
		}
		return true;
	}
	
	public static SendToQueueResponse makeSendToQueueResponse(SendToQueueRequest request) {
		SendToQueueResponse response = new SendToQueueResponse();
		String responseStatusCode=ResponseStatusCodes.OK.getResponseStatusCode();
		String responseStatusMessage=ResponseStatusMessages.OK.getResponseStatusMessage();
		response.setResponseStatusCode(responseStatusCode);
		response.setResponseStatusMessage(responseStatusMessage);
		
		response.setMessageDescription(request.getMessageDescription());
		response.setMessageId(request.getMessageId());
		response.setMessageType(request.getMessageType());
		response.setTransactionId(request.getTransactionId());
		response.setTransactionType(request.getTransactionType());
		response.setTargetSystemId(request.getTargetSystemId());
		response.setSenderSystemId(request.getSenderSystemId());
		response.setSenderId(request.getSenderId());
		
		return response;
	}	

	public static SendToQueueResponse makeSuccessResponse() {		

		SendToQueueResponse response = new SendToQueueResponse();
		response.setMessageDescription("dummy_value");
		response.setMessageType("dummy_value");
		response.setSenderId("dummy_value");
		response.setTransactionId("dummy_value");
		response.setTransactionType("dummy_value");
		response.setSenderSystemId("dummy_value");
		String responseStatusCode=ResponseStatusCodes.OK.getResponseStatusCode();
		String responseStatusMessage=ResponseStatusMessages.OK.getResponseStatusMessage();
		response.setResponseStatusCode(responseStatusCode);
		response.setResponseStatusMessage(responseStatusMessage);
		return response;
	}
	
	public static  SendToQueueResponse makeValidationFailureResponse() {
		SendToQueueResponse response = new SendToQueueResponse();
		String responseStatusCode=ResponseStatusCodes.BAD_REQUEST.getResponseStatusCode();
		String responseStatusMessage=ResponseStatusMessages.BAD_REQUEST.getResponseStatusMessage();
		response.setResponseStatusCode(responseStatusCode);
		response.setResponseStatusMessage(responseStatusMessage);
		return response;
	}

	public static  SendToQueueResponse makeMqSendFailureResponse() {
		SendToQueueResponse response = new SendToQueueResponse();
		String responseStatusCode=ResponseStatusCodes.MQ_FAILURE.getResponseStatusCode();
		String responseStatusMessage=ResponseStatusMessages.MQ_FAILURE.getResponseStatusMessage();
		response.setResponseStatusCode(responseStatusCode);
		response.setResponseStatusMessage(responseStatusMessage);
		return response;
	}

	public static  SendToQueueResponse makeJsonCoversionFailureResponse() {
		SendToQueueResponse response = new SendToQueueResponse();
		String responseStatusCode=ResponseStatusCodes.JSON_FAILURE.getResponseStatusCode();
		String responseStatusMessage=ResponseStatusMessages.JSON_FAILURE.getResponseStatusMessage();
		response.setResponseStatusCode(responseStatusCode);
		response.setResponseStatusMessage(responseStatusMessage);
		return response;
	}

	private static SendToQueueResponse makeDatabaseSaveFailureResponse() {
		SendToQueueResponse response = new SendToQueueResponse();
		String responseStatusCode=ResponseStatusCodes.DATABASE_FAILURE.getResponseStatusCode();
		String responseStatusMessage=ResponseStatusMessages.DATABASE_FAILURE.getResponseStatusMessage();
		response.setResponseStatusCode(responseStatusCode);
		response.setResponseStatusMessage(responseStatusMessage);
		return response;
	}

	public static SendToQueueResponse makeDatabaseSaveFailureResponse(SendToQueueResponse response) {
		if(response==null) {
			return makeDatabaseSaveFailureResponse();
		}
		String responseStatusCode=ResponseStatusCodes.DATABASE_FAILURE.getResponseStatusCode();
		String responseStatusMessage=ResponseStatusMessages.DATABASE_FAILURE.getResponseStatusMessage();
		response.setDatabaseResponseStatusCode(responseStatusCode);
		response.setDatabaseResponseStatusMessage(responseStatusMessage);
		return response;
	}


	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}

	public static String makeJsonString(SendToQueueRequest request) throws failedToMakeJsonStringExeption {
		log.error("makeJsonString called");
		ObjectMapper objectMapper = new ObjectMapper();
		SendToQueueDto dto=new SendToQueueDto(request);
		String json=null;
		
		try {
			json = objectMapper.writeValueAsString(dto);
		} catch (JsonProcessingException e) {
			log.error("makeJsonString | failed to create JSON String",e);
			throw new failedToMakeJsonStringExeption(e);
		}	
		
		return json;		
	}

	
}

