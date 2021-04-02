package za.co.nico.rabbitmq.poc.dtos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SendToQueueRequest")
public class SendToQueueRequest {
	String messageId;
	String messageType;
	String messageDescription;
	String transactionId;
	String transactionType;
	String transaction;
	String targetSystemId;
	String senderSystemId;
	String senderId;
			
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



	public String getTransaction() {
		return transaction;
	}



	public void setTransaction(String transaction) {
		this.transaction = transaction;
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
		return "SendToQueueRequest [messageId=" + messageId + ", messageType=" + messageType + ", messageDescription="
				+ messageDescription + ", transactionId=" + transactionId + ", transactionType=" + transactionType
				+ ", transaction=" + transaction + ", targetSystemId=" + targetSystemId + ", senderSystemId="
				+ senderSystemId + ", senderId=" + senderId + "]";
	}
}
