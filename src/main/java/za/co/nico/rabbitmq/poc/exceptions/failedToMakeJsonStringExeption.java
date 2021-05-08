package za.co.nico.rabbitmq.poc.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;

public class failedToMakeJsonStringExeption extends Exception {

	public failedToMakeJsonStringExeption() { }

	public failedToMakeJsonStringExeption(JsonProcessingException e) { }

}
