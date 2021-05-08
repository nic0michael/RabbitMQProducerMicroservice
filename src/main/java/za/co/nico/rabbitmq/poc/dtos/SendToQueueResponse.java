package za.co.nico.rabbitmq.poc.dtos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SendToQueueResponse")
public class SendToQueueResponse {
	String responseStatusCode;
	String responseStatusMessage;
	String databaseResponseStatusCode;
	String databaseResponseStatusMessage;
	String messageId;
	String messageType;
	String messageDescription;
	String transactionId;
	String transactionType;
	String targetSystemId;
	String senderSystemId;
	String senderId;
	
	public String getResponseStatusCode() {
		return responseStatusCode;
	}
	public void setResponseStatusCode(String responseStatusCode) {
		this.responseStatusCode = responseStatusCode;
	}
	public String getResponseStatusMessage() {
		return responseStatusMessage;
	}
	public void setResponseStatusMessage(String responseStatusMessage) {
		this.responseStatusMessage = responseStatusMessage;
	}
	public String getDatabaseResponseStatusCode() {
		return databaseResponseStatusCode;
	}
	public void setDatabaseResponseStatusCode(String databaseResponseStatusCode) {
		this.databaseResponseStatusCode = databaseResponseStatusCode;
	}
	public String getDatabaseResponseStatusMessage() {
		return databaseResponseStatusMessage;
	}
	public void setDatabaseResponseStatusMessage(String databaseResponseStatusMessage) {
		this.databaseResponseStatusMessage = databaseResponseStatusMessage;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessageDescription() {
		return messageDescription;
	}
	public void setMessageDescription(String messageDescription) {
		this.messageDescription = messageDescription;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTargetSystemId() {
		return targetSystemId;
	}
	public void setTargetSystemId(String targetSystemId) {
		this.targetSystemId = targetSystemId;
	}
	public String getSenderSystemId() {
		return senderSystemId;
	}
	public void setSenderSystemId(String senderSystemId) {
		this.senderSystemId = senderSystemId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	
	@Override
	public String toString() {
		return "SendToQueueResponse [responseStatusCode=" + responseStatusCode + ", responseStatusMessage="
				+ responseStatusMessage + ", databaseResponseStatusCode=" + databaseResponseStatusCode
				+ ", databaseResponseStatusMessage=" + databaseResponseStatusMessage + ", messageId=" + messageId
				+ ", messageType=" + messageType + ", messageDescription=" + messageDescription + ", transactionId="
				+ transactionId + ", transactionType=" + transactionType + ", targetSystemId=" + targetSystemId
				+ ", senderSystemId=" + senderSystemId + ", senderId=" + senderId + "]";
	}
	
	

}
