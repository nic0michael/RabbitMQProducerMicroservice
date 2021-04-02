package za.co.nico.rabbitmq.poc.enums;

public enum ResponseStatusCodes {
	OK("200"),
	BAD_REQUEST("400"),
	MQ_FAILURE("401"),
	JSON_FAILURE("402"),
	SYSTEM_FAILURE("403"),
	MISSING_MESSAGE_ID("404"),
	MISSING_MESSAGE_TYPE("405"),
	MISSING_TRANSACTION_ID("406"),
	MISSING_TRANSACTION_TYPE("407"),
	MISSING_TRANSACTION("408"),
	MISSING_Target_SYSTEM_ID("409"),
	MISSING_SENDER_SYSTEM_ID("410");
	
	String responseStatusCode;
	
	private ResponseStatusCodes(String responseStatusCode) {
		this.responseStatusCode=responseStatusCode;
	}
	public String getResponseStatusCode() {
		return this.responseStatusCode;
	}

}
