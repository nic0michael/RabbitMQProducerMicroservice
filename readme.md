# RabbitMQ Queue Producer SpringBoot Microservice

This Project offers a sample SpringBoot project to send messages to a RabbitMQ servers Queue using code buit using best practices

## Servers supported
This project runs with both a RabbitMQ Docker instance as well as on an Ubuntu Server with RabbitMQ server installed on it.  
  
Instructions for both installations are provided here

 
## Free Open Source FOS with the License based on GPL Version 3

This project is Free Open Source code FOS.   
We provide this Project as free of charge and subject to the terms and license of this project is GPL Version 3.   
As this project is Free Opensource you are welcome to make a fork of this project for comercial use.   
However please give the author of this project credit in your MD file

## Documentation Links
[To access our documentation use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/DesignPattern.md).   
[To access our POC documentation use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/POC.md).   
[To access our Recommendations for Microservices use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/MicroserviceRecomendations.md).   
[To access our Recommendations on Test Development use this link ](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/TDD.md).   
[To Access our Postman Installation and setup instructions use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/Postman.md).   
[To Access our RabbitMQ Server Installation and Administration instructions use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice).  

# Specifications
## REST Request and Response

### PostMan test : MyTest /NicosQueueProducer

### URL
http://localhost:9080/rabbitmq/rlpty/qproducer/send

### Parameters
```
Key             Value
Accept          application/json
Content-Type    application/json
```

### Body
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

### Success Response
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

### In the Logs
```
2021-04-05 14:52:23.233  INFO 33349 --- [nio-9080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 3 ms
2021-04-05 14:52:23.238  INFO 33349 --- [nio-9080-exec-1] z.c.n.r.p.c.MessageQueueController       : sendToMessageQueue called
2021-04-05 14:52:23.238  INFO 33349 --- [nio-9080-exec-1] z.c.n.r.poc.managers.ServiceManager      : sendToMessageQueue called
2021-04-05 14:52:23.240  INFO 33349 --- [nio-9080-exec-1] z.c.n.r.p.q.i.QueueProducerManagerImpl   : sendToMessageQueueService called
2021-04-05 14:52:23.242  INFO 33349 --- [nio-9080-exec-1] z.c.n.r.p.queue.impl.QueueProducerImpl   : sendToInputQueue called
2021-04-05 14:52:23.242  INFO 33349 --- [nio-9080-exec-1] z.c.n.r.p.queue.impl.QueueProducerImpl   : sendToInputQueue | Sent message to Queue
```

### Negative Test Body
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

### Failed Response
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

### In the Logs
```
2021-04-05 15:02:55.811  INFO 33349 --- [nio-9080-exec-2] z.c.n.r.p.c.MessageQueueController       : sendToMessageQueue called
2021-04-05 15:02:55.811  INFO 33349 --- [nio-9080-exec-2] z.c.n.r.poc.managers.ServiceManager      : sendToMessageQueue called
2021-04-05 15:02:55.812  INFO 33349 --- [nio-9080-exec-2] z.c.n.r.poc.validators.RequestValidator  : validateSendToQueueRequest called
2021-04-05 15:02:55.813  INFO 33349 --- [nio-9080-exec-2] z.c.n.r.poc.validators.RequestValidator  : validateRequest called
2021-04-05 15:02:55.814  INFO 33349 --- [nio-9080-exec-2] z.c.n.r.poc.validators.RequestValidator  : validateSendToQueueRequest | response : SendToQueueResponse [responseStatusCode=200, responseStatusMessage=OK : Message delivered to RabbitMQ, messageId=null, messageType=null, messageDescription=null, transactionId=null, transactionType=null, targetSystemId=null, senderSystemId=null, senderId=null]
2021-04-05 15:02:55.814  INFO 33349 --- [nio-9080-exec-2] z.c.n.r.p.q.i.QueueProducerManagerImpl   : sendToMessageQueueService called
2021-04-05 15:02:55.816  INFO 33349 --- [nio-9080-exec-2] z.c.n.r.p.queue.impl.QueueProducerImpl   : sendToInputQueue called
2021-04-05 15:02:55.816  INFO 33349 --- [nio-9080-exec-2] z.c.n.r.p.queue.impl.QueueProducerImpl   : sendToInputQueue | Sent message to Queue
2021-04-05 15:04:24.892  INFO 33349 --- [nio-9080-exec-5] z.c.n.r.p.c.MessageQueueController       : sendToMessageQueue called
2021-04-05 15:04:24.892  INFO 33349 --- [nio-9080-exec-5] z.c.n.r.poc.managers.ServiceManager      : sendToMessageQueue called
2021-04-05 15:04:24.892  INFO 33349 --- [nio-9080-exec-5] z.c.n.r.poc.validators.RequestValidator  : validateSendToQueueRequest called
2021-04-05 15:04:24.892  INFO 33349 --- [nio-9080-exec-5] z.c.n.r.poc.validators.RequestValidator  : validateRequest called
2021-04-05 15:04:24.892  INFO 33349 --- [nio-9080-exec-5] z.c.n.r.poc.validators.RequestValidator  : validateSendToQueueRequest | response : SendToQueueResponse [responseStatusCode=408, responseStatusMessage=The Request is invalid : Transaction is missing, messageId=null, messageType=null, messageDescription=null, transactionId=null, transactionType=null, targetSystemId=null, senderSystemId=null, senderId=null]
^C2021-04-05 15:05:32.639  INFO 33349 --- [      Thread-50] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'
```
# Installing RabbitMQ as a Docker Container
[To Access our RabbitMQ Server Installation and Administration instructions use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice).  



## Creating Exchange the Queues and Bindings using Exchange and Routing keys
```
Exchange : rabbitmq.in.x

Queue : rabbitmq.in.rlpty.q

Deadletter Queue : rabbitmq.dead.in.rlpty.q

Queue Routing Key : rabbitmq.rlpty.r

Deadletter Queue Routing Key : rabbitmq.dead.rlpty.r
```
