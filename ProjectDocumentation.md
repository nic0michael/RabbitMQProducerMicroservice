# RabbitMQ Queue Producer SpringBoot Microservice Documentation

## Tech Stack needed to build and run this project
 1. Apache Maven version 3.5.2 
 2. Java version 11.0.11 (java-11-openjdk-amd64)
 3. RabbitMQ version 3.8.14  (IN Docker Container)
 4. SonarQube version 8.8 (Community Edition IN Docker Container)
 5. JBehave version 5.0-SNAPSHOT for Java >8 (This is to be built from a git clone of [the jbehave project in GitHub](https://github.com/jbehave/jbehave-core)).   
    If you are using Java 8 then use version : 4.8.3 in the POM file.   

## 1. The Design Pattern used by the Project to build Better Microservices
[To access our documentation use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/DesignPattern.md).   

## 2. How we meet Dave Farley's Microservice Recommendations 
[To access our Recommendations for Microservices use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/MicroserviceRecomendations.md).   

## 3. Test Driven Development TDD
[To access our Recommendations on Test Development use this link ](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/TDD.md).   

## 4. Behaviour Driven  Development BDD
[To access our Recommendations on Test Development use this link ](https://github.com/nic0michael/RabbitMQProducerMicroservice/tree/master/jbehave-report/readme.md).

## 5. The Proof Of Concept (POC)
[To access our POC documentation use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/POC.md).   

## 6 Running SonarQube Tests
[To Access our SonarQube installation and testing Procedures use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/SonarQubeInstallationAndTesting.md).  


## 7. Installation Instructions
[To Access our Postman Installation and setup instructions use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/Postman.md).   

[To Access our Portainer Server Installation instructions use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/Portainer.md)

[To Access our RabbitMQ Server Installation and Administration instructions use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/RabitMQ.md).  

[To Access our SonarQube installation and testing Procedures use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/SonarQubeInstallationAndTesting.md).  

## 8. Creating a Docker Instance
[To Access our procedures on how to make a Docker Instance of this project use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/docker.md)


# Specifications
## 1. REST Request and Response

### 2. Postman test : MyTest /NicosQueueProducer

### 2.1 URL
http://localhost:9080/rabbitmq/rlpty/qproducer/send

### 2.2 Parameters
```
Key             Value
Accept          application/json
Content-Type    application/json
```

### 2.3 Body
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

### 2.4 Success Response
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

### 2.5 In the Logs
```
2021-04-05 14:52:23.233  INFO 33349 --- [nio-9080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 3 ms
2021-04-05 14:52:23.238  INFO 33349 --- [nio-9080-exec-1] z.c.n.r.p.c.MessageQueueController       : sendToMessageQueue called
2021-04-05 14:52:23.238  INFO 33349 --- [nio-9080-exec-1] z.c.n.r.poc.managers.ServiceManager      : sendToMessageQueue called
2021-04-05 14:52:23.240  INFO 33349 --- [nio-9080-exec-1] z.c.n.r.p.q.i.QueueProducerManagerImpl   : sendToMessageQueueService called
2021-04-05 14:52:23.242  INFO 33349 --- [nio-9080-exec-1] z.c.n.r.p.queue.impl.QueueProducerImpl   : sendToInputQueue called
2021-04-05 14:52:23.242  INFO 33349 --- [nio-9080-exec-1] z.c.n.r.p.queue.impl.QueueProducerImpl   : sendToInputQueue | Sent message to Queue
```
### 3. Postman test : MyNegativeTest /NicosQueueProducer
### 3.1 Negative Test Body
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

### 3.2 Failed Response
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

### 3.3 In the Logs
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
# 4. Installing RabbitMQ as a Docker Container
[To Access our RabbitMQ Server Installation and Administration instructions use this link](https://github.com/nic0michael/RabbitMQProducerMicroservice/blob/master/RabitMQ.md).  



## 5. Creating the Exchange, Queues, and the Bindings (using Exchange and Routing key)
```
Exchange : rabbitmq.in.x

Queue : rabbitmq.in.rlpty.q

Deadletter Queue : rabbitmq.dead.in.rlpty.q

Queue Routing Key : rabbitmq.rlpty.r

Deadletter Queue Routing Key : rabbitmq.dead.rlpty.r
```
