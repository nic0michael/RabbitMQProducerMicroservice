package za.co.nico.rabbitmq.poc.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import za.co.nico.rabbitmq.poc.dtos.SendToQueueRequest;
import za.co.nico.rabbitmq.poc.dtos.SendToQueueResponse;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusCodes;
import za.co.nico.rabbitmq.poc.enums.ResponseStatusMessages;
import za.co.nico.rabbitmq.poc.services.impl.MockMessageQueueServiceImpl;

@RunWith(SpringRunner.class)
public class UtilsTest {
	
	@Test
	public void dateTodayStringTest() {
		String pattern = "yyyy-MM-dd_HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String expectedDateString = simpleDateFormat.format(new Date());
		
		String dateString = Utils.dateTodayString(pattern);
		assertThat(dateString, is(notNullValue()));
		assertThat(dateString, is(expectedDateString));
	}
	
	@Test
	public void generateMessageIdTest() {
		String pattern = "yyyy-MM-dd_HH:mm:ss";
		String expectedDateString = Utils.dateTodayString(pattern);		
		String tempString = Utils.generateMessageId(pattern);
		assertThat(tempString, is(notNullValue()));
		String dateString=tempString.substring(0,19);
		assertThat(dateString, is(expectedDateString));
	}
	
	@Test
	public void makeSendToQueueResponseTest() {
		String expectedResponseStatusCode = ResponseStatusCodes.OK.getResponseStatusCode();
		String expectedResponseStatusMessage = ResponseStatusMessages.OK.getResponseStatusMessage();

		SendToQueueRequest request = Utils.makeSendToQueueTestRequest();
		SendToQueueResponse response = Utils.makeSendToQueueResponse(request);

		assertThat(response, is(notNullValue()));

		String responseStatusCode = response.getResponseStatusCode();
		String responseStatusMessage = response.getResponseStatusMessage();
		assertThat(responseStatusCode, is(expectedResponseStatusCode));
		assertThat(responseStatusMessage, is(expectedResponseStatusMessage));

	}	

	@Test
	public void makeMqSendFailureResponseTest() {
		String expectedResponseStatusCode = ResponseStatusCodes.MQ_FAILURE.getResponseStatusCode();
		String expectedResponseStatusMessage = ResponseStatusMessages.MQ_FAILURE.getResponseStatusMessage();

		SendToQueueRequest request = Utils.makeSendToQueueTestRequest();
		SendToQueueResponse response = Utils.makeMqSendFailureResponse(request);

		assertThat(response, is(notNullValue()));

		String responseStatusCode = response.getResponseStatusCode();
		String responseStatusMessage = response.getResponseStatusMessage();
		assertThat(responseStatusCode, is(expectedResponseStatusCode));
		assertThat(responseStatusMessage, is(expectedResponseStatusMessage));

	}

	
	@Test
	public void makeSystemFailureResponseTest() {
		String expectedResponseStatusCode = ResponseStatusCodes.SYSTEM_FAILURE.getResponseStatusCode();
		String expectedResponseStatusMessage = ResponseStatusMessages.SYSTEM_FAILURE.getResponseStatusMessage();

		SendToQueueRequest request = Utils.makeSendToQueueTestRequest();
		SendToQueueResponse response = Utils.makeSystemFailureResponse(request);

		assertThat(response, is(notNullValue()));

		String responseStatusCode = response.getResponseStatusCode();
		String responseStatusMessage = response.getResponseStatusMessage();
		assertThat(responseStatusCode, is(expectedResponseStatusCode));
		assertThat(responseStatusMessage, is(expectedResponseStatusMessage));

	}
}
