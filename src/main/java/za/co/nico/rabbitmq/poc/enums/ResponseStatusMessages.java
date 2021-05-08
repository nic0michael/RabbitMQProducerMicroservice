package za.co.nico.rabbitmq.poc.enums;

public enum ResponseStatusMessages {
	OK("OK : Message delivered to RabbitMQ"),
	BAD_REQUEST("The Request is NULL"),
	MQ_FAILURE("Failure : Failed to deliver Message to RabbitMQ"),
	JSON_FAILURE("Failure : Failed to conert DTO to a JSON String"),
	DATABASE_FAILURE("Failure : Failed to write to the Database"),
	MISSING_MESSAGE_ID("The Request is invalid : MessageId is missing"),
	MISSING_MESSAGE_TYPE("The Request is invalid : MessageType is missing"),
	MISSING_TRANSACTION_ID("The Request is invalid : TransactionId is missing"),
	MISSING_TRANSACTION_TYPE("The Request is invalid : TransactionType is missing"),
	MISSING_TRANSACTION("The Request is invalid : Transaction is missing"),
	MISSING_Target_SYSTEM_ID("The Request is invalid : TargetSystemId is missing"),
	MISSING_SENDER_SYSTEM_ID("The Request is invalid : SenderSystemId is missing"),
	SYSTEM_FAILURE("Failure : a System failure occured");
	
	
	
	String responseStatusMessage;
	
	private ResponseStatusMessages(String responseStatusMessage){
		this.responseStatusMessage=responseStatusMessage;
	}
	
	public String getResponseStatusMessage() {
		return this.responseStatusMessage;
	}

}
