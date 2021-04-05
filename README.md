# RabbitMQ Queue Producer SpringBoot Microservice

This Project offers a sample SpringBoot project to send messages to a RabbitMQ servers Queue using code buit using best practices
 
## Free Open Source FOS with the License based on GPL Version 3

This project is Free Open Source code FOS

It is provided free of charge and subject to the terms and license of this project

As this project is Free Opensource you are welcome to make a fork of this project for comercial use   
However please give the author of this project credit in your MD file

## REST REquest 

### PostMan test : MyTest /NicosQueueProducer

### URL
http://localhost:9080/rabbitmq/rlpty/qproducer/send

### Parameters
Key             Value
Accept          application/json
Content-Type    application/json

### Body
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