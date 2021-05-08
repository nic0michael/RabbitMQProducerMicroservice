package za.co.nico.rabbitmq.poc.services;import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.exceptions.FailedToWriteToDatabaseException;

public interface DatabaseService {
	void insertRecord(SendToQueueRequest request) throws FailedToWriteToDatabaseException;
	void updateRecord(SendToQueueRequest request,SendToQueueResponse response) throws FailedToWriteToDatabaseException;  
	
}
