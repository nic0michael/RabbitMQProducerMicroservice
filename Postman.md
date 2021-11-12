# Postman Installation And Use of Newman

## Download Postman

[To download Postman](https://www.postman.com/downloads/)

You need to download and Sign Up to use Postman.   
**It is important that you don't lose the password.**   

## Creating a New Positive Test Request
Open Postman and click on the New Button.   
Click on the + Create Collection link.   
Give your collection a name : My Microservice Tests.   
Click on the "Correct Tick icon".   
The created you collection for tests.   
Now give your request a name (for the test)  
Click on your collection : My Microservice Tests.   
And press the Save To Channel Button

### This creates a Service with a Get Action change it to a Post Action and enter the URL
Click on the Get drop-down combo-box and select Post.   
In the Text field next to the Post drop-down combo-box Type the URL to the Microservice.   

### URL
http://localhost:9080/rabbitmq/rlpty/qproducer/send

Press the Save Button.   

### Add the Headers Key Value Pairs
Click on the Headers Tab under the URL Field.   
Enter the following Key/Value Pairs.   

```
Key             Value   
Accept          application/json   
Content-Type    application/json   
```

### ADD the Positive Test Body
Click on the Body Tab next to the Headers tab.   
Click on the Raw Radio Button under the Body Tab.   
Paste the Body JSON text in the Text box below the Raw Radio Button.   
  
### Positive Test Body Text
```
{
    "messageId":"NOT REQUIRED",
	"messageType":"dummy_messageType",
	"messageDescription":"dummy_messageDescription",
	"transactionId":"dummy_transactionId",
	"transactionType":"dummy_transactionType",
	"transaction":"dummy_transaction",
	"targetSystemId":"dummy_targetSystemId",
	"senderSystemId":"dummy_senderSystemId",
	"senderId":"dummy_senderId"
}
```

Click on the Save button.   
You have now created the first Test Request

## Creating a New Negative Test Request

**Repeat the above process and create a new Test Request.** 

### The Header Key /Values
```
Key             Value   
Accept          application/json   
Content-Type    application/json   
```

### Negative Test Body Text
```
{
    "messageId":"NOT REQUIRED",
	"messageType":"dummy_messageType",
	"messageDescription":"dummy_messageDescription",
	"transactionId":"dummy_transactionId",
	"transactionType":"dummy_transactionType",
	"transaction":"",
	"targetSystemId":"dummy_targetSystemId",
	"senderSystemId":"dummy_senderSystemId",
	"senderId":"dummy_senderId"
}
```

## Send the Negative Test Request
Click on the tab on the top where you have your negative test.   
Then Click on the Blue Send button.   

You should receive the following response after a few seconds

### Failed Response you receive from the Microservice
```
{
    "responseStatusCode": "408",
    "responseStatusMessage": "The Request is invalid : Transaction is missing",
    "messageId": null,
    "messageType": null,
    "messageDescription": null,
    "transactionId": null,
    "transactionType": null,
    "targetSystemId": null,
    "senderSystemId": null,
    "senderId": null
}
```
## Send the Positive Test Request
Click on the tab on the top where you have your positive test.   
Then Click on the Blue Send button.   

You should receive the following response after a few seconds

### Success Response you receive from the Microservice
```
{
    "responseStatusCode": "200",
    "responseStatusMessage": "OK : Message delivered to RabbitMQ",
    "messageId": "2021-04-05_15:11:23_1273558e-5fbb-4ca0-8e25-05c94734c867",
    "messageType": "dummy_messageType",
    "messageDescription": "dummy_messageDescription",
    "transactionId": "dummy_transactionId",
    "transactionType": "dummy_transactionType",
    "targetSystemId": "dummy_targetSystemId",
    "senderSystemId": "dummy_senderSystemId",
    "senderId": "dummy_senderId"
}
```


```


